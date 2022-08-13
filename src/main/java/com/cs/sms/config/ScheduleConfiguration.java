package com.cs.sms.config;

import com.alibaba.fastjson.JSONObject;
import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 定时任务配置类
 * 实现库存预警
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduleConfiguration {
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private EmailUtil emailUtil;

    @Bean
    public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
        //创建一个以固定频率执行的线程池
        ScheduledThreadPoolExecutor scheduledThreadPool =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        //向线程池中扔1个任务按照某种频率来执行
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                List<GoodsListVO> lists = goodsMapper.list();
                if(lists != null){
                    for(GoodsListVO list : lists){
                        if(list.getCurrentStock() <= list.getLowLimitStock()){
                            String to = "1539816041@qq.com"; // 需要发送预警信息的邮箱地址
                            String title = "库存预警";
                            String content =  list.getName() + "的库存已达到预警值，当前库存为：" + list.getCurrentStock() + ".请及时补足.";
                            emailUtil.sendMessage(to, title, content);
                        }
                    }

                }

            }
        }, 0, 60*60*24, TimeUnit.SECONDS);//每天进行一次库存预警
        return scheduledThreadPool;
    }

}
