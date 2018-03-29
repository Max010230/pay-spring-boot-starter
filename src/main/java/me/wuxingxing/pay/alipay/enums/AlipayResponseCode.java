package me.wuxingxing.pay.alipay.enums;

public enum AlipayResponseCode{

    SUCCESS("10000"),
    WAITING_USER_PAYMENT("10003"),
    SERVICE_UNAVAILABLE("20000"),
    INSUFFICIENT_AUTHORIZATION("20001"),
    MISSING_REQUIRED_PARAMETER("40001"),
    INVALID_PARAMETER("40002"),
    SERVICE_PROCESSING_FAILED("40004"),
    INSUFFICIENT_PERMISSIONS("40006");

    private final String code;

    AlipayResponseCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return code;
    }
}
