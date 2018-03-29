package me.wuxingxing.pay.wechat.service;

import com.github.binarywang.wxpay.bean.request.WxPayMicropayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayOrderReverseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import me.wuxingxing.pay.wechat.enums.MicropayErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
public class WechatTradeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatTradeService.class);

    private WxPayService wxPayService;

    /**
     * 发送micropay支付接口
     * */
    public WxPayMicropayResult micropay(WxPayMicropayRequest request) {

        WxPayMicropayResult result = null;
        try {
            result = wxPayService.micropay(request);
        } catch (WxPayException e) {
            LOGGER.info("微信订单支付异常-OutTradeNo: {}", request.getOutTradeNo());
            String errCode = e.getErrCode();

            WxPayMicropayResult exceptionResult = new WxPayMicropayResult();
            exceptionResult.setErrCode(errCode);
            exceptionResult.setOutTradeNo(request.getOutTradeNo());

            if(!MicropayErrorCode.contains(errCode)) {
//                paymentCallbackHandler.onPaymentFailure(exceptionResult);
                return exceptionResult;
            }

            MicropayErrorCode errorCode = MicropayErrorCode.valueOf(errCode);
            //未知错误
            if (errorCode.isOrderQueryRequired()) {
//                paymentCallbackHandler.onPaymentUnknown(exceptionResult);
            } else {
                //支付失败
//                paymentCallbackHandler.onPaymentFailure(exceptionResult);
            }
            return exceptionResult;
        }

        // 判断第二级返回结果无逻辑错误支付成功
        if(StringUtils.isBlank(result.getErrCode())) {
            LOGGER.info("微信订单支付成功-OutTradeNo: {}", result.getOutTradeNo());
//            paymentCallbackHandler.onPaymentSuccess(result);
        }
        return result;
    }

    /**
     * 微信刷卡支付查询订单状态
     * @param transactionId
     * @param outTradeNo 商户订单号
     * @return WxPayOrderQueryResult
     */
    public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) {
        try {
            return wxPayService.queryOrder(transactionId, outTradeNo);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭微信订单
     * */
    public WxPayOrderCloseResult closeOrder(String outTradeNo){
        try {
            return wxPayService.closeOrder(outTradeNo);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 撤销订单
     * @param request
     * @return
     */
    public WxPayOrderReverseResult reverseOrder(WxPayOrderReverseRequest request) {
        try {
            return wxPayService.reverseOrder(request);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统一下单
     * @param request
     * @return
     */
    public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) {
        try {
            return wxPayService.unifiedOrder(request);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下载账单
     * @param billDate   对账单日期 bill_date	下载对账单的日期，格式：20140603
     * @param billType   账单类型	bill_type	ALL，返回当日所有订单信息，默认值，SUCCESS，返回当日成功支付的订单，REFUND，返回当日退款订单
     * @param tarType    压缩账单	tar_type	非必传参数，固定值：GZIP，返回格式为.gzip的压缩包账单。不传则默认为数据流形式。
     * @param deviceInfo 设备号	device_info	非必传参数，终端设备号
     * @return
     */
    public WxPayBillResult downloadBill(String billDate, String billType, String tarType, String deviceInfo) {
        try {
            return wxPayService.downloadBill(billDate, billType, tarType, deviceInfo);
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setWxPayService(WxPayService wxPayService) {
        this.wxPayService = wxPayService;
    }
}

