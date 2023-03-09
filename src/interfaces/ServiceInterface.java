/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


import entities.Service;
import java.util.List;

/**
 *
 * @author Nouhe
 */
public interface ServiceInterface<S> {

    public void ajouterService(Service s,int userId);

    public List<S> etitiesList();

    public void supprimerService(int s);

    public void updateService(Service s, int x);
}

