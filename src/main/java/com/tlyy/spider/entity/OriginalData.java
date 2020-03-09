package com.tlyy.spider.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
@Data
public class OriginalData {
    @JSONField(name = "ad_info")
    private String adInfo;
    private String data;
    private String paging;

    @lombok.Data
    public static class Data {
        @JSONField(name = "id")
        private Long answerId;

        @JSONField(name = "question")
        private String questionString;
        private OriginalQuestion question;

        @JSONField(name = "author")
        private String authorString;
        private OriginalAuthor author;

        private String content;

        @JSONField(name = "is_copyable")
        private Boolean isCollapsed;
        private String questionType;
//        @JSONField(name = "created_time")
//        private Long createdTime;
//        @JSONField(name = "updated_time")
//        private Long updatedTime;
//        private String extras;
//        @JSONField(name = "is_copyable")
//        private Boolean isCopyable;

//        private Object relationship;
//        @JSONField(name = "ad_answer")
//        private String adAnswer;
    }

    @lombok.Data
    public static class OriginalQuestion {
        private String type;
        private Long id;
        private String title;
        private String questionType;
        private Long created;
        @JSONField(name = "updated_time")
        private Long updatedTime;
        private String url;
        private String relationship;
    }


    @lombok.Data
    public static class OriginalAuthor {
        private String id;
        @JSONField(name = "url_token")
        private String urlToken;
        private String name;
        @JSONField(name = "avatar_url")
        private String avatarUrl;
        @JSONField(name = "avatar_url_template")
        private String avatarUrlTemplate;
        @JSONField(name = "is_org")
        private Boolean isOrg;
        private String type;
        private String url;
        @JSONField(name = "url_type")
        private String urlType;
        private String headline;
        private String[] badge;
        private Byte gender;
        @JSONField(name = "is_advertiser")
        private Boolean isAdvertiser;
        @JSONField(name = "is_privacy")
        private Boolean isPrivacy;
    }

    @lombok.Data
    public static class Page {
        @JSONField(name = "is_end")
        private Boolean isEnd;
        @JSONField(name = "is_start")
        private Boolean isStart;
        private String next;
        private String previous;
        private Long totals;
    }
}
