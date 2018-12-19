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
            if (key == '#') {
                showProducts();
                TUI.startMenu(date);
            }
            if (M.isActive()) {
                showMMode();
                TUI.startMenu(date);
            }
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
        int selected = menu();
        //selectManProduct();
        TUI.showM();
    }


    private static void showProducts() {
        char key = 0;
        double coins = 0;
        int selected = menu();
        Product sel = products.get(selected);
        TUI.showFinalProduct(sel, coins);
        do {
            key = TUI.getKey();
            if (key == '#'){
                CoinAcceptor.ejectCoins();
                TUI.showTemporaryMessage("Canceled", 1000, true);
                TUI.showTemporaryMessage(coins + "ejected", 1000, false);
            }

            if (CoinAcceptor.hasCoin()) {
                CoinAcceptor.acceptCoin();
                coins++;
                TUI.showFinalProduct(sel, coins);
                if (coins/10 == sel.getValue()) {
                    whaitForDisposal(sel);
                    key = '#';
                }
            }
        } while(key != '#');
    }

    private static void whaitForDisposal(Product selected) {
        TUI.showTopMessage("Collect Product", true);
        TUI.showBottomMessage(selected.getName(), false);
        Dispenser.dispense(selected.getId());
        TUI.showTemporaryMessage("Thanks for", 1000, true);
        TUI.showTemporaryMessage("buying :)", 1000, false);
        selected.setQuantity(selected.getQuantity()-1);
    }

    private static int menu() {
        int selected = 0;
        String keys = new String("00");
        for (; ; ) {
            int pos1 = Integer.parseInt(keys.substring(keys.length()-2, keys.length()));
            int checkedPosition = pos1 > products.get(products.size()-1).getId() ? pos1%10 : pos1;
            TUI.showProduct( products.get(checkedPosition ));
            char key_char;
            do {
                key_char = TUI.getKey();
                if (key_char == '#') break;

                if (key_char >= '0' && key_char <= '9')
                    keys += key_char;

            } while(key_char < '0' || key_char > '9');
            if (key_char == '#') {
                selected = checkedPosition;
                break;
            }
        }
        return selected;
    }



}
