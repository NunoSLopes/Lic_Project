import isel.leic.UsbPort;

public class HAL { // Virtualiza o acesso ao sistema UsbPort

    public static void main(String[] args) {
        init();
    }
    // Inicia a classe
    public static void init(){

        clrBits(0x00);
    }

    // Retorna true se o bit tiver o valor lógico ‘1’
    public static boolean isBit(int mask){
        return (UsbPort.in() & mask) !=0;
    }

    // Retorna os valores dos bits representados por mask presentes no UsbPort
    public static int readBits(int mask){
        return UsbPort.in() & mask;
    }

    // Escreve nos bits representados por mask o valor de value
    public static int writeBits(int mask, int value){
        output = (mask & value) + (~mask & output);
        UsbPort.out(output);
        return output;
    }

    // Coloca os bits representados por mask no valor lógico ‘1’
    public static void setBits(int mask){
        writeBits(mask,0xFF);
    }

    // Coloca os bits representados por mask no valor lógico ‘0’
    public static void clrBits(int mask){

        writeBits(mask, 0x00);
    }

    private static int output;

}
