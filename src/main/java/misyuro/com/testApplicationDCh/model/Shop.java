package misyuro.com.testApplicationDCh.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name; //Наименование магазина
    private String address; //Адрес расположения
    private String owner; //Владелец
    private String phone; //Номер телефона
    private Integer amount; //Количество магазинов в сети

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<Product>();



    public Shop(){};

    public Shop(String name, String address, String owner, String phone, int amount) {
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.phone = phone;
        this.amount = amount;
    }
    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

//    @Override
//    public String toString() {
//        return "Shop{" +
//                "name='" + name + '\'' +
//                ", address='" + address + '\'' +
//                ", owner='" + owner + '\'' +
//                ", phone='" + phone + '\'' +
//                ", amount=" + amount +
//                '}';
//    }
}
