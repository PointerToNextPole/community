package com.ptr2nextpole.community.mapper;

import com.ptr2nextpole.community.model.Comment;
import org.springframework.stereotype.Component;

@Component
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}