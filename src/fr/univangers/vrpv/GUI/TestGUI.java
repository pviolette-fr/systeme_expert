package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.MoteurInference.BaseDeRegle;
import fr.univangers.vrpv.MoteurInference.MoteurInferenceIO;
import net.infonode.gui.laf.InfoNodeLookAndFeel;
import net.infonode.gui.laf.InfoNodeLookAndFeelTheme;

import javax.swing.*;
import java.awt.*;

public class TestGUI{

    static public void main(String[] args){
        try {
            InfoNodeLookAndFeelTheme theme = new InfoNodeLookAndFeelTheme(
                    "YoloSwag",
                    new Color(49, 51, 53), //Control color
                    new Color(49, 51, 53), //Primary Control Color
                    new Color(43, 43, 43), //Background color
                    Color.white, //Text color
                    new Color(71,80,89), //Selected Background Color
                    new Color(176,176,176) //Selected Text color
            );
            UIManager.setLookAndFeel(new InfoNodeLookAndFeel(theme));
        } catch (Exception e) {
            // Si InfoNodeLookAndFeel pas disponible, L&F par default
        }
        BaseDeRegle bdr = MoteurInferenceIO.creerBaseDeRegle("base_de_regle.json");

        JFrame win = new MainWindow(bdr);
        win.setVisible(true);
    }

}
