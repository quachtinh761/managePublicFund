/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.function.encryptMd5;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.derby.jdbc.EmbeddedDriver;

/**
 *
 * @author Nguyen Van Tinh
 */
public class DBTemplate {
    private String dbName;
    private final String LOCATEDB_STRING = encryptMd5.getStringMd5("data");
    private final Driver DERBYEMBEDDED_DRIVER = new EmbeddedDriver();
    private Connection conn;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static DefaultTableModel model = new DefaultTableModel();

    public DBTemplate(String dbName) {
        this.dbName = dbName;
    }
    
    public String getDbName(){
        return this.dbName;
    }
    
    public boolean getConnect(){
        try {
            DriverManager.registerDriver(DERBYEMBEDDED_DRIVER);
            conn = DriverManager.getConnection("jdbc:derby:" + LOCATEDB_STRING + ";create=true;user=admin;password=admin");
            conn.setAutoCommit(false);
            st = conn.createStatement();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
    public boolean closeConnect(){
        try {
            conn.close();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
    /**
     * @param tableName
     * @param params
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","filedType1(primary key if needed)");
     * params.put("fieldName2","filedType2 (not null)");
     * @return boolean
     **/
    public boolean createTable(String tableName, Map<String, String> params){
        try {
            String CREATE_TABLE = "";
            if (searchAllTable(tableName).getRowCount() > 0){
                    return false;
            }
            CREATE_TABLE += "CREATE TABLE " + encryptMd5.getStringMd5(tableName)  + "(";
            CREATE_TABLE = params.entrySet().stream().map((entry) -> entry.getKey() + " " + entry.getValue() + ",").reduce(CREATE_TABLE, String::concat);
            if (CREATE_TABLE.endsWith(",")){
                CREATE_TABLE = CREATE_TABLE.substring(0, CREATE_TABLE.length() - 1);
                CREATE_TABLE += "); ";
            }else{
                return false;
            }
            System.out.println(CREATE_TABLE);
            st.execute(CREATE_TABLE);
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","fieldValue1");
     * params.put("fieldName2","fieldValue2");
     * @param tableName
     * @param params
     * @return 
     **/
    public String insertTable(String tableName, Map<String, String> params) {
        //Open connection to write data
        String sql = "INSERT INTO @" + encryptMd5.getStringMd5(tableName) + "(";
        String key = "", value = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            key += "'" + entry.getKey() + "',";
            value += "'" + entry.getValue() + "',";
        }
        if (key.endsWith(",")){
            key = key.substring(0, key.length() - 1);
        }
        if (value.endsWith(",")){
            value = value.substring(0, value.length() - 1);
        }
        if (params.isEmpty()){
            return "1";
        }
        sql += key + ") VALUES (" + value + ")";
        JOptionPane.showMessageDialog(null, sql);
        
        try {
            st = conn.createStatement();
            st.execute(sql);
            return "OK";
        }catch (SQLException e){
            System.err.println(e.getCause());
            return e.toString();
        }
    }
    
    public static DefaultTableModel searchAllTable(String tableName){
        Vector header = new Vector();
        header.add("MaSP");
        header.add("Tên SP");
        model = new DefaultTableModel(header, 0);
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE 1 = 1;";
            rs = st.executeQuery(sql);
            while (rs.next())
            {
                Vector row = new Vector();
                row.add(rs.getString(1));
                row.add(rs.getString(2));
                model.addRow(row);
                //DefaultTableModel DataDisplay = model;
                //DataDisplay.set
            }
        }catch (Exception e){
        }
        return model;
    }
    
    public void runQuery(String sql){
        try {
            st = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex + "1");
        }
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(st == null);
        }
    }
}
