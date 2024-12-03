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

public class ResponseServiceDTO {

    private int id;
    private String title;
    private String description;
    private Double price;
    private UserResponseDTO user;


}
