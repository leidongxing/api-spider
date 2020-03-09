package com.tlyy.spider.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tlyy.spider.dao.MemoryContainer;
import com.tlyy.spider.entity.Answer;
import com.tlyy.spider.entity.OriginalData;
import com.tlyy.spider.entity.Question;
import com.tlyy.spider.entity.User;
import com.tlyy.spider.util.Constant;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tlyy.spider.util.Constant.*;

/**
 * @author LeiDongxing
 * created on 2020/2/11
 */
@Component
@Slf4j
public class GetOriginalDataTask {
    @Autowired
    private MemoryContainer memoryContainer;

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .connectionPool(new ConnectionPool())
            .build();

    public void run(String questionId) {
        Question question = doGet(BASE_URL1 + questionId + BASE_URL2 + DEFAULT_OFFSET + BASE_URL3);
        for (int i = 0; i < question.getAnswerNum() / 20 + 1; i++) {
            try {
                TimeUnit.SECONDS.sleep(3);
                doGetAsync(BASE_URL1 + questionId + BASE_URL2 + i * 20 + BASE_URL3);
            } catch (InterruptedException e) {
                log.error("response failure, message: {}", JSON.toJSON(e));
            }
        }
    }

    private Question doGet(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            return parseQuestion(response.body().string());
        } catch (Exception e) {
            log.error("response failure, url: {}, message: {}", url, JSON.toJSON(e));
        }
        return new Question();
    }

    private void doGetAsync(String url) {
        Request request = new Request.Builder().url(url).build();
        Call call = CLIENT.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                log.info("response success, url: {}, message: {}", url, response);
                parse(response.body().string());
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                log.error("response failure, url: {}, message: {}", url, JSON.toJSON(e));
            }
        });
    }

    private void parse(String response) {
        JSONObject jsonObject = JSON.parseObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        JSONObject pageObject = jsonObject.getJSONObject("paging");
        OriginalData.Data data = JSON.toJavaObject(dataArray.getJSONObject(0), OriginalData.Data.class);
        OriginalData.Page page = JSON.toJavaObject(pageObject, OriginalData.Page.class);
        for (int i = 1; i < dataArray.size(); i++) {
            data = JSON.toJavaObject(dataArray.getJSONObject(i), OriginalData.Data.class);
            Answer answer = parseAnswer(data);
            memoryContainer.addAnswer(answer);
            memoryContainer.addUser(parseUser(data.getAuthor()));
        }
    }

    private Question parseQuestion(String response) {
        JSONObject jsonObject = JSON.parseObject(response);
        JSONArray dataArray = jsonObject.getJSONArray("data");
        JSONObject pageObject = jsonObject.getJSONObject("paging");
        OriginalData.Data data = JSON.toJavaObject(dataArray.getJSONObject(0), OriginalData.Data.class);
        OriginalData.Page page = JSON.toJavaObject(pageObject, OriginalData.Page.class);

        Question question = new Question();
        question.setId(data.getQuestion().getId());
        question.setTitle(data.getQuestion().getTitle());
        question.setUrl(Constant.QUESTION_URL.replace("{}", String.valueOf(question.getId())));
        question.setAnswerNum(page.getTotals());

        memoryContainer.addQuestion(question);
        return question;
    }

    private Answer parseAnswer(OriginalData.Data data) {
        Answer answer = new Answer();
        answer.setId(data.getAnswerId());
        answer.setQuestionId(data.getQuestion().getId());
        answer.setUserId(data.getAuthor().getId());
        answer.setUserName(data.getAuthor().getName());
        answer.setContent(parseHtml(data.getContent()));
        answer.setSourceUrl("");
        answer.setUrl(String.format(Constant.ANSWER_URL, data.getQuestion().getId(), data.getAnswerId()));
        return answer;
    }

    private User parseUser(OriginalData.OriginalAuthor author) {
        User user = new User();
        user.setId(author.getId());
        user.setName(author.getName());
        user.setHeadLine(author.getHeadline());
        user.setGender(author.getGender());
        user.setUrl(Constant.USER_URL.replace("{}", author.getUrlToken()));
        return user;
    }


    private String parseHtml(String htmlStr) {
        try {
            String regEx_html = "<[^>]+>";
            Pattern phtml = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            Matcher mhtml = phtml.matcher(htmlStr);
            htmlStr = mhtml.replaceAll("");
            return htmlStr;
        } catch (Exception e) {
            log.error("parse content failure, message: {}", JSON.toJSON(e));
        }
        return "";
    }
}
