package GUI;

import MoteurInference.Fait;
import MoteurInference.Regle;

import javax.swing.*;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;



public class PanelAjoutRegle extends JPanel{

    static final String ADD_RULE_ACTION = "add_rule";

    PanelGestionFait m_panelConclusion;
    PanelGestionFait m_panelPremisses;

    JButton m_confirmRule;

    private void buildLayout(ActionListener l){

        m_panelConclusion = new PanelGestionFait();
        m_panelPremisses = new PanelGestionFait();

        m_confirmRule = new JButton("Ajouter la règle");
        m_confirmRule.setActionCommand(ADD_RULE_ACTION);
        m_confirmRule.addActionListener(l);


        this.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weighty = 0;
        c.weightx = 5;

        this.add(new JLabel("<html><h2>Premisses</h2></html>"), c);

        c.gridx = 2;

        this.add(new JLabel("<html><h2>Conclusion</h2></html>"), c);

        c.gridx = 1;
        c.weightx = 1;
        c.fill = GridBagConstraints.VERTICAL;
        this.add(new JSeparator(JSeparator.VERTICAL), c);


        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 1;
        c.weightx = 5;
        c.fill = GridBagConstraints.BOTH;
        this.add(m_panelPremisses, c);

        c.gridx = 2;
        this.add(m_panelConclusion, c);

        c.gridx = 1;
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 1;
        this.add(new JSeparator(JSeparator.VERTICAL), c);


        c.gridy = 2;
        c.gridx = 0;
        c.weighty = 0;
        c.ipady = 5;
        c.gridwidth = 3;
        this.add(new JSeparator(JSeparator.HORIZONTAL), c);

        c.gridy= 3;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0;
        c.weightx = 3;
        c.ipady = 5;
        this.add(m_confirmRule, c);

    }

    public PanelAjoutRegle(ActionListener l) {
        buildLayout(l);
    }

    public PanelAjoutRegle(ActionListener l, Regle r){
        buildLayout(l);

        for(Fait p : r.getPremisses()){
            m_panelPremisses.ajouterFait(p);
        }
        for(Fait c : r.getConclusion()){
            m_panelConclusion.ajouterFait(c);
        }
    }

    public void clear(){
        m_panelConclusion.clear();
        m_panelPremisses.clear();
    }

    public Regle getRegle(){
        return new Regle(m_panelPremisses.getListFait(), m_panelConclusion.getListFait());
    }

    public void setRegle(Regle r){
        clear();
        for(Fait p : r.getPremisses()){
            m_panelPremisses.ajouterFait(p);
        }
        for(Fait c : r.getConclusion()){
            m_panelConclusion.ajouterFait(c);
        }
    }
}
