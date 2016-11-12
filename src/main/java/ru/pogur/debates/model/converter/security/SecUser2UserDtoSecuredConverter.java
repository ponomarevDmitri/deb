package ru.pogur.debates.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import ru.pogur.debates.dto.UserDto;
import ru.pogur.debates.model.security.SecUser;

/**
 * Created by dima-pc on 21.08.2016.
 */
public class SecUser2UserDtoSecuredConverter implements Converter<SecUser, UserDto> {
    @Override
    public UserDto convert(SecUser source) {
        UserDto userDto = new UserDto();
        userDto.setUsername(source.getUsername());
        return userDto;
    }
}
