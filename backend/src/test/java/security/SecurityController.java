package security;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@AllArgsConstructor
public class SecurityController {

    private final SecurityService securityService;
    private final JwtService jwtService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody UserCredentialsDTO credentials, @RequestHeader String authorizationHeader) {
        if (!securityService.login(credentials)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String jwt = jwtService.generateJWT(credentials.getLogin());

        return ResponseEntity.ok(jwt);

    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserCredentialsDTO credentials) throws SQLException {
        boolean registeredSuccessfully = securityService.register(credentials);

        String jwt = jwtService.generateJWT(credentials.getLogin());

        // return the JWT in the response
        if(!registeredSuccessfully){
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        } else {
            return ResponseEntity.ok(jwt);
        }
    }
}

