package edu.uet.imu.dictIMU.tools;

import edu.uet.imu.dictIMU.common.DictionaryManagement;
import edu.uet.imu.dictIMU.common.Word;
import edu.uet.imu.dictIMU.common.WordX;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.uet.imu.dictIMU.tools.ScriptRunner;

import javax.swing.*;

import static edu.uet.imu.dictIMU.common.TypeGroupManager.getCloseWord;
import static edu.uet.imu.dictIMU.common.TypeGroupManager.getType;
import static edu.uet.imu.dictIMU.common.TypeGroupManager.getTypeGroup;

public class SQLManager {
    public Connection con;
    public Statement st;
    public ResultSet rs;

    public SQLManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dictionary_imu", "root", "");
            st = con.createStatement();
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to get mysql driver: " + e);
        } catch (SQLException e) {
            System.err.println("Unable to connect to server: " + e);
        }
    }

    public void importFromSQL(DictionaryManagement dictionaryManagemer) {
        try {
            String query = "select word from tbl_edict";
            rs = st.executeQuery(query);
            while (rs.next()) {
                String wordTarget = rs.getString("word");
                String wordExplain = "";
                dictionaryManagemer.getDictionary().addWord(new Word(wordTarget, wordExplain));
                System.out.println(wordTarget);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromFile(String path) {
        ScriptRunner runner = new ScriptRunner(con, false, false);

        try {
            runner.runScript(new BufferedReader(new FileReader(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeWord(String word) {
        try {
            String query = "DELETE FROM tbl_edict WHERE word = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, word);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String searchWord(String word) {
        String wordExplain = new String();
        try {
            String sql = "select detail from tbl_edict WHERE word = '" + word + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                wordExplain = rs.getString("detail");
            }
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return wordExplain;
    }
}