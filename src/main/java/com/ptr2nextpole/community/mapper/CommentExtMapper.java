package com.ptr2nextpole.community.mapper;

import com.ptr2nextpole.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}