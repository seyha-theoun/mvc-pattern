package mvc.view;

import mvc.controller.UserController;
import mvc.model.dto.CreateUserDto;
import mvc.model.dto.UpdateResponseDto;
import mvc.model.dto.UserResponseDto;
import mvc.utils.APIResponseTamplete;
import mvc.controller.UserController;

import java.util.Scanner;
import java.util.List;

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
            System.out.println("5. Search User By Name");
            System.out.println("6. Delete User");
            System.out.println("7. Exit");

            System.out.print("Choose option: ");
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {

                case 1 -> createUser();

                case 2 -> getAllUsers();

                case 3 -> updateUser();
                case 4 -> searchUserByUuid();
                case 5 -> searchUserByName();

                case 6 -> deleteUser();

                case 7 -> {
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

    }

    private void getAllUsers() {

        APIResponseTamplete<List<UserResponseDto>> response =
                userController.getAllUsers();

        printUsersTable(response.data());
    }

    private void printUsersTable(List<UserResponseDto> users) {

        if (users == null || users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        String[] headers = {"UUID", "NAME", "EMAIL", "PROFILE"};
        int[] widths = new int[headers.length];

        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }

        for (UserResponseDto user : users) {
            widths[0] = Math.max(widths[0], safe(user.uuid()).length());
            widths[1] = Math.max(widths[1], safe(user.name()).length());
            widths[2] = Math.max(widths[2], safe(user.email()).length());
            widths[3] = Math.max(widths[3], safe(user.profile()).length());
        }

        String border = buildBorder(widths);
        System.out.println(border);
        System.out.println(buildRow(headers, widths));
        System.out.println(border);

        for (UserResponseDto user : users) {
            String[] row = {
                    safe(user.uuid()),
                    safe(user.name()),
                    safe(user.email()),
                    safe(user.profile())
            };
            System.out.println(buildRow(row, widths));
        }

        System.out.println(border);
    }

    private String buildBorder(int[] widths) {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int width : widths) {
            sb.append("-").append("-".repeat(width)).append("-").append("+");
        }
        return sb.toString();
    }

    private String buildRow(String[] values, int[] widths) {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < values.length; i++) {
            sb.append(" ").append(padRight(values[i], widths[i])).append(" |");
        }
        return sb.toString();
    }

    private String padRight(String value, int width) {
        if (value.length() >= width) {
            return value;
        }
        return value + " ".repeat(width - value.length());
    }

    private String safe(String value) {
        return value == null ? "" : value;
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

        System.out.print("Enter new profile: ");
        String profile = scanner.nextLine();

       UpdateResponseDto dto =
                new UpdateResponseDto();
        dto.setName(name);
        dto.setEmail(email);
        dto.setPassword(password);
        dto.setProfile(profile);

        System.out.println(
                userController.updateUserByUuid(uuid, dto)
        );
    }
    private void searchUserByUuid() {

        System.out.print("Enter UUID to search: ");
        String uuid = scanner.nextLine();

        try {
            APIResponseTamplete<UserResponseDto> response =
                    userController.getUserByUuid(uuid);

            if (response.data() == null) {
                System.out.println("User not found.");
                return;
            }

            printUsersTable(List.of(response.data()));
        } catch (RuntimeException ex) {
            System.out.println("User not found.");
        }
    }
    private void searchUserByName() {

        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        APIResponseTamplete<List<UserResponseDto>> response =
                userController.searchUserByName(name);

        printUsersTable(response.data());
    }
    private void deleteUser() {

        System.out.print("Enter UUID: ");
        String uuid = scanner.nextLine();

        System.out.println(
                userController.deleteUserByUuid(uuid)
        );
    }
}