package entity;

public class Product {
    protected int id;
    protected String productname;
    protected int price;
    protected String desc;
    protected int amount;

    public Product() {
    }

    public Product(int id, String productname, int price, String desc, int amount) {
        this.id = id;
        this.productname = productname;
        this.price = price;
        this.desc = desc;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
