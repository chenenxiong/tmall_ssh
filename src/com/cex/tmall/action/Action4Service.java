package com.cex.tmall.action;

import com.cex.tmall.pojo.Property;
import com.cex.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;

public class Action4Service extends Action4Pojo{
    @Autowired
    protected CategoryService categoryService;

    @Autowired
    protected PropertyService propertyService;

    @Autowired
    protected ProductService productService;

    @Autowired
    protected ProductImageService productImageService;

    @Autowired
    protected PropertyValueService propertyValueService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected OrderService orderService;

    @Autowired
    protected OrderItemService orderItemService;

    @Autowired
    protected ReviewService reviewService;
}
