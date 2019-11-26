package com.cex.tmall.service.impl;

import com.cex.tmall.pojo.Order;
import com.cex.tmall.pojo.OrderItem;
import com.cex.tmall.service.OrderItemService;
import com.cex.tmall.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl extends BaseServiceImpl implements OrderItemService {

    @Autowired
    ProductImageService productImageService;

    @Override
    public void fill(Order order) {
        List<OrderItem> orderItems = listByParent(order);
        order.setOrderItems(orderItems);

        float total=0;
        int totalNumber=0;
        for(OrderItem oi:orderItems){
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            totalNumber+=oi.getNumber();
            productImageService.setFirstProductImage(oi.getProduct());
        }
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
    }
    @Override
    public void fill(List<Order> orders) {
        for(Order order:orders){
            fill(order);
        }
    }
}
