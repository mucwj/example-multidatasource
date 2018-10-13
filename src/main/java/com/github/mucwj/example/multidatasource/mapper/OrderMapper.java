package com.github.mucwj.example.multidatasource.mapper;

import com.github.mucwj.example.multidatasource.entity.Order;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM tb_order WHERE id = #{id}")
    Order select(@Param("id") int id);

    @Insert("INSERT INTO tb_order (name) VALUES(#{name})")
    void insert(Order order);

    @Delete("DELETE FROM tb_order WHERE user_id = #{userId}")
    void deleteByUserId(@Param("userId") int userId);
}
