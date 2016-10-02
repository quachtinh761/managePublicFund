/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import model.DBTemplate;

/**
 *
 * @author Nguyen Van Tinh
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HomeLayout.run();
        DBTemplate db = new DBTemplate();
        db.getConnect();
        /*consumeModel con = new consumeModel();
        con.getConnect();
        con.createTable();*/
        //System.out.println(con.getAllTableConsumeInDB().getRowCount());
        //con.closeConnect();
        //
        
        /*String tblName = "abc";
        DBTemplate db = new DBTemplate(tblName);
        db.getConnect();
        List < String[] > params = new ArrayList();
        params.add(new String[]{consumeConstant.KEY_ID, consumeConstant.KEY_ID_TYPE});
        params.add(new String[]{consumeConstant.KEY_DATE, consumeConstant.KEY_DATE_TYPE});
        db.createTable(tblName, params);*/
        
        
    }
    
    
}
