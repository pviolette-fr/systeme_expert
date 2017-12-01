package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.MoteurInference.Fait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelAjoutFait extends JPanel{

    static final String ADD_FAIT_ACTION = "add_fait";

    JTextField m_varField;
    JTextField m_valueField;
    JButton m_addButton;

    public PanelAjoutFait(ActionListener l){

        this.setLayout(new GridBagLayout());

        m_varField = new JTextField();
        m_valueField = new JTextField();

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(2,2,2,2);
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

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        m_addButton = new JButton("Ajouter");
        m_addButton.setActionCommand(ADD_FAIT_ACTION);
        m_addButton.addActionListener(l);

        this.add(m_addButton, c);

        this.setPreferredSize(new Dimension(200, 50));

    }

    public Fait getFait(){
        return new Fait(m_varField.getText(), m_valueField.getText());
    }

    public void setFait(Fait f){
        m_varField.setText(f.getVar());
        m_valueField.setText(f.getVal());
    }

    public void clear(){
        m_valueField.setText("");
        m_varField.setText("");
    }

}
