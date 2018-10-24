package cn.njyazheng.demo.controller;

import cn.njyazheng.demo.domain.User;
import cn.njyazheng.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/list")
    public List<User>getUserList(){
        return userService.getAllUsers();
    }
}
