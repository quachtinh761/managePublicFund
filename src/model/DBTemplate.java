/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import common.function.encryptMd5;
import common.function.handleString;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.derby.jdbc.EmbeddedDriver;

/**
 *
 * @author Nguyen Van Tinh
 */
public class DBTemplate {
    private final String LOCATEDB_STRING = "src/" + encryptMd5.getStringMd5("data");
    private final Driver DERBYEMBEDDED_DRIVER = new EmbeddedDriver();
    private Connection conn;
    private static Statement st = null;
    private static ResultSet rs = null;
    private static DefaultTableModel model = new DefaultTableModel();
    private final int name = 0, type = 1;
    
    public boolean getConnect(){
        try {
            DriverManager.registerDriver(DERBYEMBEDDED_DRIVER);
            conn = DriverManager.getConnection("jdbc:derby:" + LOCATEDB_STRING + ";create=true;user=admin;password=admin");
            //conn.setAutoCommit(false);
            st = conn.createStatement();
            System.out.println("model.DBTemplate.getConnect() success");
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
    public boolean closeConnect(){
        try {
            if (conn != null){
                conn.close();
            }
            if (st != null){
                st.close();
            }
            if (rs != null){
                rs.close();
            }
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
    public boolean createTable(String tableName, List < String[] > params){
        try {
            String CREATE_TABLE = "";
            if ( this.isTableExist(tableName) ){
                return true;
            }
            CREATE_TABLE += "CREATE TABLE n" + encryptMd5.getStringMd5(tableName.toUpperCase()) + "(";
            for (int i = 0; i < params.size(); i++){
                CREATE_TABLE += params.get(i)[name] + " " + params.get(i)[type] + ",";
            }
            if (CREATE_TABLE.endsWith(",")){
                CREATE_TABLE = CREATE_TABLE.substring(0, CREATE_TABLE.length() - 1);
                CREATE_TABLE += ") ";
            }else{
                return false;
            }
            st.execute(CREATE_TABLE);
            System.out.println("model.DBTemplate.createTable() success");
        }catch (Exception ex){
            System.out.println(ex);
            return false;
        }
        return true;
    }
    
    public boolean isTableExist(String tableName) {
        try{
            String sql = "SELECT * FROM n" + encryptMd5.getStringMd5(tableName.toUpperCase()) + " WHERE 1 = 1";
            rs = st.executeQuery(sql);
        }catch(SQLException e){
            //System.out.println(e);
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
    public boolean insertTable(String tableName, Map<String, String> params) {
        //Open connection to write data
        String sql = "INSERT INTO n" + encryptMd5.getStringMd5(tableName.toUpperCase()) + "(";
        String key = "", value = "";
        for (Map.Entry<String, String> entry : params.entrySet()) {
            key += entry.getKey() + ",";
            value += "'" + handleString.encodeString(entry.getValue()) + "',";
        }
        if (key.endsWith(",")){
            key = key.substring(0, key.length() - 1);
        }
        if (value.endsWith(",")){
            value = value.substring(0, value.length() - 1);
        }
        if (params.isEmpty()){
            return false;
        }
        sql += key + ") VALUES (" + value + ")";
        System.out.println(sql);
        
        try {
            st.execute(sql);
            //System.out.println(this.searchAllTable(encryptMd5.getStringMd5(tableName.toUpperCase())).getRowCount());
            return true;
        }catch (SQLException e){
            System.out.println(e.getCause());
            return false;
        }
    }
    
    public DefaultTableModel searchAllTable(String tableName){
        Vector header = new Vector();
        header.add("ID");
        header.add("Date input");
        header.add("Amount");
        header.add("Content");
        model = new DefaultTableModel(header, 0);
        try {
            String sql = "SELECT * FROM n" + encryptMd5.getStringMd5(tableName.toUpperCase()) + " WHERE 1 = 1";
            rs = st.executeQuery(sql);
            int colCount = rs.getMetaData().getColumnCount();
            while (rs.next())
            {
                Vector row = new Vector();
                for (int i = 1; i <= colCount; i++){
                    row.add(handleString.decodeString(rs.getString(i)));
                }
                model.addRow(row);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return model;
    }
    
    public boolean deleteTable(String tableName,  String condition){
        try{
            String sql = "DELETE * FROM n" + encryptMd5.getStringMd5(tableName.toUpperCase()) + "WHERE" + condition;
            st.executeQuery(sql);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public void runQuery(String sql){
        try {
            st.executeUpdate(sql);
        } catch (SQLException ex) {
        }
    }
    
    /**
     * Map <String, String> params = new HashMap<String,String>();
     * params.put("fieldName1","fieldValueUpdate1");
     * params.put("fieldName2","fieldValueUpdate2");
     * @param tableName
     * @param params
     * @param where{"id", "IdToUpdate"}
     * @return 
     **/
    public boolean updateTable(String tableName, Map<String, String> params, String[] where){
        if (params.isEmpty() || where.length != 2){
            return false;
        }
        int id = 0, value = 1;
        try{
            String sql = "UPDATE n" + encryptMd5.getStringMd5(tableName.toUpperCase()) + " SET ";
            sql = params.entrySet().stream().map((entry) -> entry.getKey() + "='" + handleString.encodeString(entry.getValue()) + "',").reduce(sql, String::concat);
            if (sql.endsWith(",")){
                sql = sql.substring(0, sql.length() - 1);
            }
            sql += " WHERE " + where[id] + "='" + handleString.encodeString(where[value]) + "'";
            return st.execute(sql);
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }
}
