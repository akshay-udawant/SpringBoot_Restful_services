package net.javaguides.springboot.mapper;

import net.javaguides.springboot.dta.UserDto;
import net.javaguides.springboot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {
//    this method will return implementation class so we can use below methods
    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    public UserDto mapToUserDto(User user);

    public User mapToUser(UserDto userDto);
}
