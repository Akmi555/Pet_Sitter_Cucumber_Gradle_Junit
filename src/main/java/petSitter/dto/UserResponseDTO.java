package petSitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponseDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private List<ReviewDTO> reviews;
    private double averageStars;
    private String photo;
    private String description;
}
