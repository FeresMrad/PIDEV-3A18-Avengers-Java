/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.tbadel.interfaces;

import java.util.List;

/**
 *
 * @author feres
 */
public interface EntityCRUD<T> {
    public void addEntity(T t);
    public void deleteEntity (int t);
    public void updateEntity(T t);
    public List<T> entitiesList();
}
