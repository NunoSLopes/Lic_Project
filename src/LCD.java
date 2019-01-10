public class LCD { // Escreve no LCD usando a interface a 4 bits.

    public static final int LINES = 2, COLS = 16;// Dimensão do display.

    private static final int CLR_DISPLAY = 0x01;

    public static void main(String args[]) {
        HAL.init();
        SerialEmitter.init();
        LCD.init();
        /*LCD.write("Cambada de Burros");
        Time.sleep(5000);
        clear();
        LCD.write("Continuam");
        cursor(1,10);
        LCD.write("Burros");*/
        LCD.writeDATA(0x18);
    }

    // Escreve um byte de comando/dados no LCD
    private static void writeByte(boolean rs, int data) {
        data = (data << 1) + (rs ? 1 : 0);
        SerialEmitter.send(
                SerialEmitter.Destination.LCD,
                data);
    }

    // Escreve um comando no LCD
    private static void writeCMD(int data) {
        LCD.writeByte(false, data);
    }

    // Escreve um dado no LCD
    private static void writeDATA(int data) {
        LCD.writeByte(true, data);
    }

    // Envia a sequência de iniciação para comunicação a 8 bits.
    public static void init() {
        //Time.sleep(15);
        LCD.writeCMD(0x30);
        //Time.sleep(5);
        LCD.writeCMD(0x30);
        //Time.sleep(1);
        LCD.writeCMD(0x30);
        LCD.writeCMD(0x38);
        LCD.writeCMD(0x08);
        LCD.writeCMD(0x01);
        LCD.writeCMD(0x06);
        LCD.writeCMD(0x0F);
        System.out.println("acabou Inicializar");
    }

    // Escreve um caráter na posição corrente.
    public static void write(char c) {
        LCD.writeDATA(c);
    }

    // Escreve uma string na posição corrente.
    public static void write(String txt) {
        for (char c : txt.toCharArray()) {
            LCD.write(c);
        }
    }

    // Envia comando para posicionar cursor (‘lin’:0..LINES-1 , ‘col’:0..COLS-1)
    public static void cursor(int lin, int col) {
        int cod = 1<<7;
        cod += (lin == 0 ? 0x00 : 0x40) + col;
        writeCMD(cod);
    }

    // Envia comando para limpar o ecrã e posicionar o cursor em (0,0)
    public static void clear() {
        writeCMD(CLR_DISPLAY);
    }
}

