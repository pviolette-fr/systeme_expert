package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.Controller.MoteurController;
import fr.univangers.vrpv.MoteurInference.ModeChainage;
import fr.univangers.vrpv.MoteurInference.Paquet;
import fr.univangers.vrpv.MoteurInference.Regle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class PanelGestionPaquet extends JPanel implements ActionListener, ListSelectionListener{

    static final String ACTION_CLEAR_RULES = "clear_rules";

    JList<Regle> m_listeRegles;
    DefaultListModel<Regle> m_listModelRegle;

    PanelAjoutRegle m_panelRegle;

    JButton m_clearRules;

    JButton m_confirmPaquet;

    JComboBox<ModeChainage> m_modePreferentielChainage;

    public PanelGestionPaquet(MoteurController controller) {

        GridBagLayout layout = new GridBagLayout();

        this.setLayout(layout);

        m_listModelRegle = new DefaultListModel<>();
        m_listeRegles = new JList<>(m_listModelRegle);
        m_listeRegles.addListSelectionListener(this);

        m_panelRegle = new PanelAjoutRegle(this);

        m_modePreferentielChainage = new JComboBox<>();

        for(ModeChainage mCh : ModeChainage.values()){
            m_modePreferentielChainage.addItem(mCh);
        }

        m_modePreferentielChainage.setSelectedItem(ModeChainage.DEFAULT);

        m_clearRules = new JButton("Clear rules");
        m_clearRules.addActionListener(this);
        m_clearRules.setActionCommand(ACTION_CLEAR_RULES);

        m_confirmPaquet = new JButton("Ajouter ce paquet à la base de règle");
        m_confirmPaquet.setActionCommand(MoteurController.ACTION_ADD_PAQUET);
        m_confirmPaquet.addActionListener(controller);

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(4, 4,4,4);

        //Première ligne : Liste des règles et panel de création de règle
        c.gridy = 0;
        c.fill  = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.weighty = 3;
        c.weightx = 1;

        JScrollPane scrollPaneListeRegle = new JScrollPane(m_listeRegles);

        this.add(scrollPaneListeRegle, c);

        c.weightx = 2;
        c.gridx = 2;
        c.gridwidth = 3;
        this.add(m_panelRegle, c);

        c.gridwidth = 1;

        c.gridx = 1;
        c.ipadx = 5;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;
        this.add(new JSeparator(JSeparator.VERTICAL), c);

        c.gridy = 1;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0;

        this.add(m_clearRules, c);

        c.gridx = 2;
        c.fill = GridBagConstraints.NONE;
        this.add(new JLabel("Mode de chainage préferentiel"), c);

        c.gridx = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        this.add(m_modePreferentielChainage, c);

        c.gridx = 4;
        c.weightx = 2;
        this.add(m_confirmPaquet, c);

    }

    public void addRegle(Regle r){
        m_listModelRegle.addElement(r);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case PanelAjoutRegle.ADD_RULE_ACTION :
                    this.addRegle(m_panelRegle.getRegle());
                break;
            case ACTION_CLEAR_RULES:
                clearRules();
                break;
        }
    }

    public Paquet getPaquet(){
        Paquet paquet = new Paquet();
        for(int i = 0; i < m_listModelRegle.size(); ++i){
            paquet.ajouterRegle(m_listModelRegle.get(i));
        }
        paquet.setModeChainagePreferenciel(m_modePreferentielChainage.getItemAt(m_modePreferentielChainage.getSelectedIndex()));
        return paquet;
    }

    public void loadPaquet(Paquet p){
        this.clear();
        System.out.println("Loading paquet");
        System.out.println(p);
        m_modePreferentielChainage.setSelectedItem(p.getModeChainagePreferenciel());
        for(Regle r : p){
            m_listModelRegle.addElement(r);
        }
    }

    public void clear(){
        clearRules();
        m_modePreferentielChainage.setSelectedItem(ModeChainage.DEFAULT);
        m_panelRegle.clear();
    }

    public void clearRules(){
        m_listModelRegle.removeAllElements();
    }

    @Override
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(m_listeRegles.getSelectedIndex() != -1){
            m_panelRegle.setRegle(m_listModelRegle.get(m_listeRegles.getSelectedIndex()));
        }
    }
}
