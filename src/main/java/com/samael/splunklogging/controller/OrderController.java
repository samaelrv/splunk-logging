package com.samael.splunklogging.controller;


import com.samael.splunklogging.entity.Order;
import com.samael.splunklogging.service.OrderService;
import com.samael.splunklogging.util.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    Logger logger = LogManager.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order placeOrder(@RequestBody Order order) {
        logger.info("OrderController:placeOrder persist order request {}", Mapper.mapToJsonString(order));
        Order addOrder = orderService.addOrder(order);
        logger.info("OrderController:placeOrder response from service {}", Mapper.mapToJsonString(addOrder));
        return addOrder;
    }

    @GetMapping
    public List<Order> getOrders() {
        List<Order> orders = orderService.getOrders();
        logger.info("OrderController:getOrders response from service {}", Mapper.mapToJsonString(orders));
        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id) {
        logger.info("OrderController:getOrder fetch order by id {}", id);
        Order order = orderService.getOrder(id);
        logger.info("OrderController:getOrder fetch order response {}", Mapper.mapToJsonString(order));
        return order;
    }



}
