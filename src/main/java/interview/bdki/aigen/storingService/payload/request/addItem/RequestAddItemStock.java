package interview.bdki.aigen.storingService.payload.request.addItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAddItemStock {

    private String name;
    private int quantity;
    private String serialNumber;
    private Map<String,Object> additionalInfo;
    private String imageItem;
    
}
