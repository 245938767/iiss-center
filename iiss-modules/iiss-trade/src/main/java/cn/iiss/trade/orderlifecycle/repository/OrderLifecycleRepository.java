package cn.iiss.trade.orderlifecycle.repository;

import cn.iiss.trade.orderlifecycle.OrderLifecycle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderLifecycleRepository extends BaseMapper<OrderLifecycle> {
}
