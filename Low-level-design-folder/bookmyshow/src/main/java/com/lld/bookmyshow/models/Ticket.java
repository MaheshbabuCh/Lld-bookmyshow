package com.lld.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@Entity
public class Ticket extends BaseModel{
    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    @OneToMany(mappedBy = "ticket")
    private List<Payment> payments;

    private int amount;

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToMany
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    private PaymentMode paymentMode;

    private Date startTime;

}
