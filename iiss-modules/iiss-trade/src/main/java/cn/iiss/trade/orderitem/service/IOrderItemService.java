package cn.iiss.trade.orderitem.service;

import cn.iiss.common.core.web.page.TableDataInfo;
import cn.iiss.trade.orderitem.dto.OrderItemCreator;
import cn.iiss.trade.orderitem.dto.OrderItemQuery;
import cn.iiss.trade.orderitem.dto.OrderItemUpdater;
import cn.iiss.trade.orderitem.dto.OrderItemVO;

public interface IOrderItemService {
  /**
   * create
   */
  Long createOrderItem(OrderItemCreator creator);

  /**
   * update
   */
  void updateOrderItem(OrderItemUpdater updater);

  /**
   * findById
   */
  OrderItemVO findById(Long id);

  /**
   * findByPage
   */
  TableDataInfo findByPage(OrderItemQuery query);
}
