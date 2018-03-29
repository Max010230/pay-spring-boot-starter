# 集成支付宝和微信支付的spring-boot-starter

## 配置相关

### 支付宝配置

| 参数名称 | 参数值 |
| ------- | --------- |
| alipay.host | https://openapi.alipay.com |
| alipay.app-id | APPID |
| alipay.private-key | 私钥 |
| alipay.alipay-public-key | 公钥 |

### 微信配置

| 参数名称 | 参数值 |
| ------- | --------- |
| wechat.pay.app-id | APPID |
| wechat.pay.mch-id | 微信支付商户号 |
| wechat.pay.mch-key | API密钥 |
| wechat.pay.key-path | Appsecret |

## 支付宝支付服务


```
        AlipayTradePayModel tradePay = new AlipayTradePayModel();
        tradePay.setOutTradeNo(System.currentTimeMillis()+"");//订单号
        tradePay.setScene(AlipayTradePayScene.BAR_CODE.getValue());
        tradePay.setAuthCode("282511443247496298");//付款码
        tradePay.setSubject("测试订单");
        tradePay.setTotalAmount("0.01");//金额
        tradePay.setGoodsDetail("");//商品详情
        tradePay.setStoreId("3124");//门店id
        try {
            AlipayTradePayResponse pay = alipayTradeService.pay(tradePay);//调用支付
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

```

## 微信支付服务

```
        WxPayMicropayRequest request = WxPayMicropayRequest
                .newBuilder()
                .detail("")//订单详情
                .body("测试订单订单")
                .spbillCreateIp("127.0.0.1")
                .outTradeNo(System.currentTimeMillis()+"") //用时间流水号作为交易号
                .authCode("134784290905837690")//付款码
                .totalFee(1) //付款总金额
                .build();
        WxPayMicropayResult micropay = wechatTradeService.micropay(request);
```

## 关于打包
项目根路径下执行gradlew uploadArchives命令，在本地仓库上传成功后既可以使用。
```
compile group: 'me.wuxingxing', name: 'pay-spring-boot-starter', version: '0.0.1-SNAPSHOT'
```
or
```
<dependency>  
    <groupId>me.wuxingxing</groupId>  
    <artifactId>pay-spring-boot-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version> 
</dependency>  
```
