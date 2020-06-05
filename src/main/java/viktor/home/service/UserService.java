package viktor.home.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import viktor.home.domain.User;
import viktor.home.repository.UserRepository;
import viktor.home.util.JwtUtil;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public String getUserByJwt(String jwt){
        return jwtUtil.extrctUserName(jwt);
    }
}
