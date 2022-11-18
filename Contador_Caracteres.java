
package tablashash;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.Normalizer;

public class Contador_Caracteres {
    public static void main(String[] args) {
        //Almacenamiento
        String words = "a-b-c-d-e-f-g-h-i-j-k-l-m-n-ñ-o-p-q-r-s-t-u-v-w-x-y-z-,-.-:-;";
        String[][] Tabla = new String[31][2];
        String[] auxil = words.split("-");
        for (int i = 0; i < auxil.length; i++) {
            Tabla[i][0] = auxil[i];
            Tabla[i][1] = "0";
        }
        //Lectura del archivo
        String ruta_Archivo = "C:\\Users\\ijgm9\\OneDrive\\Escritorio\\el_quijote.txt";
        String renglon;
         try {
            BufferedReader lector = new BufferedReader(new FileReader(ruta_Archivo));
            while ((renglon = lector.readLine()) != null) {    
                //Escaneo de lineas
                renglon =  renglon.toLowerCase();
                renglon = quitarAcentos(renglon);
                escanear(renglon, Tabla, words);
            }
             for (int i = 0; i < Tabla.length; i++) {
                 System.out.println(Tabla[i][0] + " [" + Tabla[i][1]+"]");
             }
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public static void escanear(String s, String[][] t, String w){
        for (int i = 0; i < s.length(); i++) {
            CharSequence cs = s.substring(i, i+1);
            if (w.contains(cs)) {
                aumentar(s.substring(i, i+1), t);
            }
        }
    }
    
    public static void aumentar(String c, String[][] t){
        int aux;
        for (int i = 0; i < t.length; i++) {
            if (t[i][0].equalsIgnoreCase(c)) {
                aux = Integer.parseInt(t[i][1]) + 1;
                t[i][1] = aux + "";
            }
        }
    }
    
    public static String quitarAcentos(String s){
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }
}

