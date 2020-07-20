package com.ptr2nextpole.community.mapper;

import com.ptr2nextpole.community.model.Question;
import org.springframework.stereotype.Component;

@Component
public interface QuestionExtMapper {

    int incView(Question record);
    int incCommentCount(Question record);
}