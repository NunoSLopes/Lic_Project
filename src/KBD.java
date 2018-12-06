import isel.leic.utils.Time;

public class KBD {

    public static final char NONE = 0;
    private static final int keys = 0x0F;
    private static final int Kval = 0x10;
    private static final int ack = 0x01;

    public static void main(String[] args) {
        init();
        for(;;){
            char key = getKey();

            Time.sleep(10);

            if(key != NONE)
                System.out.println(key);
            else
                HAL.clrBits(ack);

            if(key == '*')
                break;
        }



    }
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

        if (!HAL.isBit(Kval)) return NONE;
        HAL.setBits(ack);
        return chars[HAL.readBits(keys)];
    }

    public static char waitKey ( long timeout) {

        long limit = System.currentTimeMillis() + timeout;

        while (System.currentTimeMillis() < limit) {
            if(getKey()!= NONE) return getKey();
        }
        return NONE;
    }
    }



