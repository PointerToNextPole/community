package com.ptr2nextpole.community.schedule;

import com.ptr2nextpole.community.cache.HotTagCache;
import com.ptr2nextpole.community.mapper.QuestionMapper;
import com.ptr2nextpole.community.model.Question;
import com.ptr2nextpole.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Slf4j
public class HotTagTasks {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private HotTagCache hotTagCache;

    @Scheduled(fixedRate = 10000)
    //@Scheduled(cron = "0 0 1 * * *")
    public void hotTagSchedule() {

        int offset = 0;
        int limit = 20;

        log.info("HotTagSchedule starts {}", new Date());

        List<Question> list = new ArrayList<>();

        Map<String, Integer> priorities = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {
                String[] tags = StringUtils.split(question.getTag(), ",");
                for (String tag : tags) {
                    Integer priority = priorities.get(tag);
                    if (priority != null) {
                        priorities.put(tag, 5 * priority + question.getCommentCount());
                    } else {
                        priorities.put(tag, question.getCommentCount());
                    }
                }
            }
            offset += limit;
        }

        priorities.forEach((k, v) -> { System.out.println(k + ":" + v); });

        hotTagCache.updateTags(priorities);

        log.info("HotTagSchedule stops {}", new Date());
    }
}
