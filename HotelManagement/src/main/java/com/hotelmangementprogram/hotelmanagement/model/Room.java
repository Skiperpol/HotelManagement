package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private Long roomId;
    @Column(name = "roomPrice")
    private Long roomPrice;
    @Column(name = "roomIsEmpty")
    private boolean roomIsEmpty;
    @Column(name = "roomIsClean")
    private boolean roomIsClean;
    @Column(name = "roomType")
    private RoomType roomType;
    @Column(name = "guestIds")
    private String guestIds;
}
