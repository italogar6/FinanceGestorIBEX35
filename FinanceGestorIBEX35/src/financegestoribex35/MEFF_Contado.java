package financegestoribex35;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juan
 */
public class MEFF_Contado {
    
    private final String servernameIBEX =
            "http://www.meff.es/aspx/Financiero/Ficha.aspx?ticker=FIE";
    
    private final int ntrials=5;
    private final int timeout=1000; // 10 seconds
    
    public String Spot; //Precio subyacente/puntual del IBEX35, influye en las opciones
    public String Diferencia; //Float
    public String Anterior; //Float
    public String Maximo; //Float
    public String Minimo; //Float
    public String Fecha; //DD/MM/AAAA
    public String Hora; //--:--
    
    public MEFF_Contado(){  
    }
    
    public boolean getSpot(){
         
        int trial = ntrials;
        while(trial > 0){
            try {
                Document doc = Jsoup.connect(servernameIBEX).timeout(timeout).get();
                
                for(Element table : doc.getElementsByTag("table")){
                    Elements rows = table.getElementsByTag("tr");
                    if(rows.size()>0){
                        String head=rows.get(0).text();
                        if(head.substring(0, 4).compareTo("Últ.")==0){
                             Elements data = rows.get(1).getElementsByTag("td");
                             Spot = data.get(0).text();
                             Diferencia = data.get(2).text();
                             Maximo = data.get(4).text();
                             Minimo = data.get(5).text();
                             Anterior = data.get(6).text();
                             Fecha = data.get(7).text();
                             Hora = data.get(8).text();
                             return true;
                        }
                    }
                }
                try {
                    //Logger.getLogger(MEFF_Futuros.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.sleep(3000);
                    trial--;
                } catch (InterruptedException ex1) {
                    Logger.getLogger(MEFF_Contado.class.getName()).log(Level.SEVERE, null, ex1);
                } 
            } catch (IOException ex) {
                try {
                    //Logger.getLogger(MEFF_Futuros.class.getName()).log(Level.SEVERE, null, ex);
                    Thread.sleep(3000);
                    trial--;
                } catch (InterruptedException ex1) {
                    Logger.getLogger(MEFF_Contado.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        String resultado = "||||||||Contado||||||||\n";
        resultado += "Spot: "+ Spot +"\n";
        resultado += "Diferencia: "+ Diferencia +"\n";
        resultado += "Anterior: "+ Anterior +"\n";
        resultado += "Maximo: "+ Maximo +"\n";
        resultado += "Minimo: "+ Minimo +"\n";
        resultado += "Fecha: "+ Fecha +"\n";
        resultado += "Hora: "+ Hora;
        return resultado;
    }
}
