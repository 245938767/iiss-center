package cn.iiss.trade.reviserecord.service;

import cn.iiss.common.core.web.page.TableDataInfo;
import cn.iiss.trade.reviserecord.dto.ReviseRecordCreator;
import cn.iiss.trade.reviserecord.dto.ReviseRecordQuery;
import cn.iiss.trade.reviserecord.dto.ReviseRecordUpdater;
import cn.iiss.trade.reviserecord.dto.ReviseRecordVO;

public interface IReviseRecordService {
    /**
     * create
     */
    Long createReviseRecord(ReviseRecordCreator creator);

    /**
     * update
     */
    void updateReviseRecord(ReviseRecordUpdater updater);

    /**
     * valid
     */
    void validReviseRecord(Long id);

    /**
     * invalid
     */
    void invalidReviseRecord(Long id);

    /**
     * findById
     */
    ReviseRecordVO findById(Long id);

    /**
     * findByPage
     */
    TableDataInfo findByPage(ReviseRecordQuery query);
}
