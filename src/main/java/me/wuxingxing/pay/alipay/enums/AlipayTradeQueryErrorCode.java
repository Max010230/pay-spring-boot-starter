package me.wuxingxing.pay.alipay.enums;

public enum AlipayTradeQueryErrorCode{

    SYSTEM_ERROR, INVALID_PARAMETER(500, "无效的参数"), TRADE_NOT_EXIST;

    private int status;

    private String message;

    AlipayTradeQueryErrorCode() {
    }

    private AlipayTradeQueryErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
