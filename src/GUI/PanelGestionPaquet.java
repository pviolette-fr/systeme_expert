package GUI;

import MoteurInference.Paquet;
import MoteurInference.Regle;
//import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;

import java.util.List;
import java.util.LinkedList;

public class PanelGestionPaquet extends JPanel implements ActionListener{

    static final String ACTION_CLEAR_RULES = "clear_rules";

    JList<Regle> m_listeRegles;
    DefaultListModel<Regle> m_listModelRegle;

    PanelAjoutRegle m_panelRegle;

    JTextField m_nameField;
    JButton m_clearRules;

    JButton m_confirmPaquet;

    public PanelGestionPaquet() {

        GridBagLayout layout = new GridBagLayout();

        this.setLayout(layout);

        m_listModelRegle = new DefaultListModel<>();
        m_listeRegles = new JList<>(m_listModelRegle);

        m_panelRegle = new PanelAjoutRegle(this);

        m_nameField = new JTextField("nom_paquet");

        m_clearRules = new JButton("Clear rules");
        m_clearRules.addActionListener(this);
        m_clearRules.setActionCommand(ACTION_CLEAR_RULES);

        m_confirmPaquet = new JButton("Ajouter ce paquet à la base de règle");

        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(4, 4,4,4);

        //Première ligne : Liste des règles et panel de création de règle
        c.gridy = 0;
        c.fill  = GridBagConstraints.BOTH;

        c.gridx = 0;
        c.weighty = 3;
        c.weightx = 1;

        this.add(m_listeRegles, c);

        c.weightx = 2;
        c.gridx = 2;
        c.gridwidth = 2;
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

        this.add(m_nameField, c);

        c.gridx = 3;

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
        return paquet;
    }

    public void clearRules(){
        m_listModelRegle.removeAllElements();
    }
}
