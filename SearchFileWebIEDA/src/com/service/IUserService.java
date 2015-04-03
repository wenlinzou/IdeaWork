package com.service;

import com.bean.Email;
import com.bean.IDCode;
import com.bean.User;
import com.exception.UserExistException;

/**
 * Created by Pet on 2015-04-02.
 */
public interface IUserService {
    //注册
    public void register(User user) throws UserExistException;

    //登陆
    public User login(String username, String password);

    //通过添一个 验证码的一个bean
    public String getIDCode(IDCode iword);

    public boolean sendEmail(Email semail);
}
