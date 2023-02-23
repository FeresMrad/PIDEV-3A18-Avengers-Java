/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author BACEM
 */
public interface CRUDP<T> {
    public void addEntityP(T t);
    public List<T> entitiesListP();
        public List<T> entitiesListP(int x);
    public void delParticipants(int t);

}
