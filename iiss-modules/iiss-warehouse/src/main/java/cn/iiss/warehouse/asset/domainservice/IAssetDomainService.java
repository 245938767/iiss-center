package cn.iiss.warehouse.asset.domainservice;

import cn.iiss.warehouse.asset.domainservice.model.AssetUpdateBizModel;
import cn.iiss.warehouse.asset.domainservice.model.BatchInOutModel;
import cn.iiss.warehouse.asset.domainservice.model.TransferModel;

public interface IAssetDomainService {

    /**
     * 资产入库
     *
     * @param batchInOutModel
     */
    Long handleAssetIn(BatchInOutModel batchInOutModel);


    /**
     * 资产出库
     *
     * @param batchInOutModel
     */
    void handleAssetOut(BatchInOutModel batchInOutModel);

    /**
     * 资产调拨，转移
     *
     * @param transferModel
     */
    Long handleAssetTransfer(TransferModel transferModel);

}
