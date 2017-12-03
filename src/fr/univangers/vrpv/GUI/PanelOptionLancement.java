package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.Controller.MoteurController;
import fr.univangers.vrpv.MoteurInference.Fait;
import fr.univangers.vrpv.MoteurInference.ModeChainage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PanelOptionLancement extends JPanel {

    JComboBox<ModeChainage> m_modeChainageJComboBox;

    JTextField m_varFaitField;
    JTextField m_valueFaitField;

    JTextArea m_trace;

    JButton m_start;

    public PanelOptionLancement(ActionListener listener) {

        this.setLayout(new GridBagLayout());

        m_modeChainageJComboBox = new JComboBox<>();
        for(ModeChainage mCh : ModeChainage.values()){
            m_modeChainageJComboBox.addItem(mCh);
        }

        m_varFaitField = new JTextField();
        m_valueFaitField = new JTextField();

        m_trace = new JTextArea();

        m_start = new JButton("Start !");
        m_start.setActionCommand(MoteurController.ACTION_START);
        m_start.addActionListener(listener);

        GridBagConstraints c = new GridBagConstraints();

        c.weighty = 0;
        c.weightx = 1;

        c.gridy = 0;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;

        this.add(new JLabel("Mode de chainage"),c);


        c.gridy++;

        this.add(m_modeChainageJComboBox, c);

        c.gridy++;

        c.ipady = 5;
        this.add(new JSeparator(JSeparator.HORIZONTAL));


        c.gridy++;
        c.ipady = 0;
        c.gridwidth = 3;
        this.add(new JLabel("But"), c);

        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 1;

        this.add(m_varFaitField, c);

        c.gridx++;
        c.weightx = 0;
        this.add(new JLabel("="), c);

        c.gridx++;
        c.weightx = 1;
        this.add(m_valueFaitField,c);

        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 3;
        this.add(m_start, c);

        c.gridy++;
        c.fill =GridBagConstraints.BOTH;
        c.weighty = 1;
        JScrollPane pane = new JScrollPane(m_trace);
        this.add(pane, c);
    }

    public ModeChainage getModeChainage(){
        return m_modeChainageJComboBox.getItemAt(m_modeChainageJComboBox.getSelectedIndex());
    }

    public Fait getGoal(){
        return new Fait(m_varFaitField.getText(), m_valueFaitField.getText(),true);
    }

    public void setTrace(String trace){
        m_trace.setText(trace);
    }
}
