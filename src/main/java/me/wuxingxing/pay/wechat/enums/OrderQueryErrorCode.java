package me.wuxingxing.pay.wechat.enums;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
public enum OrderQueryErrorCode {

    ORDERNOTEXIST(500, "支付错误"), SYSTEMERROR(500, "未知错误");

    private int statusCode;

    private String message;

    private OrderQueryErrorCode() {
    }

    private OrderQueryErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }
}
