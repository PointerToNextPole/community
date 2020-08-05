package com.ptr2nextpole.community.service;

import com.ptr2nextpole.community.dto.NotificationDTO;
import com.ptr2nextpole.community.dto.PaginationDTO;
import com.ptr2nextpole.community.enums.NotificationStatusEnum;
import com.ptr2nextpole.community.enums.NotificationTypeEnum;
import com.ptr2nextpole.community.exception.CustomizeErrorCode;
import com.ptr2nextpole.community.exception.CustomizeException;
import com.ptr2nextpole.community.mapper.NotificationMapper;
import com.ptr2nextpole.community.model.Notification;
import com.ptr2nextpole.community.model.NotificationExample;
import com.ptr2nextpole.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {

        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<NotificationDTO>();
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        Integer totalPage = (int) Math.ceil((double) totalCount / size);

        if (page < 1) {
            page = 1;
        } else if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        //size * (page -1)
        Integer offset = size * (page - 1);

        NotificationExample example = new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        List<Notification> notifications = notificationMapper.
                selectByExampleWithRowbounds(example, new RowBounds(offset, size));

        if (notifications.size() == 0) {
            return paginationDTO;
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }

        paginationDTO.setData(notificationDTOS);

        return paginationDTO;
    }

    public Long unreadCount(Long userId) {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDTO read(Long id, User user) {

        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if (notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        if (!Objects.equals(notification.getReceiver(), user.getId())) {
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification, notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));

        return  notificationDTO;
    }
}
