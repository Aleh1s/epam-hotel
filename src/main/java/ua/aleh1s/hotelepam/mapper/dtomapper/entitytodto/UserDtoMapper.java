package ua.aleh1s.hotelepam.mapper.dtomapper.entitytodto;

import ua.aleh1s.hotelepam.model.dto.UserDto;
import ua.aleh1s.hotelepam.model.entity.UserEntity;

import java.util.function.Function;

public class UserDtoMapper implements Function<UserEntity, UserDto> {
    @Override
    public UserDto apply(UserEntity userEntity) {
        return new UserDto(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhoneNumber(),
                userEntity.getLocale(),
                userEntity.getRole(),
                userEntity.getAccount()
        );
    }
}
