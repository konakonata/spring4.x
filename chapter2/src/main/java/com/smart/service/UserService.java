package com.smart.service;


import com.smart.dao.LoginLogDao;
import com.smart.dao.UserDao;
import com.smart.domain.LoginLog;
import com.smart.domain.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserDao userDao;

    private LoginLogDao loginLogDao;


    @Autowired
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }

    @Autowired
    public void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String userName, String password){
        int a = userDao.getMatchCount(userName,password);
        if (a > 0) {
            return true;
        }
        return false;
    }
    public User findUserByUsername(String userName){
        User user = userDao.queryByUsername(userName);
        return user;
    }

    @Transactional
    public void LoginSuccess(User user){
        user.setCredits(user.getCredits() + 5);
        LoginLog loginLog = new LoginLog();
        loginLog.setIp(user.getLastIp());
        loginLog.setUserId(user.getUserId());
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }

}
