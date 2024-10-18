package interview.bdki.aigen.storingService.payload.request.getItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGetItemStock {

    private String name;
    private String serialNumber;
    
}
