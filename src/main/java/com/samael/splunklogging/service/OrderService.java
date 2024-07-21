package com.samael.splunklogging.service;

import com.samael.splunklogging.entity.Order;
import com.samael.splunklogging.util.Mapper;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    private static final Logger logger = LogManager.getLogger(OrderService.class);

    private List<Order> orderList = new ArrayList<>();

    public Order addOrder(Order order){
        logger.info("OrderService:addOrder execution started..");
        logger.info("OrderService:addOrder request payload {} ", Mapper.mapToJsonString(order));
        order.setOrderPlacedDate(new Date());
        order.setTransactionId(UUID.randomUUID().toString());
        orderList.add(order);
        logger.info("OrderService:addOrder response  {} ", Mapper.mapToJsonString(order));
        logger.info("OrderService:addOrder execution ended..");
        return order;
    }

    public List<Order> getOrders(){
        logger.info("OrderService:getOrders execution started..");
        List<Order> list = null;
        list = orderList;
        logger.info("OrderService:getOrders response  {} ", Mapper.mapToJsonString(orderList));
        logger.info("OrderService:getOrders execution ended..");
        return list;
    }

    public Order getOrder(int id){
        Order order =orderList.stream()
                .filter(ord->ord.getId()==id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Order not found with id : " + id));
        logger.info("OrderService:getOrder execution ended..");
        return order;
    }
}
