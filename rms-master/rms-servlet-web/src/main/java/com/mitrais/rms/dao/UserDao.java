package com.mitrais.rms.dao;

import com.mitrais.rms.model.User;

import java.util.Optional;

/**
 * Provides interface specific to {@link User} data
 */
public interface UserDao extends Dao<User, Long>
{
    /**
     * Find {@link User} by its username
     * @param userName username
     * @return user
     */
    Optional<User> findByUserName(String userName);
    
    /**
     * Find {@link User} by its username and password
     * @param userName username and password
     * @return user
     */
    Optional<User> findByUserNameAndPassword(String userName, String password);
}
