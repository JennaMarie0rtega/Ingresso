package org.example;

import java.io.FileWriter;
import java.util.ArrayList;

public class FilmesWriter {

    static void salvarFilmes(ArrayList<Filmes> filmes) {
        try {
            FileWriter fw = new FileWriter("assistidos.txt");
            for (Filmes f : filmes) {
                fw.write(f.nome + "|" + f.data + "|" + f.avaliacao + "|" + f.resenha + "\n");
            }
            fw.close();
            System.out.println("Filme adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao salvar. " + e.getMessage());
        }
    }

}