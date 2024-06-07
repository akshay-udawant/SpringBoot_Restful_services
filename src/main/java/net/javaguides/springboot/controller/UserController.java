package net.javaguides.springboot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.javaguides.springboot.dta.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.services.UserSevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST API for User Resource",
        description = "CRUD REST API's - Create USer, Update User, Get User, Get All Users, Delete USer"
)
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private UserSevice userSevice;
    @Operation(
            summary = "Create USer REST API",
            description = "Create User REST API is used to save user in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 Created"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto user){
        UserDto saveUser = userSevice.createUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Get User By ID REST API",
            description = "Get User By Id REST API is used to get single user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId){
        UserDto user = userSevice.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @Operation(
            summary = "Get All User REST API",
            description = "Get All Users REST API is used to get all users from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> user = userSevice.getAllUsers();
        return new ResponseEntity<List<UserDto>>(user,HttpStatus.OK);
    }
    @Operation(
            summary = "Update User By Id REST API",
            description = "Update Users REST API is used to Update user By Id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PostMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long userId,@RequestBody @Valid UserDto user){
        user.setId(userId);
        UserDto updateUser =  userSevice.updateUser(user);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete User By ID REST API",
            description = "Delete Users By Id REST API is used to Delete particular user from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userSevice.deleteUserById(userId);
        return new ResponseEntity<String>("User deleted successfully",HttpStatus.OK);
    }
}
