package com.tlyy.spider.entity;

import lombok.Data;

import java.util.List;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
@Data
public class Answer {
    private Long id;
    private Long questionId;
    private String userId;
    private String userName;
    private String content;
    private String sourceUrl;
    private String url;
}