package cn.bnt.shoppingcart.service;

import cn.bnt.shoppingcart.beans.User;
import cn.bnt.shoppingcart.dao.UserDao;
import cn.bnt.shoppingcart.utils.BeanFactoryUtils;

public class UserService {

    private final UserDao userDao = BeanFactoryUtils.getBean(UserDao.class);

    public void register(User user) {
        userDao.save(user);
    }

    public User loginUser(String uid, String loginPwd) {
        return userDao.queryByUidAndLoginPwd(uid, loginPwd);
    }
}
