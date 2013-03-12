/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.database;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vynnyk
 */
public class QueryBuilder {
    
    private String tableName;
    private List<String> fields;
  
    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
    
    public QueryBuilder(String tableName, List<String> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }
    
    public String getSelectQuery() {
        if (fields.isEmpty()) {
            return null;
        }            
        final StringBuilder query = new StringBuilder("select ").
              append(getFieldsList(0, fields.size(), ", ")).
              append(" from ").
              append(tableName);              
        
        return  query.toString();
    };
    
    public String getInsertQuery() {
        if (fields.size() < 2) {
            return null;
        }            
        final StringBuilder query = new StringBuilder("insert into ").
              append(tableName).
              append(" (").
              append(getFieldsList(1, fields.size(), ", ")).
              append(") values (");       
        for(int i = 0; i < (fields.size() - 2) ; i++) {
            query.append("?, ");
        }        
        query.append("?)");
        
        return  query.toString();
    };
    
    public String getUpdateQuery() {
        if (fields.size() < 2) {
            return null;
        }            
        final StringBuilder query = new StringBuilder("update ").
              append(tableName).
              append(" set ").
              append(getFieldsList(1, fields.size(), " = ?, ")).
              append(" = ? where ").
              append(fields.get(0)).
              append(" = ?");       
        
        return  query.toString();
    };
    
    public String getDeleteQuery() {
        if (fields.isEmpty()) {
            return null;
        }            
        final StringBuilder query = new StringBuilder("delete from ").
              append(tableName).
              append(" where ").              
              append(fields.get(0)).
              append(" = ?");       
        
        return  query.toString();
    };    
            
    private String getFieldsList(int start, int end, String separator) {
        StringBuilder fieldList = new StringBuilder();
        for (int i = start; i < end; i++) {
            if (i > start) { 
                fieldList.append(separator);                
            } 
            fieldList.append(fields.get(i));
        }
        return fieldList.toString();
    } 
    
   public int getFieldCount() {
        return fields.size();
    } 
    
    // for testing
    public static void main(String[] args) {
        List<String> fields = new ArrayList<>();
        fields.add("id");
        fields.add("name");
        fields.add("dates");
        fields.add("field1");
        fields.add("field2");
        QueryBuilder queryBuilder = new QueryBuilder("test", fields);
        System.out.println(queryBuilder.getSelectQuery());
        System.out.println(queryBuilder.getInsertQuery());
        System.out.println(queryBuilder.getUpdateQuery());
        System.out.println(queryBuilder.getDeleteQuery());
    }

    public String getIdField() {
        return fields.get(0);
    }
    
}