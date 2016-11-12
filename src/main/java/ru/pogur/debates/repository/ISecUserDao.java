package ru.pogur.debates.repository;

import ru.pogur.debates.model.security.SecUser;

/**
 * Created by dima-pc on 07.04.2016.
 */
public interface ISecUserDao {
    SecUser findUserByName(String username);

}
