package security;

//import java.io.IOException;
//import java.util.Collections;
//
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//
//public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
//
//    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request,
//                                    HttpServletResponse response,
//                                    FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader("Authorization");
//
//        if (header == null || !header.startsWith("Bearer ")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        chain.doFilter(request, response);
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if (token != null) {
//            // parse the token and validate it
//            String user = JWTUtils.parseJWT(token);
//
//            if (user != null) {
//                return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
//            }
//            return null;
//        }
//        return null;
//    }
//}