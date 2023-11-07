package security;

import lombok.Data;

@Data
public class UserCredentialsDTO {
    private final String login;
    private final String password;
}


