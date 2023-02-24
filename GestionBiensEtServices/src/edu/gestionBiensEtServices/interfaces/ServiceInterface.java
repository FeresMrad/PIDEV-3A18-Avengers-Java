/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestionBiensEtServices.interfaces;

import edu.gestionBiensEtServices.entites.Service;
import java.util.List;

/**
 *
 * @author Nouhe
 */
public interface ServiceInterface<S> {

    public void ajouterService(S s);

    public List<S> etitiesList();

    public void supprimerService(int s);

    public void updateService(Service s, int x);
}

