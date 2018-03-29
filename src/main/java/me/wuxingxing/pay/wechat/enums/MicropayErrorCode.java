package me.wuxingxing.pay.wechat.enums;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
public enum MicropayErrorCode {

    SYSTEMERROR(500, "接口返回错误-系统超时", true),
    PARAM_ERROR(500, "请求参数未按指引进行填写"),
    ORDERPAID(400, "订单号重复"),
    NOAUTH(500, "商户没有开通被扫支付权限"),
    AUTHCODEEXPIRE(400, "用户的条码已经过期"),
    NOTENOUGH(400, "用户的零钱余额不足"),
    NOTSUPORTCARD(400, "用户使用卡种不支持当前支付形式"),
    ORDERCLOSED(400, "该订单已关"),
    ORDERREVERSED(400, "当前订单已经被撤销"),
    BANKERROR(500, "银行端超时", true),
    USERPAYING(202, "该笔交易因为业务规则要求，需要用户输入支付密码", true),
    AUTH_CODE_ERROR(400, "请求参数未按指引进行填写"),
    AUTH_CODE_INVALID(400, "收银员扫描的不是微信支付的条码"),
    XML_FORMAT_ERROR(500, "XML格式错误"),
    REQUIRE_POST_METHOD(500, "未使用post传递参数"),
    SIGNERROR(500, "参数签名结果不正确"),
    LACK_PARAMS(500, "缺少必要的请求参数"),
    NOT_UTF8(500, "未使用指定编码格式"),
    BUYER_MISMATCH(400, "暂不支持同一笔订单更换支付方"),
    APPID_NOT_EXIST(500, "参数中缺少APPID"),
    MCHID_NOT_EXIST(500, "参数中缺少MCHID"),
    OUT_TRADE_NO_USED(500, "同一笔交易不能多次提交"),
    APPID_MCHID_NOT_MATCH(500, "appid和mch_id不匹配");

    private int statusCode;

    private String message;

    private boolean orderQueryRequired = false;

    MicropayErrorCode() {
    }

    MicropayErrorCode(boolean orderQueryRequired) {
        this.orderQueryRequired = orderQueryRequired;
    }

    MicropayErrorCode(int statusCode, String message) {
        this(statusCode, message, false);
    }

    MicropayErrorCode(int statusCode, String message, boolean orderQueryRequired) {
        this.statusCode = statusCode;
        this.message = message;
        this.orderQueryRequired = orderQueryRequired;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public boolean isOrderQueryRequired() {
        return orderQueryRequired;
    }

    //是否包含枚举项
    public static boolean contains(String name){
        //所有的枚举值
        MicropayErrorCode[] micropayErrorCodes = values();
        //遍历查找
        for(MicropayErrorCode mec : micropayErrorCodes){
            if(mec.name().equals(name)){
                return true;
            }
        }
        return false;
    }
}

