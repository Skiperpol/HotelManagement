package com.hotelmangementprogram.hotelmanagement;

import com.hotelmangementprogram.hotelmanagement.model.*;
import com.hotelmangementprogram.hotelmanagement.service.DataValidation;
import com.hotelmangementprogram.hotelmanagement.service.HotelService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(com.hotelmangementprogram.hotelmanagement.controller.HotelController.class)
public class HotelControllerTests {
    @MockBean
    private HotelService hotelService;
    @MockBean
    private DataValidation dataValidation;
    @Autowired
    private MockMvc mockMvc;
    private final String BASE_ENDPOINT = "/hotel";

    @Test
    public void getGuests_ShouldReturnGuestList_WhenCalled() throws Exception{
        List<Guest> guestList = Arrays.asList(
                new Guest(1L, "first", "second", "12345678910", "123456789", "email@com", null, null, null, null),
                new Guest(2L, "first2", "second2", "12345678911", "123456700", "email2@com", null, null, null, null)
        );
        when(hotelService.getGuests()).thenReturn(guestList);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+ "/guest/get/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].personId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("first"))
                .andExpect(jsonPath("$[1].firstName").value("first2"));
    }


    @Test
    public void getEmployee_ShouldReturnEmployeeList_WhenCalled() throws Exception{
        List<Employee> employeeList = Arrays.asList(

                new Cook(1L, "first", "second", "12345678910", "123456789", "email@com", Job.COOK, null, null),
                new Receptionist(1L, "first2", "second2", "12345678910", "123456789", "email@com", Job.RECEPTIONIST, null)
        );
        when(hotelService.getEmployees()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+ "/employee/get/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].personId").value(1))
                .andExpect(jsonPath("$[0].firstName").value("first"))
                .andExpect(jsonPath("$[1].firstName").value("first2"));
    }

    @Test
    public void getRooms_ShouldReturnRoomList_WhenCalled() throws Exception{
        List<Room> roomList = Arrays.asList(
                new Room(1L, 1L, 11, false, false, RoomType.DOUBLEROOM, "1"),
                new Room(2L, 2L, 12, true, true, RoomType.SUITE, "")
        );
        when(hotelService.getRooms()).thenReturn(roomList);

        mockMvc.perform(MockMvcRequestBuilders.get(BASE_ENDPOINT+ "/room/get/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].roomId").value(1))
                .andExpect(jsonPath("$[0].roomNumber").value(1))
                .andExpect(jsonPath("$[1].roomNumber").value(2));
    }

}
