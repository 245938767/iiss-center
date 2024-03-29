package cn.iiss.trade.face.request;

import cn.iiss.common.core.domain.CodeValue;
import cn.iiss.commons.annotation.FieldDesc;
import cn.iiss.commons.model.Request;
import cn.iiss.trade.face.model.OrderItemModel;

import java.math.BigDecimal;
import java.util.List;

@FieldDesc
public class OrderBaseUpdateRequest implements Request {
    @FieldDesc(
            name = "订单金额"
    )
    private BigDecimal totalAmount;

    @FieldDesc(
            name = "真实金额"
    )
    private BigDecimal realAmount;

    @FieldDesc(
            name = "用户id"
    )
    private Long userId;

    @FieldDesc(
            name = "订单信息"
    )
    private List<CodeValue> attrs;

    private List<OrderItemModel> orderItemModelList;
    private Long id;

    public List<OrderItemModel> getOrderItemModelList() {
        return orderItemModelList;
    }

    public OrderBaseUpdateRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public OrderBaseUpdateRequest setOrderItemModelList(List<OrderItemModel> orderItemModelList) {
        this.orderItemModelList = orderItemModelList;
        return this;
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

    public List<CodeValue> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<CodeValue> attrs) {
        this.attrs = attrs;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
