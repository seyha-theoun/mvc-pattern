package mvc.model;

import mvc.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    public List<User> findAllUsers() {

        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUuid(rs.getString("uuid"));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setProfile(rs.getString("profile"));

                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public List<User> findByUuid(String uuid) {

        String sql = "SELECT * FROM users WHERE uuid = ?";

        List<User> users = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, uuid);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUuid(rs.getString("uuid"));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setProfile(rs.getString("profile"));

                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }
    public List<User> findByName(String name) {

        String sql = "SELECT * FROM users WHERE user_name ILIKE ?";

        List<User> users = new ArrayList<>();

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setUuid(rs.getString("uuid"));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setProfile(rs.getString("profile"));

                users.add(user);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public int remove(User user) {

        String sql = "DELETE FROM users WHERE id=?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setInt(1, user.getId());

            return ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public User update(User user) {

        String sql = """
            UPDATE users
            SET user_name=?,
                email=?,
                password=?,
                profile=?
            WHERE uuid=?
            """;

        try(
                Connection connection =
                        DatabaseConnection.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ){

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getProfile());
            ps.setString(5, user.getUuid());

            int rows = ps.executeUpdate();

            System.out.println("Updated rows: " + rows);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        return user;
    }

    public User save(User user) {

        String sql = """
                INSERT INTO users(uuid,user_name,email,password,profile)
                VALUES (?,?,?,?,?)
                """;

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {

            ps.setString(1, user.getUuid());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getProfile());

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}