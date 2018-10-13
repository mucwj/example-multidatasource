package com.github.mucwj.example.multidatasource.service;

import com.github.mucwj.example.multidatasource.entity.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true, rollbackFor = Exception.class)
public interface UserService {

    User select(int id);

    @Transactional(rollbackFor = Exception.class)
    void create(User user);

    @Transactional(rollbackFor = Exception.class)
    void delete(int id);
}
