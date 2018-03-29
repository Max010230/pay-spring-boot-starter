package me.wuxingxing.pay.alipay.config;

import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
@ConfigurationProperties(prefix = "alipay")
public class AlipayProperties {

    private String host;

    private String appId;

    /**
     * 签约的支付宝账号对应的支付宝唯一用户号
     */
    private String pid;

    /**
     * 应用私钥
     */
    private PrivateKey privateKey;

    /**
     * 支付宝公钥
     */
    private PublicKey alipayPublicKey;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(Resource privateKey) throws IOException {

        try (PEMParser parser = new PEMParser(new InputStreamReader(privateKey.getInputStream()))) {
            PEMKeyPair pemKeyPair = (PEMKeyPair) parser.readObject();
            JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
            this.privateKey = keyConverter.getPrivateKey(pemKeyPair.getPrivateKeyInfo());
        }
    }

    public PublicKey getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public void setAlipayPublicKey(Resource alipayPublicKey) throws IOException {
        try (PEMParser parser = new PEMParser(new InputStreamReader(alipayPublicKey.getInputStream()))) {
            SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) parser.readObject();
            JcaPEMKeyConverter keyConverter = new JcaPEMKeyConverter();
            this.alipayPublicKey = keyConverter.getPublicKey(subjectPublicKeyInfo);
        }
    }
}
