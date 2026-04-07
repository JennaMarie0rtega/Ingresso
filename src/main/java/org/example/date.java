package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class date {
    int diaSelect = 0;
    public String getDataFormatada() {
        LocalDate hoje = LocalDate.now();

        hoje = hoje.plusDays(diaSelect);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return hoje.format(formato);
   }

   public String seletorData() {

        Scanner terminal = new Scanner(System.in);

       LocalDate hj = LocalDate.now();
       DateTimeFormatter formatoHj = DateTimeFormatter.ofPattern("dd");


       for (int n = 0; n < 6; n++) {

           System.out.println(n+" - Dia " + hj.plusDays(n).format(formatoHj));

       }
       diaSelect = terminal.nextInt();
       return hj.format(formatoHj);
   }

}


