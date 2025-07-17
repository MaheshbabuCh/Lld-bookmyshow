package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class Payment {
    private Ticket ticket;
    private int amount;
    private PaymentStatus paymentStatus;
    private PaymentMode paymentMode;
    private String referenceId;
    private Date paymentDate;
    private String paymentProvider;
}
