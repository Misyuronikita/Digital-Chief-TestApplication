package misyuro.com.testApplicationDCh.controller;


import jakarta.persistence.criteria.CriteriaBuilder;
import misyuro.com.testApplicationDCh.model.Product;
import misyuro.com.testApplicationDCh.repository.ProductRepository;
import misyuro.com.testApplicationDCh.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/get-products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/get-products/{id}")
    public Product getProductById(@PathVariable("id") Integer id) {
        return productRepository.findById(id).get();
    }

    @DeleteMapping("/remove/product/{id}")
    public boolean deleteProduct(@PathVariable("id") Integer id) {
        if (!productRepository.findById(id).equals(Optional.empty())) {
            productRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("/update/product/{id}")
    public Product updateProduct(@PathVariable("id") Integer id,
                                 @RequestBody Map<String, String> body){
        Product current = productRepository.findById(id).get();

        current.setName(body.get("name"));
        current.setPrice(Double.parseDouble(body.get("price")));
        current.setCategory(body.get("category"));
        current.setDescription(body.get("description"));
        current.setShop_id(Integer.parseInt(body.get("shop_id")));

        productRepository.save(current);

        return current;
    }

    @PostMapping("add-product")
    public Product addProduct(@RequestBody Map<String, String> body){
        String name = body.get("name");
        Double price = Double.parseDouble(body.get("price"));
        String category = body.get("category");
        String description = body.get("description");
        Integer shop_id = Integer.parseInt(body.get("shop_id"));

        Product product = new Product(name, price, category, description, shop_id);
        return productRepository.save(product);
    }

}
