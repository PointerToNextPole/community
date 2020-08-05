package com.ptr2nextpole.community.controller;

import com.ptr2nextpole.community.dto.NotificationDTO;
import com.ptr2nextpole.community.enums.NotificationTypeEnum;
import com.ptr2nextpole.community.model.User;
import com.ptr2nextpole.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(@PathVariable(name = "id") Long id,
                          HttpServletRequest request,
                          Model model) {

        //检查用户是否登录
        User user = (User) request.getSession().getAttribute("user");
        //若未登录
        if (user == null) {
            return "redirect:/";
        }

        NotificationDTO notificationDTO = notificationService.read(id, user);
        if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
                || NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()){
            return "redirect:/question/" + notificationDTO.getOuterId();
        }else{
            return "redirect:/";
        }

    }
}
