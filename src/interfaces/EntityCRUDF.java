/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author Feriel
 */
import java.util.List;

public interface EntityCRUDF<T> {
    T getById(int id);
    List<T> getAll();
    //String getName(int id);
    void add(T entity);
    void update(T entity);
    void delete(int id);
}