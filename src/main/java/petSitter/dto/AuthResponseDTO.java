package petSitter.dto;


import lombok.*;



 // Конструктор без аргументов для Jackson
//@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class AuthResponseDTO {
    private String token;


}
