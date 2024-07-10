package com.introbd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GameDao {
    private Connection connection;

    public GameDao() {
        try {
            Properties props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * Consulta SQL:
     * 
     * String query = "INSERT INTO games (name) VALUES (?)": Define una consulta SQL
     * parametrizada para insertar un nuevo
     * juego.
     * Preparar la Consulta:
     * PreparedStatement ps = connection.prepareStatement(query): Crea un
     * PreparedStatement a partir de la consulta SQL. El
     * uso de PreparedStatement evita inyecciones SQL y permite manejar parámetros
     * de manera segura.
     * 
     * Asignar Parámetros:
     * ps.setString(1, game.getName()): Establece el valor del primer parámetro
     * (nombre del juego).
     * Ejecutar la Consulta:
     * ps.executeUpdate(): Ejecuta la consulta de inserción en la base de datos.
     */
    public void addGame(Game game) throws SQLException {
        String query = "INSERT INTO games (name) VALUES (?)";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, game.getName());
        ps.executeUpdate();
    }

    /*
     * Crear una Lista de Juegos:
     * 
     * List<Game> games = new ArrayList<>(): Crea una lista para almacenar los
     * objetos Game.
     * Consulta SQL:
     * 
     * String query = "SELECT * FROM games": Define una consulta SQL para
     * seleccionar todos los juegos.
     * Crear un Statement y Ejecutar la Consulta:
     * 
     * Statement st = connection.createStatement(): Crea un objeto Statement.
     * ResultSet rs = st.executeQuery(query): Ejecuta la consulta y obtiene un
     * ResultSet con los resultados.
     * Iterar sobre los Resultados:
     * 
     * while (rs.next()): Itera sobre cada fila en el ResultSet.
     * Game game = new Game(rs.getInt("id"), rs.getString("name")): Crea un objeto
     * Game para cada fila.
     * games.add(game): Agrega cada objeto Game a la lista.
     * Devolver la Lista:
     * 
     * return games: Devuelve la lista de juegos.
     */
    public List<Game> getAllGames() throws SQLException {
        List<Game> games = new ArrayList<>();
        String query = "SELECT id,name FROM games";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Game game = new Game(rs.getInt("id"), rs.getString("name"));
            games.add(game);
        }
        return games;
    }

    /*
     * Consulta SQL:
     * 
     * String query = "UPDATE games SET name = ? WHERE id = ?": Define una consulta
     * SQL parametrizada para actualizar un
     * juego.
     * Preparar la Consulta:
     * 
     * PreparedStatement ps = connection.prepareStatement(query): Crea un
     * PreparedStatement.
     * Asignar Parámetros:
     * 
     * ps.setString(1, game.getName()): Establece el valor del nombre del juego.
     * ps.setInt(2, game.getId()): Establece el valor del ID del juego.
     * Ejecutar la Consulta:
     * 
     * ps.executeUpdate(): Ejecuta la consulta de actualización.
     */
    public void updateGame(Game game) throws SQLException {
        String query = "UPDATE games SET name = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, game.getName());
        ps.setInt(2, game.getId());
        ps.executeUpdate();
    }

    /*
     * Consulta SQL:
     * 
     * String query = "DELETE FROM games WHERE id = ?": Define una consulta SQL
     * parametrizada para eliminar un juego.
     * Preparar la Consulta:
     * 
     * PreparedStatement ps = connection.prepareStatement(query): Crea un
     * PreparedStatement.
     * Asignar Parámetros:
     * 
     * ps.setInt(1, id): Establece el valor del ID del juego.
     * Ejecutar la Consulta:
     * 
     * ps.executeUpdate(): Ejecuta la consulta de eliminación.
     */
    public void deleteGame(int id) throws SQLException {
        String query = "DELETE FROM games WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
