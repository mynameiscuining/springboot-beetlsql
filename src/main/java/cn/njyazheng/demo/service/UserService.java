package cn.njyazheng.demo.service;

import cn.njyazheng.demo.dao.UserDao;
import cn.njyazheng.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     *
     * @return
     */
    public List<User>getAllUsers(){
        return userDao.all();
    }
}
