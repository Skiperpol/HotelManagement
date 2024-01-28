package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.model.Guest;
import com.hotelmangementprogram.hotelmanagement.model.OrderDto;
import com.hotelmangementprogram.hotelmanagement.repository.*;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelServiceTests {
    @Mock
    private GuestRepository guestRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeLoginRepository employeeLoginRepository;
    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private HotelService hotelService;
    private final String INVALID_ID = "-1";
    private final String VALID_ID = "1";
    private final String VALID_DATE = "2000-10-10";
    private final Guest VALID_GUEST = new Guest(
            1L, "first", "second", "12345678910", "123456789", "email@com", null, 100L, LocalDate.parse(VALID_DATE), LocalDate.parse(VALID_DATE)
    );

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void acceptOrder_ShouldThrow_WhenInvalidDataPassed() throws AssertionError{
        when(hotelService.getGuest(Long.parseLong(INVALID_ID))).thenReturn(null);
        when(hotelService.getGuest(Long.parseLong(VALID_ID))).thenReturn(Optional.of(
                VALID_GUEST
        ));
        when(hotelService.getMenu()).thenReturn(new ArrayList<>());

        assertAll(
                () ->  assertThrows(NullPointerException.class, () -> hotelService
                        .acceptOrder(new OrderDto(
                                INVALID_ID,
                                VALID_ID
                        ))),
                () -> assertThrows(IndexOutOfBoundsException.class, () -> hotelService
                        .acceptOrder(new OrderDto(
                                VALID_ID,
                                INVALID_ID
                        )))
        );
    }
}
