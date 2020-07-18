package com.ptr2nextpole.community.dto;

import lombok.Data;

@Data
public class CommentDTO {

    private Long ParentId;
    private String content;
    private int type;
}

