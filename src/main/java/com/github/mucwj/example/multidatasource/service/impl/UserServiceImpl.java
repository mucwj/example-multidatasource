package com.github.mucwj.example.multidatasource.service.impl;

import com.github.mucwj.example.multidatasource.datasource.DSLabel;
import com.github.mucwj.example.multidatasource.datasource.SwitchDataSource;
import com.github.mucwj.example.multidatasource.entity.User;
import com.github.mucwj.example.multidatasource.mapper.OrderMapper;
import com.github.mucwj.example.multidatasource.mapper.UserMapper;
import com.github.mucwj.example.multidatasource.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@SwitchDataSource(DSLabel.USER)
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    private OrderMapper orderMapper;

    public UserServiceImpl(UserMapper userMapper, OrderMapper orderMapper) {
        this.userMapper = userMapper;
        this.orderMapper = orderMapper;
    }

    @Override
    public User select(int id) {
        return userMapper.select(id);
    }

    @Override
    public void create(User user) {
        userMapper.insert(user);
    }

    @Override
    public void delete(int id) {
        userMapper.delete(id);
        // 这里是为了能够触发AOP
        ((UserServiceImpl) AopContext.currentProxy()).deleteOrder(id);
    }

    @SwitchDataSource(DSLabel.ORDER)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void deleteOrder(int id) {
        orderMapper.deleteByUserId(id);
    }
}
