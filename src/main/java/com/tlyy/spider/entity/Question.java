package com.tlyy.spider.entity;

import lombok.Data;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
@Data
public class Question {
    private Long id;
    private String title;
    private String url;
    private Long answerNum;
}
