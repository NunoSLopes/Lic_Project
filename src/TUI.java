import isel.leic.utils.Time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TUI {

    private static List<Product> products;

    public static void init(List<Product> prods) {
        LCD.init();
        KBD.init();
        products = prods;
    }

    public static void startMenu() {
        LocalDateTime date = LocalDateTime.now();
        LCD.write("Vending Machine");
        LCD.cursor(1,0);
        LCD.write(date.format( DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm") ));
    }

    public static char whaitUser() {
        char key;
        do {
            key = KBD.getKey();
            Time.sleep(10);
        } while ( key == KBD.NONE);
        return key;
    }

    private static void writeBig(String message) {
        for(int i = 0; i > 15-message.length(); i--) {
            LCD.cursor(1,0);
            LCD.write(message.substring(-i));
        }
    }


    public static void showProduct(int key) {
        Product product = null;
        for (Product result : products) {
            if (result.getId() == key ) product = result;
        }

        if (product == null) {
            return;
        }
        System.out.println(key);

        LCD.clear();

        int centerText = (LCD.COLS - product.getName().length())/2;

        LCD.cursor(0, centerText);
        LCD.write( product.getName());
        LCD.cursor(1,0);
        LCD.write(product.getId() > 10 ? "" + product.getId() : "0" + product.getId());
        LCD.cursor(1,6);
        LCD.write("#"+product.getQuantity());
        LCD.cursor(1,12);
        LCD.write((product.getQuantity()*0.10) + "E");

        LCD.cursor(1,1);
    }
}
