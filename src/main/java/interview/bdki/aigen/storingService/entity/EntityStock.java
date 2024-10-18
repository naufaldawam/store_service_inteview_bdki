package interview.bdki.aigen.storingService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stocks")
@Getter
@Setter
public class EntityStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nameItem;
    private int quantity;
    private String serialNumber;
    private String additionalInfo;
    private String imageUrl;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String updatedBy;

}