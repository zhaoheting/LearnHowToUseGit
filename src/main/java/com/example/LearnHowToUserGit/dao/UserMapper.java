package com.example.LearnHowToUserGit.dao;

import com.example.LearnHowToUserGit.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into t_user (nick_name,user_name,user_pwd,create_date,update_date) values (#{nickName},#{userName},#{userPwd},#{createDate},#{updateDate})")
    User insert(User user);

    @Select("select * from t_user where user_name = #{userName}")
    @Results({
            @Result(property = "userId", column = "user_Id"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "userCode", column = "user_code"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "userPwd", column = "user_pwd"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "updateDate", column = "update_date")
    })
    User findByName(String userName);
}

