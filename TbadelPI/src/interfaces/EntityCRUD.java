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

public interface EntityCRUD<T> {
    T getById(int id);
    List<T> getAll();
    void add(T entity);
    void update(T entity);
    void delete(int id);
}