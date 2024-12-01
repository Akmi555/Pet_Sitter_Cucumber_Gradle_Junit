package petSitter.dto;


import lombok.*;


@Getter
@Setter
@ToString
@Builder
public class ErrorDTO {
    private  String timestamp;
    private  int status;
    private  String error;
    private  String path;
    private Object message;


}
