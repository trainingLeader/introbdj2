package com.introbd.view;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.introbd.model.Game;

public class GameView {
    private Scanner scanner;

    public GameView() {
        scanner = new Scanner(System.in);
    }
    public int showMenu() {
        //clearConsole();
        System.out.println("1. Añadir juego");
        System.out.println("2. Listar juegos");
        System.out.println("3. Actualizar juego");
        System.out.println("4. Eliminar juego");
        System.out.println("5. Salir");
        System.out.print("Selecciona una opción: ");
        return scanner.nextInt();
    }
    public Game getGameDetails() {
        scanner.nextLine();
        System.out.println("Nombre del juego: ");
        String name = scanner.nextLine();
        return new Game(0, name);
    }
    public int getGameId() {
        System.out.print("ID del juego: ");
        return scanner.nextInt();
    }
    public void showGames(List<Game> games) {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        int pageSize = 3;
        int totalGames = games.size();
        int totalPages = (int) Math.ceil((double) totalGames / pageSize);
        String leftAlignFormat = "| %-4d | %-40s |%n";

        for (int page = 1; page <= totalPages; page++) {
            System.out.format("+------+------------------------------------------+%n");
            System.out.format("| ID   | Name                                     |%n");
            System.out.format("+------+------------------------------------------+%n");

            int start = (page - 1) * pageSize;
            int end = Math.min(start + pageSize, totalGames);
            for (int i = start; i < end; i++) {
                Game game = games.get(i);
                System.out.format(leftAlignFormat, game.getId(), game.getName());
            }

            System.out.format("+------+------------------------------------------+%n");
            System.out.println("Página " + page + " de " + totalPages);


            if (page <= totalPages) {
                System.out.println("Presiona Enter para mostrar la siguiente página...");
                try {

                            System.in.read();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                scanner.nextLine();
                clearConsole();
            }
        }
    }
    public void showMessage(String message) {
        System.out.println(message);
    }
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
