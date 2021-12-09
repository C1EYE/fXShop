package online.c1eye.thanato.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import online.c1eye.thanato.model.OrderDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface OrderMapper extends BaseMapper<OrderDO> {

    /**
     * 修改订单状态
     * @param status 状态
     * @param id 订单id
     * @return int
     */
    int changeOrderStatus(@Param("status") Integer status, @Param("id") Integer id);

    /**
     * 搜索订单
     * @param pager 分页对象
     * @param keyword 关键字
     * @param start 开始时间
     * @param end 结束时间
     * @return IPage
     */
    IPage<OrderDO> searchOrders(Page<OrderDO> pager, String keyword, Date start, Date end);

}
