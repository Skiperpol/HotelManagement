package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private Long roomId;
    @Column(name = "roomNumber")
    private Long roomNumber;
    @Column(name = "roomPrice")
    private double roomPrice;
    @Column(name = "roomIsEmpty")
    private boolean roomIsEmpty;
    @Column(name = "roomIsClean")
    private boolean roomIsClean;
    @Column(name = "roomType")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    @Column(name = "guestIds")
    private String guestIds;
}
