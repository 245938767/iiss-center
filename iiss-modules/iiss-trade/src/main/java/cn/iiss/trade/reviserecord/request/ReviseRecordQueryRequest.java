package cn.iiss.trade.reviserecord.request;

import cn.iiss.commons.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema
public class ReviseRecordQueryRequest implements Request {
    @Schema(
            title = "唯一流水"
    )
    private Long flowNo;

    @Schema(
            title = "表名"
    )
    private String tableName;

    public Long getFlowNo() {
        return flowNo;
    }

    public void setFlowNo(Long flowNo) {
        this.flowNo = flowNo;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
