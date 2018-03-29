package me.wuxingxing.pay.wechat.dto;

/**
 * @author xingxing.wu
 * @date 2018/3/29
 */
public class WxPayGoodsDetail {

    /**
     * 必填 32 商品的编号
     */
    private String goodsId;

    /**
     * 可选 32 微信支付定义的统一商品编号
     */
    private String wxpayGoodsId;

    /**
     * 必填 256 商品名称
     */
    private String goodsName;

    /**
     * 必填 商品数量
     */
    private Integer goodsNum;

    /**
     * 必填 商品单价，单位为分
     */
    private Integer price;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getWxpayGoodsId() {
        return wxpayGoodsId;
    }

    public void setWxpayGoodsId(String wxpayGoodsId) {
        this.wxpayGoodsId = wxpayGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "goodsId='" + goodsId + '\'' +
                ", wxpayGoodsId='" + wxpayGoodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsNum=" + goodsNum +
                ", price=" + price +
                '}';
    }
}

