package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.Controller.MoteurController;
import fr.univangers.vrpv.MoteurInference.BaseDeRegle;

import javax.swing.*;

public class MainWindow extends JFrame {

    MoteurController m_controller;

    public MainWindow(BaseDeRegle BdR) {

        m_controller = new MoteurController();

        this.setContentPane(new PanelLancementMoteur(m_controller));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

}
