package caesar;

import java.util.Scanner;

public class Caesar {

    public static void main(String[] args) {
        System.out.println("Qué desea hacer, Encriptar o Desencriptar? 'E' o 'D'?");
        try (Scanner sc = new Scanner(System.in)) {
            char option = sc.next().charAt(0);
            
            switch (option) {
                case 'E':
                    mensaje(sc);
                    posiciones();
                    encriptar(mensaje(sc),posiciones());
                    break;
                case 'D':
                    mensaje(sc);
                    posiciones();
                    desencriptar(mensaje(sc),posiciones());
                    break;
                default:
                    System.out.println("Bye-Bye!");
                    break;
            }
        }
    }
    
    public static String mensaje(Scanner sc){
        System.out.println("¿Qué mensaje desea permutar?\n");
        return sc.nextLine();
    }
    
    public static int posiciones(){
        System.out.println("Cuántas posiciones desea permutar?\n");
        Scanner pos = new Scanner(System.in);
        return pos.nextInt();
    }
    
    public static void encriptar(String mensaje, int num){
        char array[] = mensaje.toCharArray();
        for(int i=0;i<=mensaje.length();i++){
            array[i] = (char)(array[i]+(char)num);
        }
        System.out.println(String.valueOf(array));
    }
    
    public static void desencriptar(String mensaje,int num){
        char arrayD[] = mensaje.toCharArray();
        for(int i=0;i<=mensaje.length();i++){
            arrayD[i] = (char)(arrayD[i]-(char)num);
        }
        System.out.println(String.valueOf(arrayD));
    }
}