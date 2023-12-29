package com.hotelmangementprogram.hotelmanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderDto {
    private String guestId;
    private String dishIds;
}
