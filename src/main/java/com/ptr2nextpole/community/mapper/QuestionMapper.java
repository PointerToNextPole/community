package com.ptr2nextpole.community.mapper;

import com.ptr2nextpole.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into QUESTION (TITLE, DESCRIPTION, GMT_CREATE, GMT_MODIFIED, CREATOR, TAG) " +
            "values ( #{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag} )")
    void create(Question question);
}
