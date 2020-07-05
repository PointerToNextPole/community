package com.ptr2nextpole.community.mapper;

import com.ptr2nextpole.community.model.Question;
import com.ptr2nextpole.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {

    @Insert("insert into user (NAME, ACCOUNT_ID, TOKEN, GMT_CREATE, GMT_MODIFIED, AVATAR_URL) " +
            "values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified}, #{avatarUrl})")
    void insert(User user);

    @Select("select * from USER where TOKEN = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from USER where ID = #{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from USER where ACCOUNT_ID = #{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    @Update("update USER set name = #{name}, token = #{token}, gmt_modified = #{gmtModified}, " +
            "avatar_url = #{avatarUrl} where id = #{id}")
    void update(User user);
}
