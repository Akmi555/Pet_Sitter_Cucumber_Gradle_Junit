package petSitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class BookingDTO {

    private int id;
   // private ServiceDTO service;
   // private PetDTO pet;
    private int serviceId;
    private int petId;
    private Double price;
    private String startDate;
    private String endDate;
    private String status; // Возможные значения: pending, confirmed, canceled, rejected
    List<BookingDTO> bookings;
}
