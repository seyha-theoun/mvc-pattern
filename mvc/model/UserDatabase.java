package mvc.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class UserDatabase {

    public static List<User> users = new ArrayList<>();
}
