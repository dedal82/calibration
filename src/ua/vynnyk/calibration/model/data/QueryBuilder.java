/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vynnyk
 */
class QueryBuilder {
    
    private String tableName;
    private List<String> fields;
  
    String getTableName() {
        return tableName;
    }

    void setTableName(String tableName) {
        this.tableName = tableName;
    }

    List<String> getFields() {
        return fields;
    }

    void setFields(List<String> fields) {
        this.fields = fields;
    }
    
    QueryBuilder(String tableName, List<String> fields) {
        this.tableName = tableName;
        this.fields = fields;
    }
    
    String getSelectQuery() {
        if (fields.isEmpty()) {
            return null;
        }            
        final StringBuilder query = new StringBuilder("select ").
              append(getFieldsList(0, fields.size(), ", ")).
              append(" from ").
              append(tableName);              
        
        return  query.toString();
    };
    
    String getInsertQuery() {
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
    
    String getUpdateQuery() {
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
    
    String getDeleteQuery() {
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
    
   int getFieldCount() {
        return fields.size();
    } 
    
    // for testing
    static void main(String[] args) {
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

    String getIdField() {
        return fields.get(0);
    }
    
}