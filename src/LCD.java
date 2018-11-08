import isel.leic.UsbPort;
import isel.leic.utils.Time;

public class LCD { // Escreve no LCD usando a interface a 4 bits.

    private static final int LINES = 2, COLS = 16; // Dimensão do display.

    private static final int[][] position = new int [LINES][COLS];


    private static final int LSB = 0x0F;
    private static final int MSB = 0xF0;

    // Escreve um byte de comando/dados no LCD
    private static void writeByte(boolean rs, int data) {
        if (rs) {
            HAL.writeBits(MSB, data);
            Time.sleep(10);
            HAL.writeBits(LSB, data);
        }
    }

    // Escreve um comando no LCD
    private static void writeCMD(int data) {

    }

    // Escreve um dado no LCD
    private static void writeDATA(int data) {

    }

    // Envia a sequência de iniciação para comunicação a 4 bits.
    public static void init() {
        Time.sleep(15);
        HAL.writeBits(0xff, 0x30);
        HAL.writeBits(0xff, 0x30);
        Time.sleep(5);
        HAL.writeBits(0xff, 0x30);
        Time.sleep(1);
        HAL.writeBits(0xff, 0x30);
        HAL.writeBits(0xff, 0x38);
        HAL.writeBits(0xff, 0x08);
        HAL.writeBits(0xff, 0x01);
        HAL.writeBits(0xff, 0x05);
    }

    // Escreve um caráter na posição corrente.
    public static void write(char c) {

    }

    // Escreve uma string na posição corrente.
    public static void write(String txt) {

    }

    // Envia comando para posicionar cursor (‘lin’:0..LINES-1 , ‘col’:0..COLS-1)
    public static void cursor(int lin, int col) {

    }

    // Envia comando para limpar o ecrã e posicionar o cursor em (0,0)
    public static void clear() {

    }
}

