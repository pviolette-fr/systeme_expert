package fr.univangers.vrpv.GUI;

import fr.univangers.vrpv.MoteurInference.Fait;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class PanelGestionFait extends JPanel implements ActionListener, ListSelectionListener {

    static final String DELETE_FAIT_ACTION = "delete_selected";
    static final String CLEAR = "clear_fait";


    DefaultListModel<Fait> m_listModele;
    JList<Fait> m_listeFait;

    JButton m_clear;
    PanelAjoutFait m_addPanel;

    public PanelGestionFait() {
        this.setLayout(new BorderLayout());

        m_clear = new JButton("Clear");
        m_clear.setActionCommand(CLEAR);
        m_clear.addActionListener(this);

        m_addPanel = new PanelAjoutFait(this);

        m_listModele = new DefaultListModel<>();
        m_listeFait = new JList<>(m_listModele);
        m_listeFait.addListSelectionListener(this);

        JScrollPane scrollListe = new JScrollPane(m_listeFait);
        scrollListe.setPreferredSize(new Dimension(100,300));
        this.add(scrollListe, BorderLayout.CENTER);

        this.add(m_addPanel, BorderLayout.NORTH);
        this.add(m_clear, BorderLayout.SOUTH);    }

    /**
     * Ajoute le fait donné dans la liste.
     * @param f le fait à ajouter
     */
    public void ajouterFait(Fait f){
        if(f.getVal().length() > 0 && f.getVar().length() > 0){
            if(m_listeFait.getSelectedIndex() != -1){
                int pos = m_listeFait.getSelectedIndex();
                supprimerFaitSelectionne();
                m_listModele.add(pos, f);
            }
            else{
                m_listModele.addElement(f);
            }
            m_addPanel.clear();

        }
    }

    public List<Fait> getListFait(){
        List<Fait> l = new LinkedList<>();
        for(int i = 0; i < m_listModele.size(); ++i){
            l.add(m_listModele.get(i));
        }
        return l;
    }

    /**
     * Supprime le Fait à l'indice i dans la liste
     * @param i l'indice du fait à supprimer
     */
    public void supprimerFait(int i){
        System.out.println("Delete" + i);
        m_listModele.removeElementAt(i);
    }

    public void supprimerFaitSelectionne(){
        int indices[] = m_listeFait.getSelectedIndices();
        for(int i = indices.length - 1; i >= 0; i--){
            supprimerFait(indices[i]);
        }
    }

    public void clear(){
        m_listModele.removeAllElements();
        m_addPanel.clear();
    }

     
    public void actionPerformed(ActionEvent actionEvent) {
        switch (actionEvent.getActionCommand()){
            case PanelAjoutFait.ADD_FAIT_ACTION :
                    ajouterFait(m_addPanel.getFait());
                break;
            case DELETE_FAIT_ACTION:
                    supprimerFaitSelectionne();
                break;
            case CLEAR:
                System.out.print("Clear all");
                clear();
                break;
        }
    }

     
    public void valueChanged(ListSelectionEvent listSelectionEvent) {
        if(m_listeFait.getSelectedIndex() != -1){
            m_addPanel.setFait(m_listModele.get(m_listeFait.getSelectedIndex()));
        }
    }
}