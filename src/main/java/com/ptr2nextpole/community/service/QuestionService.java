package com.ptr2nextpole.community.service;

import com.ptr2nextpole.community.dto.QuestionDTO;
import com.ptr2nextpole.community.mapper.QuestionMapper;
import com.ptr2nextpole.community.mapper.UserMapper;
import com.ptr2nextpole.community.model.Question;
import com.ptr2nextpole.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDTO> list() {

        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        if(questions != null && questions.size() != 0){
            for (Question question : questions) {
                User user = userMapper.findById(question.getCreator());
                QuestionDTO questionDTO = new QuestionDTO();
                BeanUtils.copyProperties(question, questionDTO);
                questionDTO.setUser(user);
                questionDTOList.add(questionDTO);
            }
        }
        return questionDTOList;
    }
}
