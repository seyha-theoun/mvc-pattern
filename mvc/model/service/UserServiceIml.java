package mvc.model.service;

import mvc.mapper.UserMapper;
import mvc.model.User;
import mvc.model.UserDao;
import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UpdateResponseDto;
import mvc.model.dto.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceIml implements UserService {

    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper = new UserMapper();

    @Override
    public UserResponseDto createUser(
            CreateUserDto createUserDto
    ) {

        User user =
                userMapper.fromUserCreateDtoToUser(createUserDto);

        userDao.save(user);

        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {

        return userDao.findAllUsers()
                .stream()
                .map(userMapper::fromUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserByUuid(
            String uuid
    ) {

        List<User> users = userDao.findByUuid(uuid);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = users.get(0);

        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserByUuid(
            String uuid,
            UpdateResponseDto updateRequestDto
    ) {

        List<User> users = userDao.findByUuid(uuid);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = users.get(0);

        if (updateRequestDto.getName() != null
                && !updateRequestDto.getName().isBlank()) {

            user.setName(updateRequestDto.getName());
        }

        if (updateRequestDto.getEmail() != null
                && !updateRequestDto.getEmail().isBlank()) {

            user.setEmail(updateRequestDto.getEmail());
        }

        if (updateRequestDto.getPassword() != null
                && !updateRequestDto.getPassword().isBlank()) {

            user.setPassword(updateRequestDto.getPassword());
        }

        if (updateRequestDto.getProfile() != null
                && !updateRequestDto.getProfile().isBlank()) {

            user.setProfile(updateRequestDto.getProfile());
        }

        userDao.update(user);

        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public int deleteUserByUuid(
            String uuid
    ) {

        List<User> users = userDao.findByUuid(uuid);

        if (users.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = users.get(0);

        return userDao.remove(user);
    }
    @Override
    public List<UserResponseDto> searchUserByName(
            String name
    ) {

        return userDao.findAllUsers()
                .stream()
                .filter(user -> user.getName() != null)
                .filter(user ->
                        user.getName()
                                .toLowerCase()
                                .contains(name.toLowerCase())
                )
                .map(userMapper::fromUserToUserResponseDto)
                .collect(Collectors.toList());
    }
}