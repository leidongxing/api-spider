package com.tlyy.spider.util;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
public class Constant {
    public static final String BASE_URL1 = "https://www.zhihu.com/api/v4/questions/";
    public static final String BASE_URL2 = "/answers?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_labeled%3Bdata%5B*%5D.mark_infos%5B*%5D.url%3Bdata%5B*%5D.author.follower_count%2Cbadge%5B*%5D.topics&offset=";
    public static final String BASE_URL3 ="&limit=20&sort_by=updated";
    public static final String DEFAULT_OFFSET="0";

    public static final String ANSWER_URL = "https://www.zhihu.com/question/%s/answer/%s";
    public static final String QUESTION_URL = "https://www.zhihu.com/question/{}";
    public static final String USER_URL = "https://www.zhihu.com/people/{}";
}
