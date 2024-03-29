package cn.iiss.logistics.domainservice;

import cn.iiss.common.core.web.page.TableDataInfo;
import cn.iiss.logistics.LogisticsInfo;
import cn.iiss.logistics.request.LogisticsCreateRequest;
import cn.iiss.logistics.request.LogisticsUpdateRequest;
import cn.iiss.logistics.response.LogisticsDetailResponse;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ILogisticsService extends IService<LogisticsInfo> {

    /**
     * 创建
     *
     * @param logisticsCreateRequest
     * @return
     */
    boolean createBase(LogisticsCreateRequest logisticsCreateRequest);

    /**
     * 更新
     *
     * @param logisticsUpdateRequest
     * @return
     */
    boolean complete(LogisticsUpdateRequest logisticsUpdateRequest);
    boolean completeOk(LogisticsUpdateRequest logisticsUpdateRequest);

    TableDataInfo getPageList();

    LogisticsDetailResponse getLogisticsDetail(Long id);
}
