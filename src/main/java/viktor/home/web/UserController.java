package viktor.home.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import viktor.home.domain.User;
import viktor.home.service.UserService;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestBody User user){
        return "User " + userService.createUser(user).getUserName() +" created successfully";
    }


    @GetMapping(value = "{jwt}")
    public String getUserNameByJwt(@PathVariable String jwt){
        return userService.getUserByJwt(jwt);
    }

}
