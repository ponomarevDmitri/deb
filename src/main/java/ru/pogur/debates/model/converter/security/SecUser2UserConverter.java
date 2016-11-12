package ru.pogur.debates.model.converter.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import ru.pogur.debates.auxiliary.ListConverter;
import ru.pogur.debates.model.security.SecRole;
import ru.pogur.debates.model.security.SecUser;

/**
 * Created by dima-pc on 07.02.2016.
 */
public class SecUser2UserConverter implements Converter<SecUser, User> {
    private ListConverter<SecRole, GrantedAuthority> secRole2GrantedAuthorityConverter;

    public SecUser2UserConverter(ListConverter<SecRole, GrantedAuthority> secRole2GrantedAuthorityConverter) {
        if (secRole2GrantedAuthorityConverter != null) {
            this.secRole2GrantedAuthorityConverter = secRole2GrantedAuthorityConverter;
        } else {
            throw new AuthenticationServiceException("Converter<SecRole, GrantedAuthority> is not specified while" +
                    "creating SecUser2UserConverter");
        }
    }

    public User convert(SecUser secUser) {
        return new User(secUser.getUsername(), secUser.getPassword(), secRole2GrantedAuthorityConverter.convert(secUser.getRoles()));
    }
}
