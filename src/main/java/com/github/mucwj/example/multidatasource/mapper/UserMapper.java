package com.github.mucwj.example.multidatasource.mapper;

import com.github.mucwj.example.multidatasource.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM tb_user WHERE id = #{id}")
    User select(@Param("id") int id);

    @Insert("INSERT INTO tb_user (name) values(#{name})")
    void insert(User user);

    @Delete("DELETE FROM tb_user WHERE id = #{id}")
    void delete(@Param("id") int id);
}
