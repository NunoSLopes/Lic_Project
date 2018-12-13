import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class App {

    private static String date;

    public static void main(String[] args) {

        List<Product> products = ProductService.getProducts();


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

        }
        TUI.startMenu(date);
    }

    private static void showProducts() {
        //TUI.showProduct();
    }



}
