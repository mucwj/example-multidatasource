package com.github.mucwj.example.multidatasource.controller;

import com.github.mucwj.example.multidatasource.entity.Order;
import com.github.mucwj.example.multidatasource.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单Controller
 */
@RestController
public class OrderController {

    /**
     * 订单服务
     */
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 查询订单
     * @param id 订单id
     * @return 订单实体
     */
    @GetMapping("/order/{id}")
    public Order select(@PathVariable int id) {
        return orderService.select(id);
    }

    /**
     * 创建订单
     */
    @GetMapping("/order/create")
    public void create() {
        orderService.create();
    }

}
