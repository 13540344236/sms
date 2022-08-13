package com.cs.sms.tests;

import com.cs.sms.mapper.GoodsMapper;
import com.cs.sms.pojo.vo.GoodsListVO;
import com.cs.sms.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

@Slf4j
@SpringBootTest
public class MailTest {
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private GoodsMapper goodsMapper;

    @Test
    void contextLoads() {
    }

    @Test
    void sendStringEmail() {
        // 测试文本邮件发送（无附件）
        List<GoodsListVO> lists = goodsMapper.list();
        for(GoodsListVO list : lists){
            System.out.println(list.getCurrentStock());
            log.debug("1111111");
            log.debug("2222222");
            String to = "1539816041@qq.com"; // 需要发送预警信息的邮箱地址
            String title = "库存预警";
            String content =  list.getName() + "的库存已达到预警值，当前库存为：" + list.getCurrentStock() + ".请及时补足.";
            emailUtil.sendMessage(to, title, content);

        }
    }

//    @Test
//    void sendFileEmail() {
//        // 测试单个附件邮件发送
//        String to = "135472099@qq.com"; // 这是个假邮箱，写成你自己的邮箱地址就可以
//        String title = "单个附件邮件发送测试";
//        String content = "单个附件邮件发送测试";
//        File file = new File("D:\\GCH\\Typora\\Linux中常用的查看系统相关信息命令.md");
//        emailUtil.sendMessageCarryFile(to, title, content, file);
//    }
//
//    @Test
//    void sendFilesEmail() {
//        // 测试多个附件邮件发送
//        String to = "135472099@qq.com"; // 这是个假邮箱，写成你自己的邮箱地址就可以
//        String title = "多个附件邮件发送测试";
//        String content = "多个附件邮件发送测试";
//        File[] files = new File[2];
//        files[0] = new File("C:\\Users\\root\\Desktop\\配置邮箱\\1.png");
//        files[1] = new File("C:\\Users\\root\\Desktop\\配置邮箱\\2.png");
//        emailUtil.sendMessageCarryFile(to, title, content, files);
//    }

}
