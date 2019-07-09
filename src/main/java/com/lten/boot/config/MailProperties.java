package com.lten.boot.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author lijinbao
 * @version 1.0
 * @date 2019/3/28 15:54
 */
@Data
@Component
@PropertySource(value = "classpath:mail.properties",ignoreResourceNotFound = true)
public class MailProperties {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.smtp.auth}")
    private Boolean smtpAuth;

    @Value("${mail.from}")
    private String mailFrom;

    @Value("${mail.smtp.starttls-enable}")
    private Boolean smtpStarttlsEnable;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;
}
