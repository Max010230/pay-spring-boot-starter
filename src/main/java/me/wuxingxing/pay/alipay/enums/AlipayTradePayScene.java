package me.wuxingxing.pay.alipay.enums;

public enum AlipayTradePayScene {

    BAR_CODE("bar_code"), WAVE_CODE("wave_code");

    private String value;

    AlipayTradePayScene(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
