package misyuro.com.testApplicationDCh.repository;

import misyuro.com.testApplicationDCh.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MySqlRepository extends JpaRepository<Shop, Integer> {
}
