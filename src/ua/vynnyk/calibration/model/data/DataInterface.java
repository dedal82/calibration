/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.vynnyk.calibration.model.data;

import java.util.List;

/**
 *
 * @param <T> - entity type in model
 * @author vynnyk
 */
public interface DataInterface<T> {

    /**
     * Selects the entity from a data storage
     * @param id - unique id of entity in a data storage
     * @return - entity 
     */
    T select(int id);

    /**
     * Selects the list of entity from a data storage
     * @param condition - condition to select ("where types = 2")
     * @return - the list of entities
     */
    List<T> selects(String condition);

    /**
     * Inserts the entity to a data storage
     * @param entity - entity to inserting to a data storage
     * @return - id of inserted entity
     */
    int insert(T entity);
    
    /**
     * Updates the entity in a data storage
     * @param entity - entity to update
     * @return - count of updated entity (0 or 1)
     */
    int update(T entity);
    
    /**
     * Deletes the entity from a data storage
     * @param entity - entity to deleting from a data storage
     * @return - count of deleted entity (1 or 0);
     */    
    int delete(T entity);
    
}