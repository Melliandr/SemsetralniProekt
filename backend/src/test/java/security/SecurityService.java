package security;

import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SecurityService {

    private final SecurityDAO securityDAO;

    public SecurityService(SecurityDAO securityDAO) {
        this.securityDAO = securityDAO;
    }

    public boolean login(UserCredentialsDTO credentials) {

    }

    public boolean register(UserCredentialsDTO credentials) throws SQLException {
        if (!isCredentialsValid(credentials) || securityDAO.checkIfUserExists(credentials.getLogin())) {
            return false;
        }
        securityDAO.createUser(credentials);
        return true;
    }

    private boolean isCredentialsValid(UserCredentialsDTO credentials) {
        // validate credentials
        return true;
    }
}
