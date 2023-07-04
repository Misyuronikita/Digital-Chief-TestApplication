package misyuro.com.testApplicationDCh.repository;

import misyuro.com.testApplicationDCh.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
