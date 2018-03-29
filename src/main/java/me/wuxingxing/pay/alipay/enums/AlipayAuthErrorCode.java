package me.wuxingxing.pay.alipay.enums;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
public enum AlipayAuthErrorCode {
    AUTH_ERROR(3001, "认证失败"),                     ACCESS_TOKEN_ERROR(3002, "获取AccessToken失败"),
    ALIPAY_EXISTED(3003, "当前用户已绑定支付宝账户"),  ALIPAY_USER_ID_NULL(3004, "用户未绑定支付宝账户");

    private int code;
    private String msg;

    AlipayAuthErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getId() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
