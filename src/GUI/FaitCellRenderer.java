package GUI;

import MoteurInference.Fait;

import javax.swing.*;
import java.awt.*;
/*
Sert à rien pour l'instant
 */

public class FaitCellRenderer extends JPanel implements ListCellRenderer<Fait> {

    JTextField m_varField;
    JTextField m_valueField;

    public FaitCellRenderer(){
        this.setLayout(new GridBagLayout());

        m_varField = new JTextField();
        m_valueField = new JTextField();

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;

        this.add(m_varField, c);

        c.gridx = 2;

        this.add(m_valueField, c);

        JLabel labelEgale = new JLabel("=");

        c.gridx = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;

        this.add(labelEgale, c);

        this.setPreferredSize(new Dimension(200, 50));

    }

    public Component getListCellRendererComponent(JList<? extends Fait> list, Fait fait, int i, boolean isSelected, boolean cellHasFocus) {

        this.m_valueField.setText(fait.getVal());
        this.m_varField.setText(fait.getVar());

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
