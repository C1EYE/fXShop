package online.c1eye.thanato.controller.v1;


import com.baomidou.mybatisplus.core.metadata.IPage;
import io.github.talelin.autoconfigure.exception.NotFoundException;
import io.github.talelin.core.annotation.GroupRequired;
import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.core.annotation.PermissionMeta;
import io.github.talelin.core.annotation.PermissionModule;
import online.c1eye.thanato.common.util.PageUtil;
import online.c1eye.thanato.model.OrderDO;
import online.c1eye.thanato.service.OrderService;
import online.c1eye.thanato.vo.OrderSimplifyVO;
import online.c1eye.thanato.vo.PageResponseVO;
import online.c1eye.thanato.vo.UpdatedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1/order")
@Validated
@PermissionModule("订单")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PutMapping("/status")
    @GroupRequired
    @PermissionMeta(value = "修改订单状态")
    public UpdatedVO update(
            @RequestParam(name = "id") @Positive Integer id,
            @RequestParam(name = "status") @Min(value = 0) Integer status
                           ) {
        orderService.changeOrderStatus(id, status);
        return new UpdatedVO();
    }

    @GetMapping("/{id}/detail")
    @LoginRequired
    public OrderDO getDetail(@PathVariable(value = "id") @Positive(message = "{id.positive}") Integer id) {
        OrderDO order = orderService.getById(id);
        if (order == null) {
            throw new NotFoundException(110000);
        }
        return order;
    }

    @GetMapping("/page")
    @LoginRequired
    public PageResponseVO<OrderSimplifyVO> page(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page
                                               ) {
        IPage<OrderDO> iPage = orderService.getOrderByPage(count, page);
        List<OrderSimplifyVO> orderSimplifyVOList = orderService.convertFromDO(iPage.getRecords());
        return PageUtil.build(iPage, orderSimplifyVOList);
    }

    @GetMapping("/search")
    @LoginRequired
    public PageResponseVO<OrderSimplifyVO> search(
            @RequestParam(name = "count", required = false, defaultValue = "10")
            @Min(value = 1, message = "{page.count.min}")
            @Max(value = 30, message = "{page.count.max}") Integer count,
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @Min(value = 0, message = "{page.number.min}") Integer page,
            @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(name = "start", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date start,
            @RequestParam(name = "end", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date end
    ) {
        IPage<OrderDO> iPage = orderService.search(page, count, keyword, start, end);
        List<OrderSimplifyVO> orderSimplifyVOList = orderService.convertFromDO(iPage.getRecords());
        return PageUtil.build(iPage, orderSimplifyVOList);
    }

}



