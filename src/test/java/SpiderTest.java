import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tlyy.spider.entity.OriginalData;
import org.junit.Test;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
public class SpiderTest {
    @Test
    public void test() {
        String test="{\"data\":[{\"id\":252831295,\"type\":\"answer\",\"answer_type\":\"normal\",\"question\":{\"type\":\"question\",\"id\":54847944,\"title\":\"如何成为Apache的commiter？\",\"question_type\":\"normal\",\"created\":1484624245,\"updated_time\":1484624245,\"url\":\"https://www.zhihu.com/api/v4/questions/54847944\",\"relationship\":{}},\"author\":{\"id\":\"aa2d392108d8b620d88506f64c916ae5\",\"url_token\":\"interma\",\"name\":\"interma\",\"avatar_url\":\"https://pic2.zhimg.com/v2-82f23e14cfe24ea9b5324db0d33eb398_is.jpg\",\"avatar_url_template\":\"https://pic2.zhimg.com/v2-82f23e14cfe24ea9b5324db0d33eb398_{size}.jpg\",\"is_org\":false,\"type\":\"people\",\"url\":\"https://www.zhihu.com/api/v4/people/aa2d392108d8b620d88506f64c916ae5\",\"user_type\":\"people\",\"headline\":\"R.A.P\",\"badge\":[],\"gender\":1,\"is_advertiser\":false,\"is_privacy\":false},\"url\":\"https://www.zhihu.com/api/v4/answers/252831295\",\"is_collapsed\":false,\"created_time\":1509433800,\"updated_time\":1509433800,\"extras\":\"\",\"is_copyable\":true,\"content\":\"<p>我知道如下几个方法：</p><ol><li>找个以apache开源项目为工作内容的职位，如cloudera, databricks, 国内有huawei等。知名项目（如spark）的committer想不全职是很难的。</li><li>对于冷门点的项目，完全可以业余投入些时间：干点脏活，如写文档也是可以成为committer的，但得坚持。incubator中那么多项目，社区都很友善，愿意吸收外部committer。</li><li>积累点人脉，committer提名时是需要提携一下的，技术水平不是决定性因素。</li></ol><p class=\\\"ztext-empty-paragraph\\\"><br/></p><p>我参加的项目是<a href=\\\"https://link.zhihu.com/?target=http%3A//hawq.incubator.apache.org/\\\" class=\\\" wrap external\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\">Apache HAWQ</a>，目前也是apache committer了：<a href=\\\"https://link.zhihu.com/?target=http%3A//people.apache.org/committer-index.html%23I\\\" class=\\\" wrap external\\\" target=\\\"_blank\\\" rel=\\\"nofollow noreferrer\\\">ASF Committers by id</a> =&gt; interma</p><p>但深知自己技术上还差的很远，珍视这份荣誉，用它激励前进。</p><figure><noscript><img src=\\\"https://pic3.zhimg.com/50/v2-84dc5e5640e4d3b5751f4cbdfaa0183c_hd.jpg\\\" data-caption=\\\"\\\" data-rawwidth=\\\"294\\\" data-rawheight=\\\"648\\\" class=\\\"content_image\\\" width=\\\"294\\\"/></noscript><img src=\\\"data:image/svg+xml;utf8,&lt;svg xmlns=&#39;http://www.w3.org/2000/svg&#39; width=&#39;294&#39; height=&#39;648&#39;&gt;&lt;/svg&gt;\\\" data-caption=\\\"\\\" data-rawwidth=\\\"294\\\" data-rawheight=\\\"648\\\" class=\\\"content_image lazy\\\" width=\\\"294\\\" data-actualsrc=\\\"https://pic3.zhimg.com/50/v2-84dc5e5640e4d3b5751f4cbdfaa0183c_hd.jpg\\\"/></figure><p></p>\",\"relationship\":{\"upvoted_followees\":[]},\"ad_answer\":null},{\"id\":1011039313,\"type\":\"answer\",\"answer_type\":\"normal\",\"question\":{\"type\":\"question\",\"id\":54847944,\"title\":\"如何成为Apache的commiter？\",\"question_type\":\"normal\",\"created\":1484624245,\"updated_time\":1484624245,\"url\":\"https://www.zhihu.com/api/v4/questions/54847944\",\"relationship\":{}},\"author\":{\"id\":\"df1b0e88125de5402ee82f9c5c19e74c\",\"url_token\":\"lei-dong-xing\",\"name\":\"slark\",\"avatar_url\":\"https://pic2.zhimg.com/v2-ea71b8a09f9997a94a504bb813955b52_is.jpg\",\"avatar_url_template\":\"https://pic2.zhimg.com/v2-ea71b8a09f9997a94a504bb813955b52_{size}.jpg\",\"is_org\":false,\"type\":\"people\",\"url\":\"https://www.zhihu.com/api/v4/people/df1b0e88125de5402ee82f9c5c19e74c\",\"user_type\":\"people\",\"headline\":\"\",\"badge\":[],\"gender\":1,\"is_advertiser\":false,\"is_privacy\":false},\"url\":\"https://www.zhihu.com/api/v4/answers/1011039313\",\"is_collapsed\":false,\"created_time\":1581434010,\"updated_time\":1581434010,\"extras\":\"\",\"is_copyable\":true,\"content\":\"<p>努力学习</p>\",\"relationship\":{\"upvoted_followees\":[]},\"ad_answer\":null}],\"paging\":{\"is_end\":true,\"is_start\":true,\"next\":\"https://www.zhihu.com/api/v4/questions/54847944/answers?include=data%5B%2A%5D.content%3B%26sort_by%3Ddefault%26platform%3Ddesktop%5Cu0026limit%3D20%5Cu0026offset%3D20%5Cu0026platform%3Ddesktop%5Cu0026sort_by%3Ddefault&limit=5&offset=5\",\"previous\":\"https://www.zhihu.com/api/v4/questions/54847944/answers?include=data%5B%2A%5D.content%3B%26sort_by%3Ddefault%26platform%3Ddesktop%5Cu0026limit%3D20%5Cu0026offset%3D20%5Cu0026platform%3Ddesktop%5Cu0026sort_by%3Ddefault&limit=5&offset=0\",\"totals\":2}}";
        JSONObject jsonObject = JSON.parseObject(test);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArray.size();i++){
            OriginalData.Data data = JSON.toJavaObject(jsonArray.getJSONObject(i), OriginalData.Data.class);
            System.out.println(data.getAnswerId());
        }
    }

    @Test
    public void test1(){
        try{
            String url1=String.format("https://www.zhihu.com/api/v4/questions/%s/answers","368248830");
            String url2=String.format("?include=data[*].is_normal,admin_closed_comment,reward_info,is_collapsed,annotation_action,annotation_detail,collapse_reason,is_sticky,collapsed_by,suggest_edit,comment_count,can_comment,content,editable_content,voteup_count,reshipment_settings,comment_permission,created_time,updated_time,review_info,relevant_info,question,excerpt,relationship.is_authorized,is_author,voting,is_thanked,is_nothelp,is_labeled;data[*].mark_infos[*].url;data[*].author.follower_count,badge[*].topics&offset=%s&limit=20&sort_by=update","1");
            String url3 =URLEncoder.encode(url2,"UTF-8");
            System.out.println(url1+url3);
            //System.out.println(URLDecoder.decode("?include=data%5B*%5D.is_normal%2Cadmin_closed_comment%2Creward_info%2Cis_collapsed%2Cannotation_action%2Cannotation_detail%2Ccollapse_reason%2Cis_sticky%2Ccollapsed_by%2Csuggest_edit%2Ccomment_count%2Ccan_comment%2Ccontent%2Ceditable_content%2Cvoteup_count%2Creshipment_settings%2Ccomment_permission%2Ccreated_time%2Cupdated_time%2Creview_info%2Crelevant_info%2Cquestion%2Cexcerpt%2Crelationship.is_authorized%2Cis_author%2Cvoting%2Cis_thanked%2Cis_nothelp%2Cis_labeled%3Bdata%5B*%5D.mark_infos%5B*%5D.url%3Bdata%5B*%5D.author.follower_count%2Cbadge%5B*%5D.topics&offset={}&limit=20&sort_by=update","UTF-8"));
        }catch(Exception e){

        }
    }


    @Test
    public void test2(){
        String htmlStr="<p>我知道如下几个方法：</p><ol><li>找个以apache开源项目为工作内容的职位，如cloudera, databricks, 国内有huawei等。知名项目（如spark）的committer想不全职是很难的。</li><li>对于冷门点的项目，完全可以业余投入些时间：干点脏活，如写文档也是可以成为committer的，但得坚持。incubator中那么多项目，社区都很友善，愿意吸收外部committer。</li><li>积累点人脉，committer提名时是需要提携一下的，技术水平不是决定性因素。</li></ol><p class=\"ztext-empty-paragraph\"><br/></p><p>我参加的项目是<a href=\"https://link.zhihu.com/?target=http%3A//hawq.incubator.apache.org/\" class=\" wrap external\" target=\"_blank\" rel=\"nofollow noreferrer\">Apache HAWQ</a>，目前也是apache committer了：<a href=\"https://link.zhihu.com/?target=http%3A//people.apache.org/committer-index.html%23I\" class=\" wrap external\" target=\"_blank\" rel=\"nofollow noreferrer\">ASF Committers by id</a> =&gt; interma</p><p>但深知自己技术上还差的很远，珍视这份荣誉，用它激励前进。</p><figure><noscript><img src=\"https://pic3.zhimg.com/50/v2-84dc5e5640e4d3b5751f4cbdfaa0183c_hd.jpg\" data-caption=\"\" data-rawwidth=\"294\" data-rawheight=\"648\" class=\"content_image\" width=\"294\"/></noscript><img src=\"data:image/svg+xml;utf8,&lt;svg xmlns=&#39;http://www.w3.org/2000/svg&#39; width=&#39;294&#39; height=&#39;648&#39;&gt;&lt;/svg&gt;\" data-caption=\"\" data-rawwidth=\"294\" data-rawheight=\"648\" class=\"content_image lazy\" width=\"294\" data-actualsrc=\"https://pic3.zhimg.com/50/v2-84dc5e5640e4d3b5751f4cbdfaa0183c_hd.jpg\"/></figure><p></p>";
        String textStr ="";
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        try{
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签
            textStr = htmlStr;
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println(textStr);
    }
}
