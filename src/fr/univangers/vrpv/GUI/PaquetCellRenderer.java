package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.MoteurInference.Paquet;

import javax.swing.*;
import java.awt.*;

public class PaquetCellRenderer extends JPanel implements ListCellRenderer<Paquet> {

    JTextArea m_jTextArea;

    public PaquetCellRenderer() {
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 3;
        m_jTextArea = new JTextArea();
        this.add(m_jTextArea, c);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Paquet> list, Paquet p, int i, boolean isSelected, boolean cellHasFocus) {

        this.m_jTextArea.setText(
                "Paquet " + i + System.lineSeparator() +
                "    Mode Preferentielle\t" + p.getModeChainagePreferenciel() + System.lineSeparator() +
                        "    Règles\t" + p.size()
        );

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(list.isEnabled());
        setFont(list.getFont());
        setOpaque(true);
        return this;
    }
}
