package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.MoteurInference.Fait;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelAjoutFait extends JPanel implements ItemListener {

    static final String ADD_FAIT_ACTION = "add_fait";

    JTextField m_varField;
    JTextField m_valueField;
    JCheckBox m_egalite;
    JButton m_addButton;
    JLabel m_labelEgalite;

    public PanelAjoutFait(ActionListener l) {

        this.setLayout(new GridBagLayout());

        m_varField = new JTextField();
        m_valueField = new JTextField();
        m_egalite = new JCheckBox("Egalité", true);
        m_egalite.addItemListener(this);
        m_labelEgalite = new JLabel("=");


        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(2, 2, 2, 2);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 3;

        this.add(m_varField, c);

        c.gridx = 2;

        this.add(m_valueField, c);


        c.gridx = 1;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0;

        this.add(m_labelEgalite, c);

        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0;

        this.add(m_egalite, c);

        c.gridx = 1;
        c.gridwidth = 2;
        m_addButton = new JButton("Ajouter");
        m_addButton.setActionCommand(ADD_FAIT_ACTION);
        m_addButton.addActionListener(l);

        this.add(m_addButton, c);

        this.setPreferredSize(new Dimension(200, 50));

    }

    public Fait getFait() {
        return new Fait(m_varField.getText(), m_valueField.getText(), m_egalite.isSelected());
    }

    public void setFait(Fait f) {
        m_varField.setText(f.getVar());
        m_valueField.setText(f.getVal());
    }

    public void clear() {
        m_valueField.setText("");
        m_varField.setText("");
    }

    @Override
    public void itemStateChanged(ItemEvent itemEvent) {
        if(itemEvent.getItem() == m_egalite){
            if(itemEvent.getStateChange() == ItemEvent.SELECTED){
                m_labelEgalite.setText("=");
            }else{
                m_labelEgalite.setText("!=");
            }
        }
    }
}
