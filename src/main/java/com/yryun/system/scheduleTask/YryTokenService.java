package com.yryun.system.scheduleTask;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.yryun.system.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling   //开启定时任务
@EnableAsync    //开启定时任务多线程
@Slf4j
public class YryTokenService {

    /* YryToken */
    private String yryToken;
    private boolean tokenRetryEnabled = true;


    /* 请求接口 */
    RestTemplate restTemplate = new RestTemplate();

    /* 获取有人云用户Token */
    @Async
    @Scheduled(fixedDelay = 60000)
    //@PostConstruct
    public void requestYryToken() {
        if (!tokenRetryEnabled) {
            log.info("Token重试被阻止");
            return;
        }

        String url = "https://openapi.mp.usr.cn/usrCloud/V6/user/getAuthToken";
        String appKey = "w9fLdzBm";
        String appSecret = "vpzihxltq43hus3umhf74kljvuao9t0r";
        JSONObject json = new JSONObject();
        json.put("appKey", appKey);
        json.put("appSecret", appSecret);


        String body = restTemplate.postForObject(url, json, String.class);
        JSONObject result = JSON.parseObject(body);
        assert result != null;

        int status = result.getIntValue("status");

        if (status == Constants.CustomStatus.SERVER_DEFENSE_TRIGGERED) {
            String info = result.getString("info");
            log.warn("请求未成功，原因：" + info);
            // 设置开关，禁止Token重试一分钟
            disableTokenRetryForOneMinute();
        } else {
            JSONObject data = result.getJSONObject("data");
            if (data != null) {
                this.yryToken = data.getString("X-Access-Token");
                log.info("Token更新成功：" + yryToken);
            } else {
                log.warn("未找到有效的Token信息");
            }
        }

    }

    // 获取已请求的Token
    public String getToken() {
        return this.yryToken;
    }

    // 禁止Token重试一分钟
    private void disableTokenRetryForOneMinute() {
        tokenRetryEnabled = false;
        log.info("Token重试被阻止一分钟");

        // 一分钟后恢复开关状态
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        tokenRetryEnabled = true;
                        log.info("Token重试开关已恢复");
                    }
                },
                60000 // 一分钟
        );
    }



}
