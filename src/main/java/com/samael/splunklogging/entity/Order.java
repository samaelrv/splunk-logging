package com.samael.splunklogging.entity;


import lombok.Data;

import java.util.Date;

@Data
public class Order {

    private int id;
    private String name;
    private int qty;
    private double price;
    private String transactionId;
    private Date orderPlacedDate;
}
