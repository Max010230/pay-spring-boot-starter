package me.wuxingxing.pay.alipay.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import me.wuxingxing.pay.alipay.config.AlipayProperties;
import me.wuxingxing.pay.alipay.enums.AlipayTradeErrorCode;
import me.wuxingxing.pay.alipay.exception.AlipayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 支付宝交易Service
 * @author xingxing.wu
 * @date 2018/3/29
 */
public class AlipayTradeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayTradeService.class);

    private AlipayProperties properties;

    private AlipayClient alipayClient;

    public void setProperties(AlipayProperties properties) {
        this.properties = properties;
    }

    public AlipayTradeService(AlipayProperties properties, AlipayClient alipayClient) {
        this.properties = properties;
        this.alipayClient = alipayClient;
    }

    /**
     * 支付宝条码支付
     * @param tradePayModel AlipayTradePayModel
     * @return AlipayTradePayResponse
     */
    public AlipayTradePayResponse pay(AlipayTradePayModel tradePayModel) throws AlipayApiException {

        AlipayTradePayRequest request = new AlipayTradePayRequest(); //创建API对应的request类
        request.setBizModel(tradePayModel); //设置业务参数

        return alipayClient.execute(request);  //通过alipayClient调用API，获得对应的response类
    }

    /**
     * 交易查询
     * @param outTradeNo
     * @return
     */
    public AlipayTradeQueryResponse queryByOutTradeNo(String outTradeNo) {
        AlipayTradeQueryModel request = new AlipayTradeQueryModel();
        request.setOutTradeNo(outTradeNo);
        return query(request);
    }

    /**
     * 交易查询
     * @param tradeNo
     * @return
     */
    public AlipayTradeQueryResponse queryByTradeNo(String tradeNo) {
        AlipayTradeQueryModel request = new AlipayTradeQueryModel();
        request.setTradeNo(tradeNo);
        return query(request);
    }

    /**
     * 转账查询
     * @param outBizNo 商户转账唯一订单号
     * @return
     */
    public AlipayFundTransOrderQueryResponse queryByOutBizNo(String outBizNo) {
        AlipayFundTransOrderQueryModel request = new AlipayFundTransOrderQueryModel();
        request.setOutBizNo(outBizNo);
        return query(request);
    }

    /**
     * 转账查询
     * @param orderId 支付宝转账单据号
     * @return
     */
    public AlipayFundTransOrderQueryResponse queryByOrderId(String orderId) {
        AlipayFundTransOrderQueryModel request = new AlipayFundTransOrderQueryModel();
        request.setOrderId(orderId);
        return query(request);
    }

    /**
     * 交易查询
     * @param tradeQueryModel
     * @return
     */
    private AlipayTradeQueryResponse query(AlipayTradeQueryModel tradeQueryModel) {

        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(tradeQueryModel);
        AlipayTradeQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            LOGGER.info("支付宝订单支付查询返回： \n" + response.getBody());
        } catch (AlipayApiException e) {
            LOGGER.error("支付宝订单支付查询失败", e);
            throw new AlipayException(AlipayTradeErrorCode.TRADE_QUERY_ERROR.getId(), "trade error");
        }
        return response;
    }

    /**
     * 转账查询
     * @param alipayFundTransOrderQueryModel
     * @return
     */
    private AlipayFundTransOrderQueryResponse query(AlipayFundTransOrderQueryModel alipayFundTransOrderQueryModel) {

        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
        request.setBizModel(alipayFundTransOrderQueryModel);
        AlipayFundTransOrderQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
            LOGGER.info("支付宝转账结果查询返回： \n" + response.getBody());
        } catch (AlipayApiException e) {
            LOGGER.error("支付宝转账结果查询失败", e);
            throw new AlipayException(AlipayTradeErrorCode.TRADE_QUERY_ERROR.getId(), "query error");
        }
        return response;
    }

    public AlipayTradeCancelResponse cancelByOutTradeNo(String outTradeNo) {

        AlipayTradeCancelModel request = new AlipayTradeCancelModel();
        request.setOutTradeNo(outTradeNo);
        return cancel(request);
    }

    public AlipayTradeCancelResponse cancelByTradeNo(String tradeNo) {

        AlipayTradeCancelModel request = new AlipayTradeCancelModel();
        request.setTradeNo(tradeNo);
        return cancel(request);
    }

    public AlipayTradeCancelResponse cancel(AlipayTradeCancelModel tradeCancel) {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizModel(tradeCancel);
        AlipayTradeCancelResponse response = null;
        try {
            response =  alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    public AlipayTradeRefundResponse refundByOutTradeNo(String outTradeNo, String refundAmount) {

        AlipayTradeRefundModel request = new AlipayTradeRefundModel();
        request.setOutTradeNo(outTradeNo);
        request.setRefundAmount(refundAmount);
        return refund(request);
    }

    public AlipayTradeRefundResponse refundByTradeNo(String tradeNo, String refundAmount) {

        AlipayTradeRefundModel request = new AlipayTradeRefundModel();
        request.setTradeNo(tradeNo);
        request.setRefundAmount(refundAmount);
        return refund(request);
    }

    public AlipayTradeRefundResponse refund(AlipayTradeRefundModel tradeRefund) {

        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();

        request.setBizModel(tradeRefund);
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 下载账单
     * @param billDownloadurlQuery
     * @return AlipayDataDataserviceBillDownloadurlQueryResponse
     */
    public AlipayDataDataserviceBillDownloadurlQueryResponse downloadBill(
            AlipayDataDataserviceBillDownloadurlQueryModel billDownloadurlQuery) {

        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizModel(billDownloadurlQuery);

        AlipayDataDataserviceBillDownloadurlQueryResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * 支付宝转账到其他账户
     * @param transfer AlipayFundTransToAccountTransfer
     * @return 请求的响应
     */
    public AlipayFundTransToaccountTransferResponse transferToAccount(AlipayFundTransToaccountTransferModel transfer) {
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizModel(transfer);
        AlipayFundTransToaccountTransferResponse response = null;
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new AlipayException(AlipayTradeErrorCode.TRADE_TRANSFER_ERROR.getId(), AlipayTradeErrorCode.TRADE_TRANSFER_ERROR.getMsg());
        }
        if(response.isSuccess()){
            return response;
        } else {
            throw new AlipayException(500, Integer.valueOf(response.getCode()), response.getSubMsg());
        }
    }

    /**
     * App支付
     * @param request
     * @return
     */
    public String appPay(AlipayTradeAppPayRequest request) {
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            LOGGER.info(response.getBody());//就是orderString 可以直接给客户端请求，无需再做处理。
            return response.getBody();
        } catch (AlipayApiException e) {
            LOGGER.error("App支付异常", e);
            throw new AlipayException(AlipayTradeErrorCode.TRADE_PAY_ERROR.getId(), AlipayTradeErrorCode.TRADE_PAY_ERROR.getMsg());
        }
    }

    public MonitorHeartbeatSynResponse sendHeartbeat(MonitorHeartbeatSynRequest request) {
        MonitorHeartbeatSynResponse response = null;
        try {
            response = alipayClient.execute(request);
            LOGGER.info("交易保障数据上传：" + response.getBody());
        } catch (AlipayApiException e) {
            LOGGER.error("交易保障数据上传失败：",e);
            throw new AlipayException(500, "send error");
        }
        return response;
    }
}