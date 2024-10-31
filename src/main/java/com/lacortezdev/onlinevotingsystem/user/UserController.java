package com.lacortezdev.onlinevotingsystem.user;

import com.lacortezdev.onlinevotingsystem.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserMapper userResponseMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userResponseMapper) {
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
    }

    @GetMapping()
    public List<UserResponse> getUsers() {
        List<UserResponse> usersResponse = userService.getAllUsers()
                .stream()
                .map(this.userResponseMapper::userToUserResponse)
                .toList();
        return usersResponse;
    }

    @PatchMapping()
    public ResponseEntity<?> editUser(@RequestBody UserRequestBody requestBody) {
        try {
            // fetch the existing user details from database
            // user_id is use as the username
            User oldUserDetails = this.userService.loadUserByUsername(requestBody.id().toString());

            // when the user_id does not match any user
            if (oldUserDetails == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            // create a new user detail with the request body
            User newUserDetails = User.builder()
                    .id(oldUserDetails.getId())
                    .firstName(requestBody.firstName())
                    .middleName(requestBody.middleName())
                    .lastName(requestBody.lastName())
                    .password(oldUserDetails.getPassword())
                    .roles(UserRole.valueOf(requestBody.role()))
                    .salt(oldUserDetails.getSalt())
                    .isActive(requestBody.isActive())
                    .build();

            // save the user
            User resultUser = this.userService.saveNewUser(newUserDetails);

            // when saving is unsuccessful
            if (resultUser == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping()
    public void deleteUser(@RequestParam Long userId) {
        this.userService.deleteUser(userId);
    }
}
