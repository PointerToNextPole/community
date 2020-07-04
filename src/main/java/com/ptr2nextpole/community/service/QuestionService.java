package com.ptr2nextpole.community.service;

import com.ptr2nextpole.community.dto.PaginationDTO;
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

    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if(page < 1){
            page = 1;
        }else if(page > paginationDTO.getTotalPage()){
            page = paginationDTO.getTotalPage();
        }

        //size * (page -1)
        Integer offset = size * (page - 1);

        List<Question> questions = questionMapper.list(offset, size);
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
        paginationDTO.setQuestions(questionDTOList);

        return paginationDTO;
    }
}
