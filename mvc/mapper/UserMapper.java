package mvc.mapper;

import mvc.model.User;
import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UserResponseDto;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public User fromUserCreateDtoToUser(CreateUserDto createUserDto){
        return new User(
                new Random().nextInt(9999),
                UUID.randomUUID().toString(),
                createUserDto.name(),
                createUserDto.email(),
                createUserDto.password(),
                "avarta.png"
        );
    }
    public UserResponseDto fromUserToUserResponseDto(User user){
        return new UserResponseDto(
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getProfile()
        );
    }
}
