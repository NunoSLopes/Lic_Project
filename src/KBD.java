public class KBD {

    public static final char NONE = 0;
    private static final int keys = 0x0F;
    private static final int Dval = 0x10;
    private static final int ack = 0x04;

    private static final char[] chars = {
            '1', '4', '7', '*',
            '2', '5', '8', '0',
            '3', '6', '9', '#'
    };


    public static void init() {
        HAL.init();
    }

    public static char getKey(){
        char key = NONE;

        if (!HAL.isBit(Dval)) return NONE;
        HAL.setBits(ack);
        key =  chars[HAL.readBits(keys)];
        while(HAL.isBit(Dval));
        HAL.clrBits(ack);
        return key;
    }

    public static char waitKey ( long timeout) {

        long limit = System.currentTimeMillis() + timeout;

        while (System.currentTimeMillis() < limit) {
            if(getKey()!= NONE) return getKey();
        }
        return NONE;
    }
}



