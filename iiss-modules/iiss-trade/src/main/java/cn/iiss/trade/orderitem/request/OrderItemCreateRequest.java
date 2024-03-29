package cn.iiss.trade.orderitem.request;

import cn.iiss.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema
public class OrderItemCreateRequest implements Request {
    @Schema(
            title = "唯一流水号"
    )
    private Long flowNo;

    @Schema(
            title = "amount"
    )
    private BigDecimal amount;

    @Schema(
            title = "真实金额"
    )
    private BigDecimal realAmount;

    @Schema(
            title = "计量数量"
    )
    private BigDecimal itemCount;

    @Schema(
            title = "计量单位"
    )
    private String measureUnit;

    @Schema(
            title = "商品ID/计费模型ID"
    )
    private String goodsId;

    @Schema(
            title = "商品编码"
    )
    private String goodsCode;

    @Schema(
            title = "商品名称"
    )
    private String productName;

    @Schema(
            title = "费用描述"
    )
    private String feeRemark;

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getItemCount() {
        return itemCount;
    }

    public void setItemCount(BigDecimal itemCount) {
        this.itemCount = itemCount;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFeeRemark() {
        return feeRemark;
    }

    public void setFeeRemark(String feeRemark) {
        this.feeRemark = feeRemark;
    }
}
