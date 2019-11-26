package com.cex.tmall.action;

import com.cex.tmall.pojo.Order;
import com.cex.tmall.service.OrderService;
import com.cex.tmall.util.Page;
import org.apache.struts2.convention.annotation.Action;

import java.util.Date;

public class OrderAction extends Action4Result {
    @Action("admin_order_list")
    public String list(){
        if(page==null) page=new Page();
        int total=orderService.total();
        page.setTotal(total);
        orders = orderService.listByPage(page);
        orderItemService.fill(orders);
        return "listOrder";
    }

    @Action("admin_order_delivery")
    public String delivery() {
        int id = order.getId();
        order = (Order) orderService.get(id);
        order.setDeliveryDate(new Date());
        order.setStatus(OrderService.waitConfirm);
        orderService.update(order);
        return "listOrderPage";
    }
}
