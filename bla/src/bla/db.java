package bla;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 

import java.sql.Statement; 

public class db { 

    public static void main(String[] args) { 
        new db().runMem(); 
    } 

    private void runMem() { 
        System.out.println("DB in Datei:"); 
        ResultSet rs = null; 
        Statement stmt = null; 
        Connection c = null; 
        try {
        	    
            String fileName = "datenDB";
            c = DriverManager.getConnection("jdbc:hsqldb:file:" + fileName + ";shutdown=true", "jb", "123");
            
            String query = "CREATE TABLE IF NOT EXISTS PUBLIC.USERS (spiel_id CHAR(4),  modus char(20), schwierigkeit CHAR(20),  minen CHAR(3), ergebnis CHAR(20));"; 
//            String query = "CREATE TABLE IF NOT EXISTS PUBLIC.USERS (spiel_id INTEGER NOT NULL, modus char(20), schwierigkeit CHAR(20));";
            
            stmt = c.createStatement(); 
            stmt.executeQuery(query); 
//            query = "INSERT INTO USERS (spiel_id, modus, schwierigkeit, minen, zeit_std, zeit_min, zeit_sek, ergebnis) VALUES ('0001', 'Klassik', 'Leicht', 10, 0, 0, 12, 'Sieg')";
            query = "INSERT INTO USERS (spiel_id, modus, schwierigkeit, minen, ergebnis) VALUES ('1', 'Klassik', 'Leicht', '10', 'Sieg')"; 
            rs = stmt.executeQuery(query); 
            rs.close(); 
            query = "SELECT * FROM USERS;"; 
            rs = stmt.executeQuery(query); 
            while (rs.next()) { 
                System.out.println(rs.getString("spiel_id")); 
                System.out.println(rs.getString("modus")); 
                System.out.println(rs.getString("schwierigkeit")); 
                System.out.println(rs.getString("minen")); 
                System.out.println(rs.getString("ergebnis")); 
            } 
            rs.close(); 
            stmt.close(); 
            c.close(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (rs != null && !rs.isClosed()) 
                    rs.close(); 
                if (stmt != null && !stmt.isClosed()) 
                    stmt.close(); 
                if (c != null && !c.isClosed()) 
                    c.close(); 
            } catch (SQLException e) { 
            } 
        } 
    } 
} 