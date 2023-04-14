// ---Auto Generated by Only4Play ---
package cn.iiss.trade.order.response;

import cn.iiss.common.core.domain.CodeValue;
import cn.iiss.commons.constants.ValidStatus;
import cn.iiss.commons.model.AbstractMybatisResponse;
import cn.iiss.commons.model.Response;
import cn.iiss.order.commons.pay.PayItem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema
public class OrderBaseResponse extends AbstractMybatisResponse {
    @Schema(
            title = "唯一流水号"
    )
    private Long flowNo;

    @Schema(
            title = "订单金额"
    )
    private BigDecimal totalAmount;

    @Schema(
            title = "真实金额"
    )
    private BigDecimal realAmount;

    @Schema(
            title = "用户id"
    )
    private Long userId;

    @Schema(
            title = "订单类型、订单类型创建不同的状态机"
    )
    private Integer orderType;

    @Schema(
            title = "支付详情"
    )
    private List<PayItem> payList;

    @Schema(
            title = "支付时间"
    )
    private Long payTime;

    @Schema(
            title = "订单状态"
    )
    private Integer orderState;

    @Schema(
            title = "validStatus"
    )
    private ValidStatus validStatus;

    @Schema(
            title = "订单信息"
    )
    private List<CodeValue> attrs;

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public List<PayItem> getPayList() {
        return payList;
    }

    public void setPayList(List<PayItem> payList) {
        this.payList = payList;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public ValidStatus getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(ValidStatus validStatus) {
        this.validStatus = validStatus;
    }

    public List<CodeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<CodeValue> attrs) {
        this.attrs = attrs;
    }
}
