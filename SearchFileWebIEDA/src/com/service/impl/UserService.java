package com.service.impl;

import com.bean.Email;
import com.bean.IDCode;
import com.bean.User;
import com.dao.IUserDao;
import com.dao.impl.UserDao;
import com.exception.UserExistException;
import com.service.IUserService;
import com.util.IDCodeUtils;
import com.util.MD5Utils;

/**
 * Created by Pet on 2015-04-02.
 */
public class UserService implements IUserService {
    private IUserDao dao = new UserDao();

    //注册
    @Override
    public void register(User user) throws UserExistException {
        boolean flag = dao.find(user.getUsername());
        if (flag) {
            throw new UserExistException();
        } else {
            user.setShowpassword(user.getPassword());
            user.setPassword(MD5Utils.md5(user.getPassword()));
            dao.add(user);
        }
    }

    //登陆
    @Override
    public User login(String username, String password) {

        password = MD5Utils.md5(password);
        return dao.find(username, password);
    }

    //通过添一个 验证码的一个bean
    @Override
    public String getIDCode(IDCode idCode) {
        return new IDCodeUtils(idCode).getIDCodeStyle();
    }

    @Override
    public boolean sendEmail(Email semail) {
        return dao.sendEmail(semail);
    }

}
