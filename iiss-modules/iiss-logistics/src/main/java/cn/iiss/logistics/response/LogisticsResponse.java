package cn.iiss.logistics.response;

import cn.iiss.commons.annotation.FieldDesc;
import cn.iiss.commons.model.AbstractMybatisResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsResponse extends AbstractMybatisResponse {

    @FieldDesc(name = "唯一流水号")
    private Long flowNo;

    @FieldDesc(name = "状态")
    private String logisticsStatus;

    @FieldDesc(name = "到达时间")
    private Instant arriveTime;

    @FieldDesc(name = "费用")
    private BigDecimal freight;
    @FieldDesc(name = "订单ID")
    private Long orderId;

    private Long assetId;

    @FieldDesc(name = "发货仓")
    private Long shipWarehouseId;
    @FieldDesc(name = "发货仓名称")
    private String shipWarehouseName;

    @FieldDesc(name = "收货仓")
    private Long consigneeWarehouseId;
    @FieldDesc(name = "收货仓名称")
    private String consigneeWarehouseName;

}
