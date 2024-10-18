package interview.bdki.aigen.storingService.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {

    private String status;
    private String responseCode;
    private String responseMessage;
    private T data;
    
}
