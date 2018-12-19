public class Product {

    private int id;
    private String name;
    private int quantity;
    private double value;

    public Product(String stringToParse) {
        this.parser(stringToParse);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    private void parser(String stringToParse) {
        String[] split = stringToParse.split(";");
        this.id = Integer.parseInt(split[0]);
        this.name = split[1];
        this.quantity = Integer.parseInt(split[2]);
        this.value = Integer.parseInt(split[3]) * 0.10;
    }

    @Override
    public String toString() {
        return id + ";"
                + name + ";"
                + quantity + ";"
                + value + ";";
    }
}
