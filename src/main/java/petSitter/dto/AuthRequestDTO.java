package petSitter.dto;


import lombok.*;


@Getter
@Setter
@ToString
@Builder
public class AuthRequestDTO {

    private String email;
    private String password;
    private  String firstName;
    private String lastName;
    private String description;
    private String photo;


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

}
