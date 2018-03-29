package me.wuxingxing.pay.alipay.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.DefaultAlipayClient;
import me.wuxingxing.pay.alipay.service.AlipayAuthService;
import me.wuxingxing.pay.alipay.service.AlipayTradeService;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
@Configuration
@EnableConfigurationProperties(AlipayProperties.class)
@ConditionalOnProperty(prefix = "alipay", name = { "host", "app-id" })
public class AlipayAutoConfiguration {

    @Autowired
    private AlipayProperties properties;

    @Bean
    public AlipayTradeService alipayTradeService() {

        byte[] privateKeyBytes = properties.getPrivateKey().getEncoded();
        byte[] AlipayPublicKeyBytes = properties.getAlipayPublicKey().getEncoded();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(properties.getHost() + "/gateway.do",
                properties.getAppId(), Base64.encodeBase64String(privateKeyBytes), AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8, Base64.encodeBase64String(AlipayPublicKeyBytes), AlipayConstants.SIGN_TYPE_RSA2);

        return new AlipayTradeService(properties, alipayClient);
    }

    @Bean
    public AlipayAuthService alipayAuthService() {

        byte[] privateKeyBytes = properties.getPrivateKey().getEncoded();
        byte[] AlipayPublicKeyBytes = properties.getAlipayPublicKey().getEncoded();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(properties.getHost() + "/gateway.do",
                properties.getAppId(), Base64.encodeBase64String(privateKeyBytes), AlipayConstants.FORMAT_JSON,
                AlipayConstants.CHARSET_UTF8, Base64.encodeBase64String(AlipayPublicKeyBytes), AlipayConstants.SIGN_TYPE_RSA2);

        return new AlipayAuthService(properties, alipayClient);
    }
}
