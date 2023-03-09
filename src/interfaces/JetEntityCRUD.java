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
public interface JetEntityCRUD<J> {
    public List<J> entitiesList();
    public void updEntity(J t);
    
}
