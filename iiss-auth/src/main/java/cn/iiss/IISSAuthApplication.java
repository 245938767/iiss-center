package cn.iiss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import cn.iiss.common.security.annotation.EnableRyFeignClients;

/**
 * 认证授权中心
 *
 */
@EnableRyFeignClients
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class IISSAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(IISSAuthApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  认证授权中心启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}
