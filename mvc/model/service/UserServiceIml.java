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
    public final UserMapper userMapper = new UserMapper();

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {

//        );
//
//        // Save user to database
//        userDao.save(user);
//
//        // Map User -> UserResponseDto
//        UserResponseDto userResponseDto = new UserResponseDto(
//                user.getUuid(),
//                user.getName(),
//                user.getEmail(),
//                user.getProfile()
//        );
        User user = userMapper.fromUserCreateDtoToUser(createUserDto);
        userDao.save(user);

        return userMapper.fromUserToUserResponseDto(user);
    }


    @Override
    public List<UserResponseDto> getAllUsers() {

        return userDao.findAllUsers().stream()
                .map(userMapper::fromUserToUserResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        User user = userDao.findAllUsers()
                .stream()
                .findFirst().get();
        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public UserResponseDto updateUserByUuid(
            String uuid,
            UpdateResponseDto updateRequestDto
    ) {

        User user = userDao.findByUuid(uuid)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        if (updateRequestDto.getName() != null) {
            user.setName(updateRequestDto.getName());
        }

        if (updateRequestDto.getEmail() != null) {
            user.setEmail(updateRequestDto.getEmail());
        }

        if (updateRequestDto.getPassword() != null) {
            user.setPassword(updateRequestDto.getPassword());
        }

        userDao.update(user);

        return userMapper.fromUserToUserResponseDto(user);
    }

    @Override
    public int deleteUserByUuid(String uuid) {

        User user = userDao.findAllUsers()
                .stream()
                .filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
        userDao.remove(user);
        return 1;

    }

    @Override
    public List<UserResponseDto> searchUserByName(String name) {
        return userDao.findAllUsers()
                .stream()
                .filter(u->u.getName()!=null)
                .filter(u->u.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .map(userMapper::fromUserToUserResponseDto)
                .toList();
    }


}