package com.zcy.rescue.passenger;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zouhx
 * @Description: 启动类
 * <p>
 * TODO 这里一定要排除这里的SpringBootConfiguration，因为我们已经自定义了DataSource，所以需要排序shardingjdbc设置的DataSource
 * @date 2019/12/28 下午6:33
 */
@EnableSwagger2Doc
@SpringBootApplication
@ComponentScan(basePackages = {"com.*"})
@EnableConfigurationProperties
public class PassengerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PassengerApplication.class, args);
    }

}
