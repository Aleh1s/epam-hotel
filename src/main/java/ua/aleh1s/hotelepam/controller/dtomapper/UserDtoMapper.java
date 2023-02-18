package ua.aleh1s.hotelepam.controller.dtomapper;

import ua.aleh1s.hotelepam.controller.dto.UserDto;
import ua.aleh1s.hotelepam.model.entity.UserEntity;

import java.util.function.Function;

public class UserDtoMapper implements Function<UserEntity, UserDto> {
    @Override
    public UserDto apply(UserEntity userEntity) {
        return UserDto.Builder.newBuilder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .phoneNumber(userEntity.getPhoneNumber())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .locale(userEntity.getLocale())
                .role(userEntity.getRole())
                .account(userEntity.getAccount())
                .build();
    }
}
