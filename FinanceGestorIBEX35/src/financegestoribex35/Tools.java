/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package financegestoribex35;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Italo
 */
public class Tools {
    
    public static boolean esFloat(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace(",", ".");
        try{
            Float.parseFloat(texto);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    
    public static Float StringToFloat(String texto){
        texto = texto.replace(".", "");
        texto = texto.replace(",", ".");
        return Float.valueOf(texto);
    }
    
    public static String floatToString(Float numero){
        String resultado = String.valueOf(numero);
        resultado = resultado.replace(".", ",");
        int posPunto = resultado.indexOf(",");
        if (posPunto == -1)
            posPunto = resultado.length();
        int contador = 0;
        for (int i = posPunto-1; i >= 0; i--) {
            if (contador==2){
                resultado = resultado.substring(0, i) + "." + resultado.substring(i, resultado.length());
                contador = 0;
            }
            else
                contador++;
        }
        if(resultado.charAt(0) == '.')
            resultado = resultado.substring(1);
        return resultado;
    }
    
    public static boolean fechaVencida(String fecha){
        GregorianCalendar fechaOpcion = new GregorianCalendar();
        String fechaTraducida = darFormatoFecha(fecha);
        fechaOpcion.set(Tools.StringToInteger(fechaTraducida.substring(0, 4)), 
                (Tools.StringToInteger(fechaTraducida.substring(4,6))-1), 
                Tools.StringToInteger(fechaTraducida.substring(6)));
        return fechaOpcion.before(new GregorianCalendar());
    }
    
    public static String getFechaActual(){
        GregorianCalendar fechaActual = new GregorianCalendar();
        String resultado;
        int dato = fechaActual.get(Calendar.DAY_OF_MONTH);
        if(dato < 10)
            resultado = "0" + String.valueOf(dato);
        else
            resultado = String.valueOf(dato);
        
        resultado += " " + getMesAlfabeto(fechaActual.get(Calendar.MONTH));
        resultado += " " + String.valueOf(fechaActual.get(Calendar.YEAR));
        return resultado;
    }
    
    public static int StringToInteger(String texto){
        return Integer.parseInt(texto);
    }
    
    public static boolean esInteger(String texto){
        try{
            Integer.parseInt(texto);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public static String darFormatoFecha(String fechaPalabras){
        String fecha = fechaPalabras.toString();
        if(fecha.length()==11)
        return fecha.substring(7, 11) + "" + getMesNumerico(fecha.substring(3, 6)) 
                + "" + fecha.substring(0, 2) ;
        return ("0" + fecha.substring(5, 10) + "" + 
                getMesNumerico(fecha.substring(2, 5)) 
                + "" + fecha.substring(0, 1)) ;
    }
    
    public static String getMesNumerico(String fecha){
        if(fecha.equals("ene")) return "01";
        if(fecha.equals("feb")) return "02";
        if(fecha.equals("mar")) return "03";
        if(fecha.equals("abr")) return "04";
        if(fecha.equals("may")) return "05";
        if(fecha.equals("jun")) return "06";
        if(fecha.equals("jul")) return "07";
        if(fecha.equals("ago")) return "08";
        if(fecha.equals("sep")) return "09";
        if(fecha.equals("oct")) return "10";
        if(fecha.equals("nov")) return "11";
        return "12";
    }

    private static String getMesAlfabeto(int mes) {
        if(mes==0) return "ene";
        if(mes==1) return "feb";
        if(mes==2) return "mar";
        if(mes==3) return "abr";
        if(mes==4) return "may";
        if(mes==5) return "jun";
        if(mes==6) return "jul";
        if(mes==7) return "ago";
        if(mes==8) return "sep";
        if(mes==9) return "oct";
        if(mes==10) return "nov";
        return "dic";
    }
    
    
}
