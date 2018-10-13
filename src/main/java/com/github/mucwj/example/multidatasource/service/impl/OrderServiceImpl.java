package com.github.mucwj.example.multidatasource.service.impl;

import com.github.mucwj.example.multidatasource.datasource.DSLabel;
import com.github.mucwj.example.multidatasource.datasource.SwitchDataSource;
import com.github.mucwj.example.multidatasource.entity.Order;
import com.github.mucwj.example.multidatasource.entity.User;
import com.github.mucwj.example.multidatasource.mapper.OrderMapper;
import com.github.mucwj.example.multidatasource.service.OrderService;
import com.github.mucwj.example.multidatasource.service.UserService;
import org.springframework.stereotype.Service;

@Service
@SwitchDataSource(DSLabel.ORDER)
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    private UserService userService;

    public OrderServiceImpl(OrderMapper orderMapper, UserService userService) {
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

    @Override
    public Order select(int id) {
        return orderMapper.select(id);
    }

    @Override
    public void create() {
        Order order = new Order();
        order.setName("name-2");
        orderMapper.insert(order);
    }

    @SwitchDataSource(DSLabel.USER)
    public void insertUser() {
        User user = new User();
        user.setName("name-2");
        userService.create(user);
    }
}
