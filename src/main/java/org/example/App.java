package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class App {

    public static boolean xiri() throws Exception {

        Scanner terminal = new Scanner(System.in);
        bl pararWhile = new bl();
        System.out.println("\n\n\n\n\n");

        while (true) {

            date calendario = new date();
            calendario.seletorData();
            String dataForm1 = calendario.getDataFormatada();

            // --- SEÇÃO DE FILTROS ---
            System.out.println("\n--- FILTROS (Aperte ENTER para pular e mostrar tudo) ---");
            System.out.print("Filtrar por nome do filme: ");
            String filtroNome = terminal.nextLine();

            System.out.print("Filtrar por gênero (Ex: Terror): ");
            String filtroGenero = terminal.nextLine();

            System.out.print("Filtrar por formato (Ex: Dublado): ");
            String filtroFormato = terminal.nextLine();
            // ------------------------

            String url = "https://api-content.ingresso.com/v0/sessions/city/1/theater/131/partnership/home/groupBy/sessionType?date=" + dataForm1;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(response.body());

                System.out.println("\n======================================");
                System.out.println("      PROGRAMAÇÃO DE UCI - " + dataForm1);
                System.out.println("======================================");

                boolean achouAlgumFilme = false;

                for (JsonNode dia : rootNode) {
                    JsonNode listaDeFilmes = dia.path("movies");

                    for (JsonNode filme : listaDeFilmes) {
                        String titulo = filme.path("title").asText("Título Indisponível");

                        // Processamento de gêneros
                        String generos = "";
                        JsonNode listaGeneros = filme.path("genres");
                        for (JsonNode genero : listaGeneros) {
                            generos += genero.asText() + " / ";
                        }
                        if (!generos.isEmpty()) {
                            generos = generos.substring(0, generos.length() - 3);
                        } else {
                            generos = "Não informado";
                        }

                        JsonNode listaTiposSessao = filme.path("sessionTypes");

                        for (JsonNode tipoSessao : listaTiposSessao) {
                            JsonNode listaSessoes = tipoSessao.path("sessions");

                            for (JsonNode sessao : listaSessoes) {
                                String horario = sessao.path("time").asText("00:00");
                                String sala = sessao.path("room").asText("Não informada");

                                String formato = "";
                                for (JsonNode t : sessao.path("type")) {
                                    formato += t.asText() + " ";
                                }
                                formato = formato.trim();

                                // --- APLICAÇÃO DOS FILTROS ---
                                boolean passaNome = filtroNome.isEmpty() || titulo.toLowerCase().contains(filtroNome.toLowerCase());
                                boolean passaGenero = filtroGenero.isEmpty() || generos.toLowerCase().contains(filtroGenero.toLowerCase());
                                boolean passaFormato = filtroFormato.isEmpty() || formato.toLowerCase().contains(filtroFormato.toLowerCase());

                                if (passaNome && passaGenero && passaFormato) {
                                    achouAlgumFilme = true;
                                    System.out.println("Filme:   " + titulo);
                                    System.out.println("Gênero:  " + generos);
                                    System.out.println("Horário: " + horario);
                                    System.out.println("Sala:    " + sala);
                                    System.out.println("Formato: " + formato);
                                    System.out.println("-------------------------");
                                }
                            }
                        }
                    }
                }

                if (!achouAlgumFilme) {
                    System.out.println("Nenhum filme encontrado para os filtros aplicados......");
                }

            } catch (Exception e) {
                System.out.println("Erro ao solicitar dados...");
                e.printStackTrace();
            }

            System.out.println("\n[1] Nova consulta ");
            System.out.println("[2] Voltar ao Menu");
            String resposta = terminal.nextLine();

            if (resposta.equalsIgnoreCase("1")) {
              continue;
            } else if (resposta.equals("2")) {
                System.out.println("Voltando!");
                pararWhile.parar = true;
                Thread.sleep(500);
                return true;
            }return false;
        }
    }
}