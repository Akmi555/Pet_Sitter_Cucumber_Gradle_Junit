package petSitter.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServiceDTO {
    private int id;
    private UserDTO user;
    private String title;
    private String description;
    private double price;
    private String photo;
    //private CategoryDTO serviceCategory;
    private int serviceCategory;
    List<ServiceDTO> services;

}
