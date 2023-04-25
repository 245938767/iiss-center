// ---Auto Generated by Only4Play ---
package cn.iiss.trade.order.request;

import cn.iiss.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class OrderBaseQueryRequest implements Request {
    @Schema(
            title = "用户id"
    )
    private Long userId;

    @Schema(
            title = "订单类型、订单类型创建不同的状态机"
    )
    private Integer orderType;

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
}
