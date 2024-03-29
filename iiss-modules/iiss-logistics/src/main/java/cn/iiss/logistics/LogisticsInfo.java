package cn.iiss.logistics;

import cn.iiss.common.core.domain.CodeValue;
import cn.iiss.common.log.annotation.Log;
import cn.iiss.commons.annotation.FieldDesc;
import cn.iiss.commons.constants.CodeEnum;
import cn.iiss.commons.exception.BusinessException;
import cn.iiss.logistics.request.LogisicsProductRequest;
import cn.iiss.mybatis.converter.InstantLongConverter;
import cn.iiss.mybatis.support.BaseMybatisAggregate;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@TableName(autoResultMap = true)
@Data
public class LogisticsInfo extends BaseMybatisAggregate {
    @FieldDesc(name = "唯一流水号")
    private Long flowNo;

    @FieldDesc(name = "状态")
    @TableField(typeHandler = LogisticsConverter.class)
    private LogisticsStatus logisticsStatus;

    @FieldDesc(name = "到达时间")
    @TableField(typeHandler = InstantLongConverter.class)
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
    @TableField(typeHandler = FastjsonTypeHandler.class)
    private List<CodeValue> products;

    /**
     * 初始化
     * @param flowNo
     * @param logisticsStatus
     * @param freight
     * @param shipWarehouseId
     * @param consigneeWarehouseId
     * @param orderId
     * @param assetId
     * @param shipWarehouseName
     * @param consigneeWarehouseName
     * @param logisicsProductRequests
     */
    public void init(Long flowNo, LogisticsStatus logisticsStatus, BigDecimal freight,
                     Long shipWarehouseId, Long consigneeWarehouseId, Long orderId,
                     Long assetId, String shipWarehouseName, String consigneeWarehouseName,
                     List<LogisicsProductRequest> logisicsProductRequests
    ) {
        this.assetId = assetId;
        this.flowNo = flowNo;
        this.logisticsStatus = logisticsStatus;
        this.freight = freight;
        this.orderId = orderId;
        this.shipWarehouseId = shipWarehouseId;
        this.consigneeWarehouseId = consigneeWarehouseId;
        this.consigneeWarehouseName = consigneeWarehouseName;
        this.shipWarehouseName = shipWarehouseName;
        this.products = logisicsProductRequests.stream().map(x -> {
            CodeValue codeValue = new CodeValue();
            codeValue.setK("productId");
            codeValue.setV(x.getProductId().toString());
            codeValue.setL(x.getProductNum().toString());
            return codeValue;
        }).toList();
        prePersist();
    }

    public void complete() {
        if (this.logisticsStatus==LogisticsStatus.DELIVERY) {
            this.logisticsStatus=LogisticsStatus.TRANSIT;
        }else {
            throw new BusinessException(CodeEnum.Fail);
        }

    }
    public void completeOk(){
        if(this.logisticsStatus==LogisticsStatus.TRANSIT){
            this.logisticsStatus=LogisticsStatus.COMPLETION;
        }else{
            throw new BusinessException(CodeEnum.Fail);
        }
    }
}
