package com.code.dao;

import com.code.models.Player;
import com.code.models.Upgrade;
import com.code.utils.AlertUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;

public class PlayerDAO extends ConnectionFactory {
    private Connection connection;

    public PlayerDAO() {
        try {
            this.connection = createConnection("jdbc:postgresql://localhost:5432/rtte", "postgres", "senha123");
            System.out.println("Conectado com sucesso!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean create(Player player) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(
            "INSERT INTO players(name, coins, level) VALUES (?, ?, ?)");
        stmt.setString(1, player.getName());
        stmt.setInt(2, player.getCoins());
        stmt.setInt(3, player.getLevel());
        return stmt.execute();
    }

    public void listAll(ObservableList<Player> list) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM players");
        ResultSet result = stmt.executeQuery();
        while (result.next()) {
            list.add(new Player(result.getInt("id"), result.getString("name"),
                                result.getInt("coins"), result.getInt("level")));
        }
    }

    public int getByName(String name) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(
            "SELECT * FROM players WHERE name = ?");
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        if (result.next()) return result.getInt("id");
        throw new RuntimeException("This player does not exists!");
    }

    public Player getPlayerByName(String name) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(
            "SELECT * FROM players WHERE name = ?");
        stmt.setString(1, name);
        ResultSet result = stmt.executeQuery();
        if (result.next())
            return new Player(result.getInt("id"), result.getString("name"),
                              result.getInt("coins"), result.getInt("level"));
        throw new RuntimeException("This player does not exists!");
    }

    public Player getPlayerByID(int id) throws SQLException {
        PreparedStatement stmt = this.connection.prepareStatement(
            "SELECT * FROM players WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet result = stmt.executeQuery();
        if (result.next())
            return new Player(result.getInt("id"), result.getString("name"),
                              result.getInt("coins"), result.getInt("level"));
        throw new RuntimeException("This player does not exists!");
    }

    // ---------------------------------------------------------------
    // Coins
    // ---------------------------------------------------------------

    public void addCoins(Player player, int coins) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "UPDATE players SET coins = coins + ? WHERE id = ?");
            stmt.setInt(1, coins);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
    }

    public void decrementCoins(Player player, int coins) {
        try {
            // Garante que não vai abaixo de 0 direto no SQL
            PreparedStatement stmt = this.connection.prepareStatement(
                "UPDATE players SET coins = GREATEST(0, coins - ?) WHERE id = ?");
            stmt.setInt(1, coins);
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
    }

    public void setCoins(Player player, int newQ) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "UPDATE players SET coins = ? WHERE id = ?");
            stmt.setInt(1, Math.max(0, newQ));
            stmt.setInt(2, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
    }

    public void getCoins(Player player) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "SELECT coins FROM players WHERE id = ?");
            stmt.setInt(1, player.getId());
            ResultSet result = stmt.executeQuery();
            if (result.next()) player.setCoins(result.getInt("coins"));
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
    }

    // ---------------------------------------------------------------
    // Upgrade
    // ---------------------------------------------------------------

    public Upgrade getUpgrade(Player player) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "SELECT * FROM upgrade WHERE player_id = ?");
            stmt.setInt(1, player.getId());
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Upgrade upgrade = new Upgrade(
                    result.getInt("strength"),
                    result.getInt("health"),
                    result.getInt("recup")
                );
                player.setUpgrade(upgrade);
                return upgrade;
            }
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
        return null;
    }

    public void setUpgrade(Player player) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "UPDATE upgrade SET strength=?, health=?, recup=? WHERE player_id = ?");
            stmt.setInt(1, player.getUpgrade().getStrength());
            stmt.setInt(2, player.getUpgrade().getHealth());
            stmt.setInt(3, player.getUpgrade().getRecup());
            stmt.setInt(4, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
    }

    public void setUpgrade(Player player, Upgrade upgrade) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(
                "UPDATE upgrade SET strength=?, health=?, recup=? WHERE player_id = ?");
            stmt.setInt(1, upgrade.getStrength());
            stmt.setInt(2, upgrade.getHealth());
            stmt.setInt(3, upgrade.getRecup());
            stmt.setInt(4, player.getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            AlertUtils.error("Erro!", e);
        }
    }
}