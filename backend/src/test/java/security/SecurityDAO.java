package security;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@AllArgsConstructor
public class SecurityDAO {


    private final Connection connection;

    public void createUser(UserCredentialsDTO credentials) throws SQLException {
        String sql = "INSERT INTO USER (credentials.getLogin(), credentials.getPassword()) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, credentials.getLogin());
            statement.setString(2, credentials.getPassword());
            statement.executeUpdate();
        }
    }

    public User getUser(UserCredentialsDTO credentials) throws SQLException {
        String sql = "SELECT * FROM USER WHERE username = ? and password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, credentials.getLogin());
            statement.setString(2, credentials.getPassword());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    return new User(id, name, password);
                } else {
                    return null;
                }
            }
        }
    }

    public boolean checkIfUserExists(String username) throws SQLException {
        String sql = "SELECT * FROM USER WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }

}
