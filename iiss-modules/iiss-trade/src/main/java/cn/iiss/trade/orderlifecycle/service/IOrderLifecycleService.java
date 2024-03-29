package cn.iiss.trade.orderlifecycle.service;

import cn.iiss.common.core.web.page.TableDataInfo;
import cn.iiss.trade.orderlifecycle.dto.OrderLifecycleCreator;
import cn.iiss.trade.orderlifecycle.dto.OrderLifecycleQuery;
import cn.iiss.trade.orderlifecycle.dto.OrderLifecycleUpdater;
import cn.iiss.trade.orderlifecycle.dto.OrderLifecycleVO;

public interface IOrderLifecycleService {
  /**
   * create
   */
  Long createOrderLifecycle(OrderLifecycleCreator creator);

  /**
   * update
   */
  void updateOrderLifecycle(OrderLifecycleUpdater updater);

  /**
   * valid
   */
  void validOrderLifecycle(Long id);

  /**
   * invalid
   */
  void invalidOrderLifecycle(Long id);

  /**
   * findById
   */
  OrderLifecycleVO findById(Long id);

  /**
   * findByPage
   */
  TableDataInfo findByPage(OrderLifecycleQuery query);
}
