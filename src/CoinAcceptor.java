public class CoinAcceptor {

    private static final int COIN_ACCEPT = 0x10;
    private static final int COIN_COLLECT = 0x20;
    private static final int COIN_EJECT = 0x40;
    private static final int COIN_COIN = 0x20;

    public static void init() {
        HAL.init();
    }

    // Retorna true se foi introduzida uma nova moeda.
    public static boolean hasCoin() {
        return HAL.isBit(COIN_COIN);
    }

    // Informa o moedeiro que a moeda foi contabilizada.
    public static void acceptCoin() {
        HAL.setBits(COIN_ACCEPT);
        HAL.clrBits(COIN_ACCEPT);
    }

    // Devolve as moedas que estão no moedeiro.
    public static void ejectCoins() {
        HAL.setBits(COIN_EJECT);
        HAL.clrBits(COIN_EJECT);
    }

    // Recolhe as moedas que estão no moedeiro.
    public static void collectCoins() {
        HAL.setBits(COIN_COLLECT);
        HAL.clrBits(COIN_COLLECT);
    }
}
