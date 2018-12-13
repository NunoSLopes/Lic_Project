import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class App {

    private static String date;
    private static List<Product> products;

    public static void main(String[] args) {

        products = ProductService.getProducts();


        TUI.init();

        for(;;) {
            if ( calcTime() )
                TUI.startMenu(date);
            int key = TUI.getKey();
            if (key == '#') showProducts();
            if (M.isActive()) showMMode();
        }

    }

    private static boolean calcTime() {
        if (date == null) {
            date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return true;
        }
        String newDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        if ( date.equals(newDate) ) return false;

        date = newDate;
        return true;
    }

    private static void showMMode() {
        TUI.showM();
        while (M.isActive()) {
            char key = TUI.getKey();
            switch (key) {
                case '1':
                    mLoad();break;
                case '2':
                    //mRemove();break;
                case '3':
                    //mExit();break;
                default: //do nothing
            }
        }
        TUI.startMenu(date);
    }

    private static void mLoad() {
        String keys = new String("00");
        for (; ; ) {
            System.out.println(keys);
            int pos = Integer.parseInt(keys.substring(keys.length()-2, keys.length()));
            TUI.showProduct( products.get(pos > products.get(products.size()-1).getId() ? pos%10 : pos ));
            char key_char;
            do {
                key_char = TUI.getKey();

                if (key_char >= '0' && key_char <= '9')
                    keys += key_char;
            } while(key_char < '0' || key_char > '9');
            //selectManProduct();
        }
    }


    private static void showProducts() {
        //TUI.showProduct();
    }



}
