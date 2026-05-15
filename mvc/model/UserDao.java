package mvc.model;

import java.util.List;
import java.util.Optional;

public class UserDao {
    public List<User> findAllUsers() {
        return UserDatabase.users;
    }

    public int remove(User user) {
        UserDatabase.users.remove(user);
        return 1;
    }

    public User update(User uu) {

        User user = UserDatabase.users.stream()
                .filter(u -> u.getId().equals(uu.getId()))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        user.setName(uu.getName());
        user.setEmail(uu.getEmail());
        user.setPassword(uu.getPassword());
        user.setProfile(uu.getProfile());

        return user;
    }
    public Optional<User> findByUuid(String uuid) {

        return UserDatabase.users
                .stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }
    public User save(User user) {
        UserDatabase.users.add(user);
        return user;
    }
}
