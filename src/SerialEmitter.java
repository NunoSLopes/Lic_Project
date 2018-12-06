import isel.leic.utils.Time;

// Envia tramas para os diferentes módulos Serial Receiver.
public class SerialEmitter {

    private static int SDX = 0x00;  //inport
    private static int SCLK = 0x00; //inport
    private static int BUSY = 0x00; //outport

    public enum Destination {
        Dispenser,
        LCD
    };

    // Inicia a classe
    public static void init() {

    }

    // Envia uma trama para o SerialReceiver identificado o destino em addr e os bits de dados em ‘data’.
    public static void send(Destination addr, int data) {
        //if (isBusy()) return;
        start();
        int size = (addr == Destination.Dispenser ? 6 : 11 );
        int parity = 0;


        if (addr == Destination.Dispenser) HAL.clrBits(SDX);
        else HAL.setBits(SDX);

        HAL.setBits(SCLK);

        for (int i = 0; i < size - 1; i++) {
            int bit = data & (0x01<<i);
            if(bit>0 ) {
                HAL.setBits(SDX);
                parity++;
            }
            else HAL.clrBits(SDX);
            Time.sleep(1);
            HAL.clrBits(SCLK);
            Time.sleep(1);
            HAL.setBits(SCLK);
        }

        if (parity % 2 == 0) HAL.setBits(SDX);
        else HAL.clrBits(SDX);

        Time.sleep(1);
        HAL.clrBits(SCLK);
        Time.sleep(1);
        HAL.setBits(SCLK);
        HAL.setBits(SDX);
        Time.sleep(1);
        HAL.clrBits(SCLK);

    }

    // Retorna true se o canal série estiver ocupado
    public static boolean isBusy() {
        return HAL.isBit(BUSY);
    }

    private static void start() {
        HAL.writeBits(SDX, 0x01);
        HAL.writeBits(SCLK, 0x00);
        Time.sleep(1);
        HAL.writeBits(SDX, 0x00);
    }
}
