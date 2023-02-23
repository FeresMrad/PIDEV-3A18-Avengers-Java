/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

/**
 *
 * @author BACEM
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public List<T> entitiesList();
    public void delEvenements(int t);
    public void updEvenements(T t,int x);
//    public List<T>entitiesList2();
}