package org.example;

import java.io.FileWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        ArrayList<Filmes> filmes = FWriter.carregarFilmes();
        Scanner terminal = new Scanner(System.in);

        pl pularl = new pl();

        menuprincipal:
        while (true) {

            while (true) {

                System.out.println("\n\n\n\n\n");
                System.out.println("╔══════════════════════════╗");
                System.out.println("║                          ║");
                System.out.println("║    SHELBY COMPANY LTD.   ║");
                System.out.println("║  The Small Heath Rifles  ║");
                System.out.println("║                          ║");
                System.out.println("╠══════════════════════════╣");
                System.out.println("║                          ║");
                System.out.println("║     C I N E M I N H A    ║");
                System.out.println("║       R E S E N H A      ║");
                System.out.println("║                          ║");
                System.out.println("║       ── 365 DNI ──      ║");
                System.out.println("║                          ║");
                System.out.println("╚══════════════════════════╝");
                System.out.println("");
                System.out.println("[1] Consulta de Filmes No Cinema");
                System.out.println("[2] Filmes Assistidos");
                System.out.println("[3] Sair");
                String rFilmes = "";

                App consulta = new App();

                bl pararWhile = new bl();

                while (true) {

                    if (pararWhile.parar == true) {
                        rFilmes = "";
                        break;
                    }

                    rFilmes = terminal.nextLine();

                    if (rFilmes.equalsIgnoreCase("1")) {
                        boolean voltar = consulta.xiri();
                        if (voltar) {
                            continue menuprincipal;
                        }
                    } else if (rFilmes.equalsIgnoreCase("2")) {
                        pl.pularLinha(1000);
                        break;
                    } else if (rFilmes.equalsIgnoreCase("3")) {
                        System.out.println("Encerrando...... By Order of the Peaky Blinders!");
                        Thread.sleep(1000);
                        System.exit(0);
                    } else {
                        System.out.println("\n");
                        System.out.println("Resposta inválida.");
                        System.out.println("\n");
                    }

                }
                if (pararWhile.parar == true) {
                    pararWhile.parar = false;
                    continue;
                } else {
                    break;
                }
            }
            System.out.println("[1] Registrar Novo Filme");
            System.out.println("\n");
            System.out.println("[2] Listar Filmes Assistidos");
            System.out.println("\n");
            System.out.println("[3] Voltar");
            pl.pularLinha(5);
            String resposta = terminal.nextLine();

            if (resposta.equalsIgnoreCase("1")) {

                Filmes assistidos = new Filmes();

                System.out.println("Digite o nome do filme:");
                System.out.println("\n");
                assistidos.nome = terminal.nextLine();
                System.out.println("\n");

                //kayla Axé ioruba

                System.out.println("Digite a data em que assistiu:");
                System.out.println("\n");
                assistidos.data = terminal.nextLine();
                System.out.println("\n");

                System.out.println("Digite a sua nota (de 1 a 10):");
                System.out.println("\n");
                assistidos.avaliacao = terminal.nextDouble();
                terminal.nextLine();
                System.out.println("\n");

                System.out.println("Digite uma breve resenha sobre o filme, ou deixe em branco");
                System.out.println("\n");
                assistidos.resenha = terminal.nextLine();
                System.out.println("\n");

                filmes.add(assistidos);
                FilmesWriter.salvarFilmes(filmes);

                Thread.sleep(1000);
                // que toda umbanda do inferno atinja a Kayla e que ela fique possuida durante a madrugada ae
            } else if (resposta.equalsIgnoreCase("2")) {

                for (Filmes f : filmes) {
                    System.out.println("┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐");
                    System.out.println("  Nome     : " + f.nome);
                    System.out.println("  Assistiu : " + f.data);
                    System.out.println("  Nota     : " + f.avaliacao);
                    System.out.println("  Resenha  : " + f.resenha);
                    System.out.println("└────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘");
                }

                System.out.println("\n");
                System.out.println("Aperte ENTER para voltar ao início");
                System.out.println("\n");
                terminal.nextLine();
            } else if (resposta.equalsIgnoreCase("3")) {
                System.out.println("Voltando!");
                Thread.sleep(1000);
                continue menuprincipal;
            }

        }


    }

}