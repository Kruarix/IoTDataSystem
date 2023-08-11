package com.yryun.system.scheduleTask;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling   //开启定时任务
@EnableAsync    //开启定时任务多线程
@Slf4j
public class ScheduleTask {

    /* Redis接口 */
    /*@Resource
    private RedisTemplate redisTemplate;*/




    int a = 0;
    @Async
    @Scheduled(cron = "* 0/1 * * * ?")
    public void test(){
        log.info("过了"+a++);
    }

}
