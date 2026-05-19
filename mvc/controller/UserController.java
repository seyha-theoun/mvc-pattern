package mvc.controller;

import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UpdateResponseDto;
import mvc.model.dto.UserResponseDto;
import mvc.model.service.UserService;
import mvc.model.service.UserServiceIml;
import mvc.utils.APIResponseTamplete;

import java.time.LocalDate;
import java.util.List;

public class UserController {

    private final UserService userService = new UserServiceIml();

    public APIResponseTamplete<UserResponseDto> createUser(
            CreateUserDto createUserDto
    ) {

        return APIResponseTamplete.<UserResponseDto>builder()
                .status(201)
                .message("User created successfully")
                .timeStamp(LocalDate.now())
                .data(userService.createUser(createUserDto))
                .build();
    }

    public APIResponseTamplete<List<UserResponseDto>> getAllUsers() {

        return new APIResponseTamplete<>(
                200,
                "Get all users successfully",
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

        return new APIResponseTamplete<>(
                200,
                "Update successfully",
                LocalDate.now(),
                updatedUser
        );
    }

    public APIResponseTamplete<String> deleteUserByUuid(
            String uuid
    ) {

        userService.deleteUserByUuid(uuid);

        return new APIResponseTamplete<>(
                200,
                "Delete successfully",
                LocalDate.now(),
                "User deleted"
        );
    }

    public APIResponseTamplete<UserResponseDto> getUserByUuid(
            String uuid
    ) {

        UserResponseDto user =
                userService.getUserByUuid(uuid);

        return new APIResponseTamplete<>(
                200,
                "User found successfully",
                LocalDate.now(),
                user
        );
    }

    public APIResponseTamplete<List<UserResponseDto>> searchUserByName(
            String name
    ) {

        return new APIResponseTamplete<>(
                200,
                "Search users successfully",
                LocalDate.now(),
                userService.searchUserByName(name)
        );
    }


}