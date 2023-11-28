package user_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_api.dto.UserDto;
import user_api.service.userService.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById (@PathVariable Long id){
        UserDto userDto = userService.getUserById(id);
        if (userDto != null){
            return ResponseEntity.ok(userDto);
        }
        return ResponseEntity.badRequest().body("User Not Found! Check ID");
    }
    @GetMapping("/")
    public ResponseEntity<List <UserDto>> getUsers (){
        List <UserDto> usersDto = userService.getAll();
        return ResponseEntity.ok(usersDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById (@PathVariable Long id){
        if (userService.deleteUserById(id)){
            return ResponseEntity.ok().body("User is deleted!");
        }
        return ResponseEntity.badRequest().body("Failed to delete! Check ID or permissions!");
    }
    @PutMapping("/")
    public ResponseEntity<?> updateUser (@RequestBody UserDto userDto){
        UserDto updatedUser = userService.updateUser(userDto);
        if (updatedUser != null){
            return ResponseEntity.ok().body(updatedUser);
        }
        return ResponseEntity.badRequest().body("Failed to update! Check ID or permissions!");
    }
}