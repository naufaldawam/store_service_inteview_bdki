package interview.bdki.aigen.storingService.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import interview.bdki.aigen.storingService.entity.EntityStock;

public interface RepositoryStock extends JpaRepository<EntityStock, Long> {

    List<EntityStock> findAll();

    List<EntityStock> findByNameItemAndSerialNumber(String name, String serialNumber);

    EntityStock findByNameItem(String nameItem);

    EntityStock findBySerialNumber(String serialNumber);

    void deleteBySerialNumber(String serialNumber);

}
