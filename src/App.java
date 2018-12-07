import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Product> products = ProductService.getProducts();

        TUI.init( products );
        TUI.startMenu();
        int key = 0;
        for(;;) {
            int value = Character.getNumericValue(TUI.whaitUser());
            if (key * 10 < ProductService.getLastProduct().getId())
                key = ( key * 10 ) + value;
            else
                key = value;
            TUI.showProduct(key);
        }

    }

}
