package interview.bdki.aigen.storingService.payload.request.deleteStock;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDeleteStock {
    private String serialNumber;
}
