package com.introbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.introbd.controller.GameController;
import com.introbd.model.GameDao;
import com.introbd.view.GameView;


public class Main {
    public static void main(String[] args) {
        GameView view = new GameView();
        GameDao dao = new GameDao();
        clearConsole();
        GameController controller = new GameController(view, dao);
        controller.start();
    }
    public static void clearConsole() {
        // Secuencia de escape ANSI para limpiar la consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}