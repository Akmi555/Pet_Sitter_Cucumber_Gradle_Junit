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

public class PetDTO {

    private int id;
    private String type;
    private String name;
    private String photo;
    private UserDTO user;
    private boolean deleted;
    // List<PetDTO> pets;
}
