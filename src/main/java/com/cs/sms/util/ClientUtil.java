package com.cs.sms.util;
/**短信验证工具类*/
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "spring.sms")
public class ClientUtil {

    private String accessKeyId;      //ID

    private String accessKeySecret;  //Secret

    private String endpoint;         //访问域名

    private String signName;         //短信签名

    private String templateCode;     //短信模板编码

    /**
     * 使用AK&SK初始化账号Client
     * @param phoneNumber
     * @param code
     * @return Client
     * @throws Exception
     */
    public void sendSmsCode(String phoneNumber,String code)throws Exception{
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = endpoint;

        Client client = new Client(config);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam("{\"code\":"+code+"}");

        client.sendSms(sendSmsRequest);
    }

}
