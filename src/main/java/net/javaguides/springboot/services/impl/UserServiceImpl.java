package net.javaguides.springboot.services.impl;

import lombok.AllArgsConstructor;
import net.javaguides.springboot.dta.UserDto;
import net.javaguides.springboot.entity.User;
import net.javaguides.springboot.exception.EmailAlreadyExistsException;
import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.mapper.AutoUserMapper;
import net.javaguides.springboot.mapper.UserMapper;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.services.UserSevice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserSevice {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
//      Convert UseeDto into User Entity
//        User user = UserMapper.mapToUser(userDto);

        //User user = modelMapper.map(userDto, User.class);
        Optional<User> optionalUser =  userRepository.findByEmail(userDto.getEmail());
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("Email Already Exist");
        }
        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User saveUser = userRepository.save(user);

//        Convert User Entity into UserDto
//        UserDto savedUserDto = UserMapper.mapToUserDto(saveUser);

//        UserDto savedUserDto = modelMapper.map(saveUser, UserDto.class);
        UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(saveUser);

        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));

//         return UserMapper.mapToUserDto(user);
//        return modelMapper.map(user, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users=  userRepository.findAll();
//        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
//        return users.stream().map((user)-> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return users.stream().map((user)-> AutoUserMapper.MAPPER.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto user) {
        User existingUser =  userRepository.findById(user.getId()).orElseThrow(()-> new ResourceNotFoundException("user", "id", user.getId()));
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setEmail(user.getEmail());
        BeanUtils.copyProperties(user,existingUser);
        User updatedUser =  userRepository.save(existingUser);
//        return UserMapper.mapToUserDto(updatedUser);
//        return modelMapper.map(updatedUser, UserDto.class);
        return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long userId) {
        User existingUser =  userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user", "id", userId));

        userRepository.deleteById(userId);
    }


}
