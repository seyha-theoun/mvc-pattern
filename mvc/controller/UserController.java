package mvc.controller;


import mvc.model.User;
import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UpdateResponseDto;
import mvc.model.dto.UserResponseDto;
import mvc.model.service.UserService;
import mvc.model.service.UserServiceIml;
import mvc.utils.APIResponseTamplete;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;

public class UserController {
    public final UserService userService = new UserServiceIml();
    public APIResponseTamplete<UserResponseDto> createUser(CreateUserDto createUserDto) {

        return APIResponseTamplete.<UserResponseDto>builder()
                .status(201)
                .message("User created successfully")
                .data(userService.createUser(createUserDto))
                .timeStamp(LocalDate.now())
                .build();
    }

    public APIResponseTamplete<List<UserResponseDto>>getAllUser(){
        return new APIResponseTamplete<>(
                200,
                "Get All User Successfully",
                LocalDate.now(),
                userService.getAllUsers()
        );

    }
    public APIResponseTamplete<UserResponseDto> updateUserByUuid(
            String uuid,
            UpdateResponseDto updateRequestDto
    ) {

        UserResponseDto updatedUser =
                userService.updateUserByUuid(uuid, updateRequestDto);

        return new APIResponseTamplete<UserResponseDto>(
                200,
                "Update Successfully",
               LocalDate.now(),
                updatedUser


        );
    }

    public APIResponseTamplete<String> deleteUserByUuid(String uuid) {

        userService.deleteUserByUuid(uuid);

        return new APIResponseTamplete<>(
                200,
                "Delete Successfully",
                LocalDate.now(),
                "User deleted"
        );
    }
    public APIResponseTamplete<UserResponseDto> getUserByUuid(String uuid) {

        UserResponseDto result = userService.getUserByUuid(uuid);

        return new APIResponseTamplete<>(
                200,
                "User found successfully",
                LocalDate.now(),
                result
        );
    }
}
