import java.util.List;

public class TUI {

    private static List<Product> products;

    public static void init() {
        LCD.init();
        KBD.init();
    }

    public static void startMenu(String date) {
        LCD.clear();

        LCD.write("Vending Machine");
        LCD.cursor(1,0);
        LCD.write(date);
    }

    public static char getKey() {
        return KBD.getKey();
    }

    /**
     * Just in case we need to show bigger text
     * @param message
     */
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

    public static void showM() {
        LCD.clear();
        LCD.write("Maintenance Mode");
        LCD.cursor(1,0);
        LCD.write("1-Ld 2-Rm 3-Off");
    }
}
