/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vynnyk
 */
public class DataWorker {
    private QueryBuilder queryBilder;  
    private Connection con;
    
    public DataWorker(QueryBuilder queryBilder, Connection con) {
        this.queryBilder = queryBilder;
        this.con = con;
    }
    
    public ResultSet selectRecords(String condition) {       
        String query = queryBilder.getSelectQuery();
        if (condition != null) {
            query = query + " " + condition;
        }        
        Statement st = null; 
        try {
            st = con.createStatement();
            return st.executeQuery(query);
        } catch (SQLException e ) {
            Logger.getLogger(DataWorker.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    };
    
    public ResultSet selectRecord(int in) {
        return selectRecords("where " + queryBilder.getIdField() + " = " + in);
    };
            
    public int insertRecord(List<Object> params) {
        if (queryBilder.getFieldCount() == params.size() + 1) {
            String query = queryBilder.getInsertQuery();               
            return ExecuteSQL(query, params);
        } 
        return 0;
    }
    
    public int updateRecord(List<Object> params) {
        if (queryBilder.getFieldCount() == params.size()) {
            String query = queryBilder.getUpdateQuery();               
            return ExecuteSQL(query, params);
        } else {
            return 0;
        }
    }
    
    public int deleteRecord(int id) {
        String query = queryBilder.getDeleteQuery();               
        List<Object> params = new ArrayList<>();
        params.add(id);
        return ExecuteSQL(query, params);                
    }        

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }  
    
    public QueryBuilder getQueryBilder() {
        return queryBilder;
    }

    public void setQueryBilder(QueryBuilder queryBilder) {
        this.queryBilder = queryBilder;
    }
    
    private int ExecuteSQL(String query, List<Object> params) {
        PreparedStatement st = null; 
            try {
                st = con.prepareStatement(query);                        
                int i = 1;
                for (Object o: params) {
                   st.setObject(i++, o); 
                }            
                return st.executeUpdate();
            } catch (SQLException e ) {
                Logger.getLogger(DataWorker.class.getName()).log(Level.SEVERE, null, e);
            } finally {
                if (st != null) {
                    try {
                        st.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(DataWorker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        return 0;
    }
    
    public int getIdentity() {
        Statement st = null; 
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("CALL IDENTITY()");
            rs.next();
            return rs.getInt(1);
        } catch (SQLException e ) {
            Logger.getLogger(DataWorker.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataWorker.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return -1;
    }
}
