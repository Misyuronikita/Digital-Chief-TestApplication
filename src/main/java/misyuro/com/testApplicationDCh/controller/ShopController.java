package misyuro.com.testApplicationDCh.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import misyuro.com.testApplicationDCh.model.Shop;
import misyuro.com.testApplicationDCh.repository.MySqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ShopController {
    @Autowired
    MySqlRepository mySqlRepository;

    @GetMapping("/get-shops")
    public List<Shop> getAllShops() {
        return mySqlRepository.findAll();
    }

    @GetMapping("/get-shops/{id}")
    public Shop getShopById(@PathVariable("id") Integer id) {
        return mySqlRepository.findById(id).get();
    }

    @DeleteMapping("/remove/{id}")
    public boolean deleteShop(@PathVariable("id") Integer id) {
        if(!mySqlRepository.findById(id).equals(Optional.empty())){
            mySqlRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update/{id}")
    public Shop updateShop(@PathVariable("id") Integer id,
                           @RequestBody Map<String, String> body){
        Shop current = mySqlRepository.findById(id).get();
        current.setName(body.get("name"));
        current.setAddress(body.get("address"));
        current.setOwner(body.get("owner"));
        current.setPhone(body.get("phone"));
        current.setAmount(Integer.parseInt(body.get("amount")));
        mySqlRepository.save(current);
        return current;
    }
    @PostMapping("/add")
    public Shop addShop(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String address = body.get("address");
        String owner = body.get("owner");
        String phone = body.get("phone");
        int amount = Integer.parseInt(body.get("amount"));

        Shop shop = new Shop(name, address, owner, phone, amount);
        return mySqlRepository.save(shop);
    }

}
