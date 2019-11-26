package com.cex.tmall.service;

import com.cex.tmall.pojo.Order;

import java.util.List;

public interface OrderItemService extends BaseService {
    public void fill(List<Order> orders);
    public void fill(Order order);
}
