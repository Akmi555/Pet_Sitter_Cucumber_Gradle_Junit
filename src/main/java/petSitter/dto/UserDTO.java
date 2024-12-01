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
public class UserDTO {

    private int id;

    private  String firstName;
    private String lastName;
    private String email;
    private String password;
    private String photo;
    private String description;
    private boolean isDeleted;

    private List<ReviewResponseDTO> reviews;

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", photo='").append(photo).append('\'');
        sb.append('}');
        return sb.toString();


    }

//    public UserDTO setPassword(String password) {
//        this.password = password;
//        return this;
//    }

}
