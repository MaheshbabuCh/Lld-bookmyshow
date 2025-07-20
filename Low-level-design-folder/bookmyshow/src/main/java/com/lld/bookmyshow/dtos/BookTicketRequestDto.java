package com.lld.bookmyshow.dtos;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BookTicketRequestDto {

    private List<Long> seatIds;
    private long userId;
    private long showId;
}
