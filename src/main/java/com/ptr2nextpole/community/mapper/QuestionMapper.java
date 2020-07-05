package com.ptr2nextpole.community.mapper;

import com.ptr2nextpole.community.dto.QuestionDTO;
import com.ptr2nextpole.community.model.Question;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into QUESTION (TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) " +
            "values ( #{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag} )")
    void create(Question question);

    @Select("select * from QUESTION limit #{size} offset #{offset}")
    List<Question> list(@Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from QUESTION")
    Integer count();

    @Select("select * from QUESTION where CREATOR = #{userId} limit #{size} offset #{offset}")
    List<Question> listByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("size") Integer size);

    @Select("select count(1) from QUESTION where CREATOR = #{userId}")
    Integer countByUserId(@Param("userId") Integer userId);

    @Select("select * from QUESTION where ID = #{id};")
    Question getById(@Param("id") Integer id);

    @Update({"update question set title = #{title}, description = #{description}, tag = #{tag}, " +
            "gmt_modified = #{gmtModified} where id = #{id}"})
    void update(Question question);
}
