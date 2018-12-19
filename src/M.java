public class M {

    private static final int M_MASK = 0x80;

    public static boolean isActive() {
        return HAL.isBit(M_MASK);
    }

}
