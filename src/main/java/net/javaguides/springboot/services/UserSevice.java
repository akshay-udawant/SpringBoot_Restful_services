package net.javaguides.springboot.services;

import net.javaguides.springboot.dta.UserDto;
import net.javaguides.springboot.entity.User;

import java.util.List;

public interface UserSevice {
    UserDto createUser(UserDto user);
    UserDto getUserById(Long userId);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto user);
    void deleteUserById(Long userId);
}
