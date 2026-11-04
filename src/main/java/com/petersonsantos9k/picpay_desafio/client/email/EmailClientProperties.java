package com.petersonsantos9k.picpay_desafio.client.email;

import com.petersonsantos9k.picpay_desafio.client.Properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "integration.email")
@Getter
@Setter
public class EmailClientProperties extends Properties {
    private String sendEmailPath;
}
