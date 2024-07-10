package com.introbd.controller;

import java.sql.SQLException;
import java.util.List;

import com.introbd.model.Game;
import com.introbd.model.GameDao;
import com.introbd.view.GameView;

public class GameController {
    private GameView view;
    private GameDao dao;

    public GameController(GameView view, GameDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void start() {
        while (true) {
            int choice = view.showMenu();
            try {
                switch (choice) {
                    case 1:
                        addGame();
                        break;
                    case 2:
                        listGames();
                        break;
                    case 3:
                        updateGame();
                        break;
                    case 4:
                        deleteGame();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        view.showMessage("Opción no válida.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addGame() throws SQLException {
        Game game = view.getGameDetails();
        dao.addGame(game);
        view.showMessage("Juego añadido exitosamente.");
    }

    private void listGames() throws SQLException {
        List<Game> games = dao.getAllGames();
        view.showGames(games);
    }

    private void updateGame() throws SQLException {
        int id = view.getGameId();
        Game game = view.getGameDetails();
        game.setId(id);
        dao.updateGame(game);
        view.showMessage("Juego actualizado exitosamente.");
    }

    private void deleteGame() throws SQLException {
        int id = view.getGameId();
        dao.deleteGame(id);
        view.showMessage("Juego eliminado exitosamente.");
    }
}
