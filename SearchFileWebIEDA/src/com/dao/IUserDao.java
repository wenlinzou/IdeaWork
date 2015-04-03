package com.dao;

import com.bean.Email;
import com.bean.User;

/**
 * Created by Pet on 2015-04-02.
 */
public interface IUserDao {
    public void add(User user);

    public User find(String username, String password);

    public boolean find(String username);

    public boolean sendEmail(Email email);
}
