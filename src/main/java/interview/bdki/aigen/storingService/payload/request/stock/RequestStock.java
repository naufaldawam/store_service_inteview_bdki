package interview.bdki.aigen.storingService.payload.request.stock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestStock {

    private Long id;
    private String name;
    private int quantity;
    private String serialNumber;
    private Map<String,Object> additionalInfo;
    private String imageUrl;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;
    
}
