package com.hotelmangementprogram.hotelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RoomDto {
    private Long roomNumber;
    private Long roomPrice;
    private String roomType;
}
