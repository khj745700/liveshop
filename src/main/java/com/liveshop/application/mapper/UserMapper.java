package com.liveshop.application.mapper;

import com.liveshop.application.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, nickname, phone_num) VALUES (#{username}, #{password}, #{nickname}, #{phoneNum}) ")
    void insertUser(User user);

    @Select("SELECT id, password, phone_num, username, nickname FROM users WHERE id = #{id}")
    User findById(@Param("id") Integer id);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE username = #{username}) as exist")
    Boolean existByUsername(@Param("username") String username);

    @Select("SELECT EXISTS(SELECT 1 FROM users WHERE nickname = #{nickname}) as exist")
    Boolean existByNickname(@Param("nickname") String nickname);

    @Select("SELECT id, password, nickname, username, phone_num as phoneNum FROM users WHERE username=#{username}")
    User findByUsername(@Param("username")String username);
}
