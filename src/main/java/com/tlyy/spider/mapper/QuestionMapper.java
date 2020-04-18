package com.tlyy.spider.mapper;

import com.tlyy.spider.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author LeiDongxing
 * created on 2020/2/13
 */
public interface QuestionMapper {
    @Insert("insert into t_zhihu_question(id, title, url, answer_num)"
            + "values(#{id}, #{title}, #{url}, #{answerNum})")
    int insert(Question question);

    @Select("select id from t_zhihu_question")
    Set<Long> selectAllIds();
}
