package GUI;

import net.infonode.gui.laf.InfoNodeLookAndFeel;
import net.infonode.gui.laf.InfoNodeLookAndFeelTheme;
import net.infonode.gui.laf.InfoNodeLookAndFeelThemes;

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
            // Si InfoNodeLookAndFeel pas disponnible, L&F par default
        }

        JFrame win = new MainWindow();
        win.setVisible(true);
    }

}
