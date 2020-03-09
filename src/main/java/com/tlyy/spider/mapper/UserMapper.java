package com.tlyy.spider.mapper;

import com.tlyy.spider.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Set;

/**
 * @author LeiDongxing
 * created on 2020/2/13
 */
public interface UserMapper {
    @Insert("insert into t_user(id, name, head_line, gender, url)"
            + "values(#{id}, #{name}, #{headLine}, #{gender}, #{url})")
    int insert(User user);

    @Select("select id from t_user")
    Set<String> selectAllIds();
}
