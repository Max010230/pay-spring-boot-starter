package me.wuxingxing.pay.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConstants;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import me.wuxingxing.pay.alipay.config.AlipayProperties;
import me.wuxingxing.pay.alipay.enums.AlipayAuthErrorCode;
import me.wuxingxing.pay.alipay.exception.AlipayException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付宝用户认证相关Service
 * @author xingxing.wu
 * @date 2018/3/29
 */
public class AlipayAuthService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayTradeService.class);

    private AlipayProperties properties;

    private AlipayClient alipayClient;

    public AlipayAuthService(AlipayProperties properties, AlipayClient alipayClient) {
        this.properties = properties;
        this.alipayClient = alipayClient;
    }

    public void setProperties(AlipayProperties properties) {
        this.properties = properties;
    }

    /**
     * 根据authCode获得AccessToken
     * @param authCode authCode
     * @param alipayUserId alipayUserId
     * @return AccessToken
     */
    public String userAuth(String authCode, String alipayUserId) {
        //创建API对应的request类
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setGrantType("authorization_code");
        request.setCode(authCode);
        //通过alipayClient调用API，获得对应的response类
        AlipaySystemOauthTokenResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new AlipayException(504, AlipayAuthErrorCode.ACCESS_TOKEN_ERROR.getId(),
                    AlipayAuthErrorCode.ACCESS_TOKEN_ERROR.getMsg());
        }
        //根据response中的结果继续业务逻辑处理
        if(response == null) {
            return "";
        }
        if(!response.getUserId().equals(alipayUserId)) {
            return "";
        }
        return response.getAccessToken();
    }

    /**
     * 使用accessToken获取用户信息
     * @param accessToken token
     * @return AlipayUserUserinfoShareResponse
     */
    public AlipayUserUserinfoShareResponse getAlipayUserInfo(String accessToken) {
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.user.userinfo.share
        AlipayUserUserinfoShareRequest request = new AlipayUserUserinfoShareRequest();

        //授权类接口执行API调用时需要带上accessToken
        AlipayUserUserinfoShareResponse response = null;
        try {
            response = alipayClient.execute(request, accessToken);
        } catch (AlipayApiException e) {
            LOGGER.error("支付宝-使用accessToken获取用户信息失败", e);
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 支付宝授权加签
     * @return 签名后字符串
     */
    public String rsaSign() {
        Map<String, String> params = new HashMap<>();
        // 服务接口名称， 固定值
        params.put("apiname", "com.alipay.account.auth");
        params.put("method", "alipay.open.auth.sdk.code.get");
        params.put("app_id", properties.getAppId());
        // 商户类型标识， 固定值
        params.put("app_name", "mc");
        // 商户签约拿到的pid，如：2088102123816631
        params.put("pid", properties.getPid());
        // 业务类型， 固定值
        params.put("biz_type", "openservice");
        // 产品码， 固定值
        params.put("product_id", "APP_FAST_LOGIN");
        // 授权范围， 固定值
        params.put("scope", "kuaijie");
        // 商户唯一标识，如：kkkkk091125
        params.put("target_id", new Date().toString());
        // 授权类型， 固定值
        params.put("auth_type", "AUTHACCOUNT");
        // 签名类型
        params.put("sign_type", AlipayConstants.SIGN_TYPE_RSA2);

        try {
            byte[] privateKeyBytes = properties.getPrivateKey().getEncoded();
            String sign = AlipaySignature.rsaSign(AlipaySignature.getSignContent(params), Base64.encodeBase64String(privateKeyBytes),
                    AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
            return AlipaySignature.getSignContent(params)+"&sign="+ URLEncoder.encode(sign, "UTF-8");
        } catch (Exception e) {
            LOGGER.error("授权签名失败", e);
            throw new AlipayException("授权签名失败");
        }
    }

    public boolean rsaCheckV1(Map<String, String> params) {

        byte[] alipaypublicKey = properties.getAlipayPublicKey().getEncoded();
        try {
            return AlipaySignature.rsaCheckV1(params, Base64.encodeBase64String(alipaypublicKey),
                    AlipayConstants.CHARSET_UTF8, AlipayConstants.SIGN_TYPE_RSA2);
        } catch (AlipayApiException e) {
            LOGGER.error("异步返回结果验签失败", e);
        }
        return false;
    }
}

