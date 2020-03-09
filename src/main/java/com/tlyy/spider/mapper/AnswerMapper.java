package com.tlyy.spider.mapper;

import com.tlyy.spider.entity.Answer;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author LeiDongxing
 * created on 2020/2/13
 */
public interface AnswerMapper {
    @Insert("insert into t_answer(id, question_id, user_id, user_name, content, source_url, url)"
            + "values(#{id}, #{questionId},#{userId}, #{userName}, #{content}, #{sourceUrl}, #{url})")
    int insert(Answer answer);

    @Select("select id from t_answer")
    Set<Long> selectAllIds();
}
