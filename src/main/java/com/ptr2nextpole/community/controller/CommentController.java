package com.ptr2nextpole.community.controller;

import com.ptr2nextpole.community.dto.CommentCreateDTO;
import com.ptr2nextpole.community.dto.ResultDTO;
import com.ptr2nextpole.community.exception.CustomizeErrorCode;
import com.ptr2nextpole.community.model.Comment;
import com.ptr2nextpole.community.model.User;
import com.ptr2nextpole.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/comment")
    public Object comment(@RequestBody CommentCreateDTO commentDTO,
                          HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
