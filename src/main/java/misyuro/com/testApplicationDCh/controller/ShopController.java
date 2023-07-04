package misyuro.com.testApplicationDCh.controller;

import misyuro.com.testApplicationDCh.model.Shop;
import misyuro.com.testApplicationDCh.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class ShopController {
    @Autowired
    ShopRepository shopRepository;

    @GetMapping("/get-shops")
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @GetMapping("/get-shops/{id}")
    public Shop getShopById(@PathVariable("id") Integer id) {
        return shopRepository.findById(id).get();
    }

    @DeleteMapping("/remove/shop/{id}")
    public boolean deleteShop(@PathVariable("id") Integer id) {
        if(!shopRepository.findById(id).equals(Optional.empty())){
            shopRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update/shop/{id}")
    public Shop updateShop(@PathVariable("id") Integer id,
                           @RequestBody Map<String, String> body){
        Shop current = shopRepository.findById(id).get();
        current.setName(body.get("name"));
        current.setAddress(body.get("address"));
        current.setOwner(body.get("owner"));
        current.setPhone(body.get("phone"));
        current.setAmount(Integer.parseInt(body.get("amount")));
        shopRepository.save(current);
        return current;
    }
    @PostMapping("/add-shop")
    public Shop addShop(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String address = body.get("address");
        String owner = body.get("owner");
        String phone = body.get("phone");
        int amount = Integer.parseInt(body.get("amount"));

        Shop shop = new Shop(name, address, owner, phone, amount);
        return shopRepository.save(shop);
    }

}
