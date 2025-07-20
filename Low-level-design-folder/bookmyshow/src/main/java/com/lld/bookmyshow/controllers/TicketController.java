package com.lld.bookmyshow.controllers;

import com.lld.bookmyshow.dtos.BookTicketRequestDto;
import com.lld.bookmyshow.dtos.BookTicketResponseDto;
import com.lld.bookmyshow.exceptions.IllegalInputParamException;
import com.lld.bookmyshow.exceptions.SeatNotAvailableException;
import com.lld.bookmyshow.exceptions.ShowNotFoundException;
import com.lld.bookmyshow.models.Ticket;
import com.lld.bookmyshow.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class TicketController {

    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }



  public BookTicketResponseDto bookTicket(BookTicketRequestDto request) throws IllegalInputParamException, ShowNotFoundException, SeatNotAvailableException {

      Ticket ticket = ticketService.bookTicket(request.getShowId(), request.getSeatIds(), request.getUserId());

      BookTicketResponseDto bookTicketResponseDto = new BookTicketResponseDto();

      try {
          bookTicketResponseDto.setSeatNumbers(ticket.getSeats().stream()
                  .map(seat -> seat.getSeatNumber())
                  .toList());
          bookTicketResponseDto.setTicketStatus(String.valueOf(ticket.getTicketStatus()));
          bookTicketResponseDto.setAmount(ticket.getAmount());
          bookTicketResponseDto.setAuditoriumName(ticket.getShow().getAuditorium().getName());
          bookTicketResponseDto.setMovieName(ticket.getShow().getMovie().getTitle());
          bookTicketResponseDto.setTicketId(ticket.getId());
          bookTicketResponseDto.setShowStartTime(ticket.getShow().getStartTime());
      } catch (Exception e){
          throw new IllegalInputParamException(e.getMessage());
      }

       return bookTicketResponseDto;
    }
}
