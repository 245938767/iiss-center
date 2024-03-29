package cn.iiss.trade.controller;

import cn.iiss.common.core.web.controller.BaseController;
import cn.iiss.common.core.web.page.TableDataInfo;
import cn.iiss.commons.constants.CodeEnum;
import cn.iiss.commons.model.JsonObject;
import cn.iiss.trade.reviserecord.dto.ReviseRecordCreator;
import cn.iiss.trade.reviserecord.dto.ReviseRecordUpdater;
import cn.iiss.trade.reviserecord.dto.ReviseRecordVO;
import cn.iiss.trade.reviserecord.mapper.ReviseRecordMapper;
import cn.iiss.trade.reviserecord.request.ReviseRecordCreateRequest;
import cn.iiss.trade.reviserecord.request.ReviseRecordQueryRequest;
import cn.iiss.trade.reviserecord.request.ReviseRecordUpdateRequest;
import cn.iiss.trade.reviserecord.response.ReviseRecordResponse;
import cn.iiss.trade.reviserecord.service.IReviseRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("reviseRecord/v1")
@RequiredArgsConstructor
public class ReviseRecordController extends BaseController {
    private final IReviseRecordService reviseRecordService;

    /**
     * createRequest
     */
    @PostMapping("createReviseRecord")
    public JsonObject<Long> createReviseRecord(@RequestBody ReviseRecordCreateRequest request) {
        ReviseRecordCreator creator = ReviseRecordMapper.INSTANCE.request2Dto(request);
        return JsonObject.success(reviseRecordService.createReviseRecord(creator));
    }

    /**
     * update request
     */
    @PostMapping("updateReviseRecord")
    public JsonObject<String> updateReviseRecord(@RequestBody ReviseRecordUpdateRequest request) {
        ReviseRecordUpdater updater = ReviseRecordMapper.INSTANCE.request2Updater(request);
        reviseRecordService.updateReviseRecord(updater);
        return JsonObject.success(CodeEnum.Success.getName());
    }

    /**
     * valid
     */
    @PostMapping("valid/{id}")
    public JsonObject<String> validReviseRecord(@PathVariable Long id) {
        reviseRecordService.validReviseRecord(id);
        return JsonObject.success(CodeEnum.Success.getName());
    }

    /**
     * invalid
     */
    @PostMapping("invalid/{id}")
    public JsonObject<String> invalidReviseRecord(@PathVariable Long id) {
        reviseRecordService.invalidReviseRecord(id);
        return JsonObject.success(CodeEnum.Success.getName());
    }

    /**
     * findById
     */
    @GetMapping("findById/{id}")
    public JsonObject<ReviseRecordResponse> findById(@PathVariable Long id) {
        ReviseRecordVO vo = reviseRecordService.findById(id);
        ReviseRecordResponse response = ReviseRecordMapper.INSTANCE.vo2CustomResponse(vo);
        return JsonObject.success(response);
    }

    /**
     * findByPage request
     */
    @PostMapping("findByPage")
    public JsonObject<TableDataInfo> findByPage(
            @RequestBody ReviseRecordQueryRequest request) {
        startPage();
        TableDataInfo page = reviseRecordService.findByPage(ReviseRecordMapper.INSTANCE.request2Query(request));
        return JsonObject.success(
                page
        );
    }
}
