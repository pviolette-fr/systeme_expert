package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.MoteurInference.BaseDeRegle;

import javax.swing.*;

public class MainWindow extends JFrame {


    public MainWindow(BaseDeRegle BdR) {

        PanelGestionPaquet PGP = new PanelGestionPaquet();

        PGP.loadPaquet(BdR.get(0));

        this.setContentPane(PGP);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
    }

}
