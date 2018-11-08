public class LCD { // Escreve no LCD usando a interface a 4 bits.

    private static final int LINES = 2, COLS = 16; // Dimensão do display.

    // Escreve um byte de comando/dados no LCD
    private static void writeByte(boolean rs, int data) ...

    // Escreve um comando no LCD
    private static void writeCMD(int data) ...

    // Escreve um dado no LCD
    private static void writeDATA(int data) ...

    // Envia a sequência de iniciação para comunicação a 4 bits.
    public static void init() ...

    // Escreve um caráter na posição corrente.
    public static void write(char c) ...

    // Escreve uma string na posição corrente.
    public static void write(String txt) ...

    // Envia comando para posicionar cursor (‘lin’:0..LINES-1 , ‘col’:0..COLS-1)
    public static void cursor(int lin, int col) ...

    // Envia comando para limpar o ecrã e posicionar o cursor em (0,0)
    public static void clear() ...
}

