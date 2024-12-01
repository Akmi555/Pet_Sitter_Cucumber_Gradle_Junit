package petSitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingNewStatusDTO {

    private int id;
    private String status; // Возможные значения: pending, confirmed, canceled, rejected
}
