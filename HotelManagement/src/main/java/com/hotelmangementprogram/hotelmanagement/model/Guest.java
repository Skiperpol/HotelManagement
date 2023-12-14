package com.hotelmangementprogram.hotelmanagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "guest")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Guest extends People{
    private byte dontDeleteMeUntilYouAddSomethingElse; //Lombok will crash with empty class. Delete when you add something else
}
