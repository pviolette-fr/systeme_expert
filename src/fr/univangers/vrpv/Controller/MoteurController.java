package fr.univangers.vrpv.Controller;

import fr.univangers.vrpv.GUI.PanelLancementMoteur;

import javax.swing.*;

public class MoteurController {

    PanelLancementMoteur m_panel;

    public MoteurController(){

    }

    public void setPanel(PanelLancementMoteur panel){
        m_panel = panel;

        panel.setListModelPaquet(new DefaultListModel<>());
    }

    public void loadBdRFile(String Path){

    }

    public void saveBdRFile(String Path){

    }

    public void loadBdFFile(String Path){

    }

    public void saveBdFFile(String Path){

    }
}
