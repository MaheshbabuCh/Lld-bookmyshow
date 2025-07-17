package com.lld.bookmyshow.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
public class Ticket {
    private User user;
    private Show show;
    private List<Payment> payments;
    private int amount;
    private TicketStatus ticketStatus;
    private List<Seat> seats;
    private PaymentMode paymentMode;
    private Date startTime;

}
