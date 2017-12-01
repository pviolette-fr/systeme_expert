package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.Controller.MoteurController;
import fr.univangers.vrpv.MoteurInference.Paquet;

import javax.swing.*;

public class PanelLancementMoteur extends JPanel {

    PanelGestionFait m_panelBaseDeFait;

    JList<Paquet> m_listPaquet;
    DefaultListModel<Paquet> m_listModelPaquet;

    JButton m_editPaquetButton;
    JButton m_addPaquetButton;
    JButton m_deletePaquetButton;

    JButton m_loadBdRFileButton;
    JButton m_saveBdRFileButton;

    JButton m_loadBdFFile;
    JButton m_saveBdFFile;

    MoteurController m_controller;


    public PanelLancementMoteur(MoteurController controller) {
        m_controller = controller;
        m_controller.setPanel(this);
    }

    public void setListModelPaquet(DefaultListModel<Paquet> listModelPaquet){
        m_listModelPaquet = listModelPaquet;
    }
}
