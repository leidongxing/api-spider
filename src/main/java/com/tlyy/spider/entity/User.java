package com.tlyy.spider.entity;

import lombok.Data;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
@Data
public class User {
    private String id;
    private String name;
    private String headLine;
    private Byte gender;
    private String url;
}
