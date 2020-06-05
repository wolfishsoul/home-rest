package viktor.home.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import viktor.home.domain.AutenticationRequest;
import viktor.home.domain.AuthenticationResponse;
import viktor.home.util.JwtUtil;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping()
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AutenticationRequest autenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autenticationRequest.getUserName(), autenticationRequest.getPassword())
            );
        } catch (BadCredentialsException ex) {
            throw new Exception("Incorrect credentials", ex);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(autenticationRequest.getUserName());
        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
