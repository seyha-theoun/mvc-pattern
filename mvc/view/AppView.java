package mvc.view;

import mvc.controller.UserController;
import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UpdateResponseDto;
import mvc.model.dto.UserResponseDto;
import mvc.utils.APIResponseTamplete;

import java.util.Scanner;

public class AppView {

    private final UserController userController = new UserController();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        while (true) {

            System.out.println("\n===== USER MANAGEMENT SYSTEM =====");
            System.out.println("1. Create User");
            System.out.println("2. Get All Users");
            System.out.println("3. Update User");
            System.out.println("4. Search User By UUID");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");

            System.out.print("Choose option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {

                case 1 -> createUser();

                case 2 -> getAllUsers();

                case 3 -> updateUser();
                case 4 ->searchUserByName();

                case 5 -> deleteUser();

                case 6 -> {
                    System.out.println("Exit Program");
                    return;
                }

                default -> System.out.println("Invalid Option");
            }
        }
    }

    private void createUser() {

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        CreateUserDto dto =
                new CreateUserDto(name, email, password);

        APIResponseTamplete<UserResponseDto> creteUser = userController.createUser(dto);
        System.out.println(
                userController.createUser(dto)
        );
    }

    private void getAllUsers() {

        System.out.println(
                userController.getAllUser()
        );
    }

    private void updateUser() {

        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new password: ");
        String password = scanner.nextLine();

       UpdateResponseDto dto =
                new UpdateResponseDto(name, email, password);

        System.out.println(
                userController.updateUserByUuid(uuid, dto)
        );
    }
    private void searchUserByName() {

        System.out.print("Enter UUID to search: ");
        String uuid = scanner.nextLine();

        System.out.println(
                userController.getUserByUuid(uuid)
        );
    }
    private void deleteUser() {

        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();

        System.out.println(
                userController.deleteUserByUuid(uuid)
        );
    }
}