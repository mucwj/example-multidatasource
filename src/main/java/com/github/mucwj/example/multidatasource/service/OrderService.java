package com.github.mucwj.example.multidatasource.service;

import com.github.mucwj.example.multidatasource.entity.Order;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, rollbackFor = Exception.class)
public interface OrderService {

    Order select(int id);

    @Transactional(rollbackFor = Exception.class)
    void create();

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    void insertUser();
}
