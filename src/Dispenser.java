public class Dispenser {

    private static final int DISPENSER = 0x80;

    // Inicia a classe, estabelecendo os valores iniciais.
    public static void init() {
        HAL.init();
    }

    // Envia comando para dispensar uma unidade de um produto
    public static void dispense(int productId) {
        CoinAcceptor.collectCoins();
        SerialEmitter.send(SerialEmitter.Destination.Dispenser, productId);
        while (SerialEmitter.isBusy());
    }

}
