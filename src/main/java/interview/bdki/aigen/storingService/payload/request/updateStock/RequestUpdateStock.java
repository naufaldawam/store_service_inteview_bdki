package interview.bdki.aigen.storingService.payload.request.updateStock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateStock {
    
    private String name;
    private Integer quantity;
    private String serialNumber;
    
}
