/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import java.util.List;

/**
 *
 * @author feres
 */
public interface EntityCRUDT<T> {
    public void addEntity(T t);
    public List<T> entitiesList();
    public void delEntity(int t);
    public void updEntity(T t);
    
}
