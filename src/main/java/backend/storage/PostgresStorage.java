package backend.storage;

import backend.domain.ListInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresStorage implements Storage {

    //private static final String HOST = "0.0.0.0";//"postgres";
    //private static final int PORT = 32768;//5432;

    private static final String HOST = "postgres";
    private static final int PORT = 5432;

    private Connection createConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://" + HOST + ":" + PORT + "/postgres", "postgres", "postgres");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }

    @Override
    public String lookupMeaning(String word) {
        try {
            final Connection connection = createConnection();
            final String sql = "SELECT meaning FROM dictionary WHERE word = ?;";
            final PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, word.toLowerCase());
            final ResultSet rs = stmt.executeQuery();
            String meaning = "";
            if (rs.next()) {
                meaning = rs.getString("meaning");
            }
            rs.close();
            stmt.close();
            connection.close();
            return meaning;
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }

    @Override
    public void saveWord(String word, String meaning, SaveMode saveMode) {
        try {
            final Connection connection = createConnection();
            final String sql = "INSERT INTO dictionary (word, meaning) VALUES (?, ?);";
            final PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, word.toLowerCase());
            stmt.setString(2, meaning);
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }

    @Override
    public List<String> getAllLists() {
        try {
            final Connection connection = createConnection();
            final String sql = "SELECT name FROM lists ORDER BY created ASC, name ASC;";
            final PreparedStatement stmt = connection.prepareStatement(sql);
            final ResultSet rs = stmt.executeQuery();
            final List<String> lists = new ArrayList<>();
            while (rs.next()) {
                lists.add(rs.getString("name"));
            }
            rs.close();
            stmt.close();
            connection.close();
            return lists;
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }

    @Override
    public boolean addWordToCurrentList(String word) {
        return false;
    }

    @Override
    public boolean createList(String list) {
        try {
            final Connection connection = createConnection();
            final String sql = "INSERT INTO lists (name) VALUES (?);";
            final PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, list.toLowerCase());
            stmt.executeUpdate();
            stmt.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }

    @Override
    public ListInfo getListInfo(String list) {
        return null;
    }

    @Override
    public List<String> getWordsOfList(String list) {
        return null;
    }

    @Override
    public String getCurrentList() {
        try {
            final Connection connection = createConnection();
            final String sql = "SELECT l.name FROM lists l JOIN currentlist cl ON l.id = cl.list_id;";
            final PreparedStatement stmt = connection.prepareStatement(sql);
            final ResultSet rs = stmt.executeQuery();
            String currentlist = "";
            if (rs.next()) {
                currentlist = rs.getString("name");
            }
            rs.close();
            stmt.close();
            connection.close();
            return currentlist;
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }

    @Override
    public boolean setCurrentList(String list) {
        try {
            final Connection connection = createConnection();
            final String sql1 = "DELETE FROM currentlist;";
            final PreparedStatement stmt1 = connection.prepareStatement(sql1);
            stmt1.executeUpdate();
            stmt1.close();
            final String sql2 = "INSERT INTO currentlist (SELECT id FROM lists WHERE name = ?);";
            final PreparedStatement stmt2 = connection.prepareStatement(sql2);
            stmt2.setString(1, list.toLowerCase());
            stmt2.executeUpdate();
            stmt2.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Unexpected exception!", e);
        }
    }
}
