import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductService {

    public static List<Product> products = new ArrayList<>();
    private static final String FILE_NAME = "PRODUCTS.txt";

    public static void getProductsFromFile() {
        try {
            Scanner scanner = new Scanner( new File(FILE_NAME) );
            while (scanner.hasNext()) {
                products.add(new Product(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Product> getProducts() {
        getProductsFromFile();
        return products;
    }

    public static void updateFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Product product : products) {
            writer.write(product.toString() + '\n');
        }
        writer.close();
    }

    public static Product getLastProduct() {
        return products.get(products.size()-1);
    }

}
