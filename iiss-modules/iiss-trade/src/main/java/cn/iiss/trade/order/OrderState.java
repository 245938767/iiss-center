package cn.iiss.trade.order;


import cn.iiss.commons.constants.BaseEnum;

import java.util.Optional;

/**
 *
 */
public enum OrderState implements BaseEnum<OrderState> {

    /**
     *
     */
    WAIT_PAY(1, "待支付"),
    PAY_SUCCESS(2, "支付完成"),
    ABANDON(3, "已废弃");

    OrderState(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<OrderState> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderState.class, code));
    }

}
