package ru.pogur.debates.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.pogur.debates.model.SecUser;
import ru.pogur.debates.repository.ISecUserDao;

/**
 * Created by dima-pc on 13.05.2016.
 */
@Service
public class UserService {

    @Autowired
    private ISecUserDao secUserDao;

    public SecUser getUserByUsername(String username){
        return secUserDao.findUserByName(username);
    }

    public SecUser getCurrentUser() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserByUsername(principal.getUsername());
    }
}
