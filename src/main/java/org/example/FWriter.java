package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class FWriter {
    static ArrayList<Filmes> carregarFilmes() throws InterruptedException {
        ArrayList<Filmes> filmes = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("assistidos.txt"));
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split("\\|");
                if (partes.length == 4) {
                    Filmes f = new Filmes();
                    f.nome = partes[0];
                    f.data = partes[1];
                    f.avaliacao = Double.parseDouble(partes[2]);
                    f.resenha = partes[3];
                    filmes.add(f);

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nenhum arquivo encontrado, criando um...");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Erro ao carregar. " + e.getMessage());
            Thread.sleep(1000);
        }
        return filmes;
    }
}



