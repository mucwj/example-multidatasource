package com.github.mucwj.example.multidatasource.controller;

import com.github.mucwj.example.multidatasource.entity.User;
import com.github.mucwj.example.multidatasource.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户Controller
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询用户
     * @param id 用户id
     * @return 用户实体
     */
    @GetMapping("/user/{id}")
    public User select(@PathVariable int id) {
        return userService.select(id);
    }

    /**
     * 删除用户
     * @param id 用户id
     */
    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable int id) {
        userService.delete(id);
    }

}
