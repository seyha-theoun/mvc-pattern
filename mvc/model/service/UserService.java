package mvc.model.service;

import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UpdateResponseDto;
import mvc.model.dto.UserResponseDto;

import java.util.List;
public interface UserService {
    UserResponseDto createUser(CreateUserDto createUserDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid, UpdateResponseDto updateRequestDto);
    int deleteUserByUuid(String uuid);
    List<UserResponseDto> searchUserByName (String name);


}
