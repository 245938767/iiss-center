package cn.iiss.trade.orderitem.service;

import cn.iiss.common.core.constant.HttpStatus;
import cn.iiss.common.core.web.page.TableDataInfo;
import cn.iiss.commons.constants.CodeEnum;
import cn.iiss.commons.exception.BusinessException;
import cn.iiss.mybatis.support.BaseMybatisAggregate;
import cn.iiss.mybatis.support.EntityOperations;
import cn.iiss.trade.orderitem.OrderItem;
import cn.iiss.trade.orderitem.dto.OrderItemCreator;
import cn.iiss.trade.orderitem.dto.OrderItemQuery;
import cn.iiss.trade.orderitem.dto.OrderItemUpdater;
import cn.iiss.trade.orderitem.dto.OrderItemVO;
import cn.iiss.trade.orderitem.mapper.OrderItemMapper;
import cn.iiss.trade.orderitem.repository.OrderItemRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderItemServiceImpl implements IOrderItemService {
    private final OrderItemRepository orderItemRepository;

    /**
     * createImpl
     */
    @Override
    public Long createOrderItem(OrderItemCreator creator) {
        Optional<OrderItem> orderItem = EntityOperations.doCreate(orderItemRepository)
                .create(() -> OrderItemMapper.INSTANCE.dtoToEntity(creator))
                .update(BaseMybatisAggregate::prePersist)
                .execute();
        return orderItem.isPresent() ? orderItem.get().getId() : 0;
    }

    /**
     * update
     */
    @Override
    public void updateOrderItem(OrderItemUpdater updater) {
        EntityOperations.doUpdate(orderItemRepository)
                .loadById(updater.getId())
                .update(updater::updateOrderItem)
                .execute();
    }

    /**
     * findById
     */
    @Override
    public OrderItemVO findById(Long id) {
        Optional<OrderItem> orderItem = Optional.of(orderItemRepository.selectById(id));
        return new OrderItemVO(orderItem.orElseThrow(() -> new BusinessException(CodeEnum.NotFindError)));
    }

    /**
     * findByPage
     */
    @Override
    public TableDataInfo findByPage(OrderItemQuery query) {
        LambdaQueryWrapper<OrderItem> orderItemLambdaQueryWrapper = new LambdaQueryWrapper<OrderItem>().orderByDesc(OrderItem::getCreatedAt);
        List<OrderItem> orderItems = orderItemRepository.selectList(orderItemLambdaQueryWrapper);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setRows(orderItems);
        rspData.setMsg("查询成功");
        rspData.setTotal(new PageInfo(orderItems).getTotal());
        return rspData;
    }
}
