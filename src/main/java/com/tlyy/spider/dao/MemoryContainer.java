package com.tlyy.spider.dao;

import com.alibaba.fastjson.JSON;
import com.tlyy.spider.entity.Answer;
import com.tlyy.spider.entity.Question;
import com.tlyy.spider.entity.User;
import com.tlyy.spider.mapper.AnswerMapper;
import com.tlyy.spider.mapper.QuestionMapper;
import com.tlyy.spider.mapper.UserMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
@Slf4j
@Getter
@Component
public class MemoryContainer {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private AnswerMapper answerMapper;


    private static Set<String> userContainer = new HashSet<>();
    private static Set<Long> questionContainer = new HashSet<>();
    private static Set<Long> answerContainer = new HashSet<>();
    private static ReentrantReadWriteLock userLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock questionLock = new ReentrantReadWriteLock();
    private static ReentrantReadWriteLock answerLock = new ReentrantReadWriteLock();

    public void init() {
        userContainer = userMapper.selectAllIds();
        questionContainer = questionMapper.selectAllIds();
        answerContainer = answerMapper.selectAllIds();
    }

    public void addUser(User user) {
        if (!hasUser(user)) {
            userLock.writeLock().lock();
            try {
                userContainer.add(user.getId());
            } finally {
                userLock.writeLock().unlock();
                userMapper.insert(user);
                log.info("add user:{}", JSON.toJSON(user));
            }
        }
    }

    public void addQuestion(Question question) {
        if (!hasQuestion(question)) {
            questionLock.writeLock().lock();
            try {
                questionContainer.add(question.getId());
            } finally {
                questionLock.writeLock().unlock();
                questionMapper.insert(question);
                log.info("add question:{}", JSON.toJSON(question));
            }
        }
    }

    public void addAnswer(Answer answer) {
        if (!hasAnswer(answer)) {
            answerLock.writeLock().lock();
            try {
                answerContainer.add(answer.getId());
            } finally {
                answerLock.writeLock().unlock();
                answerMapper.insert(answer);
                log.info("add answer:{}", JSON.toJSON(answer));
            }
        }
    }

    private Boolean hasUser(User user) {
        userLock.readLock().lock();
        try {
            return userContainer.contains(user.getId());
        } finally {
            userLock.readLock().unlock();
        }
    }

    public Boolean hasQuestion(Question question) {
        try {
            questionLock.readLock().lock();
            return questionContainer.contains(question.getId());
        } finally {
            questionLock.readLock().unlock();
        }
    }

    private Boolean hasAnswer(Answer answer) {
        try {
            answerLock.readLock().lock();
            return answerContainer.contains(answer.getId());
        } finally {
            answerLock.readLock().unlock();
        }
    }
}

