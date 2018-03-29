package me.wuxingxing.pay.alipay.enums;

public enum AlipayTradePayErrorCode {

    SYSTEM_ERROR(true),
    INVALID_PARAMETER(500, "参数无效"),
    ACCESS_FORBIDDEN(403, "无权限使用接口"),
    EXIST_FORBIDDEN_WORD(400, "订单信息中包含违禁词"),
    PARTNER_ERROR(400, "应用APP_ID填写错误"),
    TOTAL_FEE_EXCEED(400, "订单总金额超过限额"),
    PAYMENT_AUTH_CODE_INVALID(400, "支付授权码无效"),
    CONTEXT_INCONSISTENT(400, "交易信息被篡改"),
    TRADE_HAS_SUCCESS(400, "交易已被支付"),
    TRADE_HAS_CLOSE(400, "交易已经关闭"),
    BUYER_BALANCE_NOT_ENOUGH(400, "买家余额不足"),
    BUYER_BANKCARD_BALANCE_NOT_ENOUGH(400, "用户银行卡余额不足"),
    ERROR_BALANCE_PAYMENT_DISABLE(400, "余额支付功能关闭"),
    BUYER_SELLER_EQUAL(400, "买卖家不能相同"),
    TRADE_BUYER_NOT_MATCH(400, "交易买家不匹配"),
    BUYER_ENABLE_STATUS_FORBID(400, "买家状态非法"),
    PULL_MOBILE_CASHIER_FAIL(400, "唤起移动收银台失败"),
    MOBILE_PAYMENT_SWITCH_OFF(400, "用户的无线支付开关关闭"),
    PAYMENT_FAIL(400, "支付失败"),
    BUYER_PAYMENT_AMOUNT_DAY_LIMIT_ERROR(400, "买家付款日限额超限"),
    BEYOND_PAY_RESTRICTION(400, "商户收款额度超限"),
    BEYOND_PER_RECEIPT_RESTRICTION(400, "商户收款金额超过月限额"),
    BUYER_PAYMENT_AMOUNT_MONTH_LIMIT_ERROR(400, "买家付款月额度超限"),
    SELLER_BEEN_BLOCKED(400, "商家账号被冻结"),
    ERROR_BUYER_CERTIFY_LEVEL_LIMIT(400, "买家未通过人行认证"),
    PAYMENT_REQUEST_HAS_RISK(400, "支付有风险"),
    NO_PAYMENT_INSTRUMENTS_AVAILABLE(400, "没用可用的支付工具"),
    USER_FACE_PAYMENT_SWITCH_OFF(400, "用户当面付付款开关关闭"),
    INVALID_STORE_ID(500, "商户门店编号无效"),
    SUB_MERCHANT_CREATE_FAIL(500, "二级商户创建失败"),
    SUB_MERCHANT_TYPE_INVALID(500, "二级商户类型非法"),
    AGREEMENT_NOT_EXIST(500, "用户协议不存在"),
    AGREEMENT_INVALID(500, "用户协议失效"),
    AGREEMENT_STATUS_NOT_NORMAL(500, "用户协议状态非NORMAL"),
    MERCHANT_AGREEMENT_NOT_EXIST(500, "商户协议不存在"),
    MERCHANT_AGREEMENT_INVALID(500, "商户协议已失效"),
    MERCHANT_STATUS_NOT_NORMAL(500, "商户协议状态非正常状态");

    private int statusCode;

    private String message;

    private boolean orderQueryRequired = false;

    AlipayTradePayErrorCode(boolean orderQueryRequired) {
        this.orderQueryRequired = orderQueryRequired;
    }

    AlipayTradePayErrorCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    AlipayTradePayErrorCode(int statusCode, String message, boolean orderQueryRequired) {
        this.statusCode = statusCode;
        this.message = message;
        this.orderQueryRequired = orderQueryRequired;
    }

    public boolean isOrderQueryRequired() {
        return orderQueryRequired;
    }
}
