import isel.leic.utils.Time;

public class TUI {


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
    public static void writeBig(String message) {
        for(int i = 0; i > 15-message.length(); i--) {
            LCD.clear();
            LCD.cursor(0, 0);
            LCD.write( message.substring(-i));
            Time.sleep(250);
        }
    }


    public static void showProduct(Product product, boolean arrow) {
        showTopMessage(product.getName(), true);

        char right = 126;
        char left = 127;

        LCD.cursor(1,0);
        LCD.write(String.format("%02d", product.getId() ) + ( arrow ? (char)left + "" + (char)right : ":"));
        System.out.println(String.format("%02d", product.getId() ) + ( arrow ? (char)left + "" + (char)right : ":"));

        LCD.cursor(1,6);
        String quantity = "";
        if (product.getQuantity() == 0) quantity = "#--";
        else quantity = String.format("#%02d", product.getQuantity());
        LCD.write(quantity);

        LCD.cursor(1,12);
        LCD.write( String.format("%.1f", product.getValue() ) + "E" );

        if (!arrow) LCD.cursor(1,1);
    }

    public static void changeProduct(Product product, String value) {
        showTopMessage(product.getName(), true);

        LCD.cursor(1,0);
        LCD.write(String.format("%02d", product.getId() ) + ":");

        LCD.cursor(1,6);
        if (value.length() == 0) LCD.write("#??");
        else if (value.length() == 1) LCD.write("#?" + value);
        else LCD.write("#" + value);

        LCD.cursor(1,12);
        LCD.write( String.format("%.1f", product.getValue() ) + "E" );

        LCD.cursor(1,7);
    }

    public static void showFinalProduct(Product product, double coins) {
        showTopMessage(product.getName(), true);
        LCD.cursor(1,0);

        LCD.cursor(1,6);
        LCD.write(String.format("%.1f", product.getValue() - (coins/10) ) + "E");
        LCD.cursor(1,17); //hide cursor
    }

    public static void showM() {
        LCD.clear();
        LCD.write("Maintenance Mode");
        LCD.cursor(1,0);
        LCD.write("1-Ld 2-Rm 3-Off");
    }

    public static void showTopMessage(String message, boolean clear) {
        if (clear) LCD.clear();

        int centerText = (LCD.COLS - message.length())/2;

        LCD.cursor(0, centerText);
        LCD.write( message);
    }

    public static void showBottomMessage(String message, boolean clear) {
        if (clear) LCD.clear();

        int centerText = (LCD.COLS - message.length())/2;

        LCD.cursor(1, centerText);
        LCD.write( message);
    }

    public static void showTemporaryMessage(String message, int time, boolean top) {
        LCD.clear();
        if (top) showTopMessage(message, true);
        else showBottomMessage(message, false);
        Time.sleep(time);
    }

    public static void turnOff() {
        LCD.clear();
        LCD.cursor(0,0);
        for(int i = 0; i <16; i++ ) {
            LCD.write((char)255);
        }
    }
}
