package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.Controller.MoteurController;
import fr.univangers.vrpv.MoteurInference.Paquet;

import javax.swing.*;
import java.awt.*;

public class PanelLancementMoteur extends JPanel {

    PanelGestionFait m_panelBaseDeFait;

    JList<Paquet> m_listPaquet;
    DefaultListModel<Paquet> m_listModelPaquet;

    JButton m_editPaquetButton;
    JButton m_addPaquetButton;
    JButton m_deletePaquetButton;

    JButton m_loadBdRFile;
    JButton m_saveBdRFile;

    JButton m_loadBdFFile;
    JButton m_saveBdFFile;

    MoteurController m_controller;

    public PanelLancementMoteur(MoteurController controller) {


        this.setLayout(new GridBagLayout());

        m_editPaquetButton = new JButton("Edit");
        m_editPaquetButton.setActionCommand(MoteurController.ACTION_EDIT_PAQUET);
        m_editPaquetButton.addActionListener(controller);

        m_addPaquetButton = new JButton("Add");
        m_addPaquetButton.setActionCommand(MoteurController.ACTION_NEW_PAQUET);
        m_addPaquetButton.addActionListener(controller);

        m_deletePaquetButton = new JButton("Delete");
        m_deletePaquetButton.setActionCommand(MoteurController.ACTION_DELETE_PAQUET);
        m_deletePaquetButton.addActionListener(controller);

        m_loadBdFFile = new JButton("Ouvir Base de Fait");
        m_loadBdFFile.setActionCommand(MoteurController.ACTION_SAVE_BDF);
        m_loadBdFFile.addActionListener(controller);

        m_saveBdFFile = new JButton("Sauver Base de Fait");
        m_saveBdFFile.setActionCommand(MoteurController.ACTION_SAVE_BDF);
        m_saveBdFFile.addActionListener(controller);

        m_loadBdRFile = new JButton("Ouvrir Base de Regle");
        m_loadBdRFile.setActionCommand(MoteurController.ACTION_LOAD_BDR);
        m_loadBdRFile.addActionListener(controller);

        m_saveBdRFile = new JButton("Sauver Base de règle");
        m_saveBdRFile.setActionCommand(MoteurController.ACTION_SAVE_BDR);
        m_saveBdRFile.addActionListener(controller);

        m_listModelPaquet = new DefaultListModel<>();
        m_listPaquet = new JList<>(m_listModelPaquet);

        m_panelBaseDeFait = new PanelGestionFait();

        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;

        this.add(new JLabel("Base de regle"), c);

        c.gridx = 3;

        this.add(new JLabel("Base de Fait"), c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weightx = 3;
        c.weighty = 1;
        JScrollPane scrollListPaquet = new JScrollPane(m_listPaquet);
        this.add(scrollListPaquet, c);

        c.gridwidth = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 2;

        this.add(m_addPaquetButton, c);

        c.gridx = 1;
        this.add(m_editPaquetButton, c);

        c.gridx = 2;
        this.add(m_deletePaquetButton, c);

        c.gridx = 3;
        c.gridy = 1;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 1;
        c.gridheight = 2;
        this.add(m_panelBaseDeFait, c);


        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty =0;
        c.ipady = 10;
        c.gridwidth = 4;
        this.add(new JSeparator(JSeparator.HORIZONTAL), c);

        c.gridwidth = 1;
        c.gridy = 4;
        c.gridx = 0;
        c.gridheight = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        this.add(m_loadBdRFile, c);
        c.gridx++;
        this.add(m_saveBdRFile, c);
        c.gridx++;
        this.add(m_loadBdFFile, c);
        c.gridx++;
        this.add(m_saveBdFFile, c);

        m_controller = controller;
        m_controller.setPanelLancementMoteur(this);
    }

    public void setListModelPaquet(DefaultListModel<Paquet> listModelPaquet){
        m_listModelPaquet = listModelPaquet;
    }

    public DefaultListModel<Paquet> getListModelPaquet() {
        return m_listModelPaquet;
    }

    public PanelGestionFait getPanelBaseDeFait() {
        return m_panelBaseDeFait;
    }
}
