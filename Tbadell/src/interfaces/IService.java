/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author hazem
 */
public interface IService <T>  {
    public void ajouter(T t);
    public T getById(int id);
    public List<T> getAll();
    public boolean modifier(T t);
    public boolean supprimer(int t);
    List<T> getAllByUser(int t);
    public T findById(int t);
  //  public ObservableList<U> getAll();
 
    
}
