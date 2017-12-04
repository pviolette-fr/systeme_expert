package fr.univangers.vrpv.Controller;

import fr.univangers.vrpv.GUI.MainWindow;
import fr.univangers.vrpv.GUI.PanelGestionPaquet;
import fr.univangers.vrpv.GUI.PanelLancementMoteur;
import fr.univangers.vrpv.MoteurInference.*;

import java.util.List;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MoteurController implements ActionListener {

    private PanelLancementMoteur m_panelLancementMoteur;
    private PanelGestionPaquet m_panelGestionPaquet;
    private BaseDeRegle m_bdr;
    private BaseDeFait m_bdf;
    private final MainWindow m_win;

    private int currently_editing;

    public static final String ACTION_SAVE_BDR = "save-bdr";
    public static final String ACTION_SAVE_BDF = "save-bdf";
    public static final String ACTION_LOAD_BDR = "load-bdr";
    public static final String ACTION_LOAD_BDF = "load-bdf";

    public static final String ACTION_EDIT_PAQUET = "edit-paquet";
    public static final String ACTION_NEW_PAQUET = "new-paquet";
    public static final String ACTION_ADD_PAQUET = "add-paquet";
    public static final String ACTION_DELETE_PAQUET = "del-paquet";

    public static final String ACTION_START = "start";

    public MoteurController(MainWindow win, BaseDeRegle bdr, BaseDeFait bdf) {
        m_win = win;
        if (bdr != null)
            m_bdr = bdr;
        else
            m_bdr = new BaseDeRegle();

        if (bdf != null)
            m_bdf = bdf;
        else
            m_bdf = new BaseDeFait();
    }

    public void setPanelLancementMoteur(PanelLancementMoteur panelLancementMoteur) {
        m_panelLancementMoteur = panelLancementMoteur;
        redoPanel();
    }

    public void redoPanel() {
        redoPaquetList();
        redoFaitList();
    }

    public void redoPaquetList() {
        DefaultListModel<Paquet> l = m_panelLancementMoteur.getListModelPaquet();
        l.removeAllElements();
        for (Paquet p : m_bdr) {
            l.addElement(p);
        }
    }

    public void redoFaitList() {
        DefaultListModel<Fait> l = m_panelLancementMoteur.getPanelBaseDeFait().getListModele();
        l.removeAllElements();
        for (Fait f : m_bdf) {
            l.addElement(f);
        }
    }

    public void loadBdRFile(String path) {
        m_bdr = MoteurInferenceIO.creerBaseDeRegle(path);
        redoPaquetList();
    }

    public void saveBdRFile(String path) {
        try {
            MoteurInferenceIO.exporterBaseDeRegle(path, m_bdr);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void loadBdFFile(String path) {
        try {
            m_bdf = MoteurInferenceIO.importerBaseDeFait(path);
            redoFaitList();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void saveBdFFile(String path) {
        m_bdf = new BaseDeFait(m_panelLancementMoteur.getPanelBaseDeFait().getListFait());
        try {
            MoteurInferenceIO.exporterBaseDeFait(path, m_bdf);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void redoBdR(){
        m_bdr = new BaseDeRegle();
        DefaultListModel<Paquet> l = m_panelLancementMoteur.getListModelPaquet();
        for(int i = 0; i < l.size(); ++i){
            m_bdr.add(l.get(i));
        }
    }

    public void startMoteur(){
        ModeChainage mCh = m_panelLancementMoteur.getPanelOptionLancement().getModeChainage();
        Fait but = m_panelLancementMoteur.getPanelOptionLancement().getGoal();

        redoBdR();

        List<Fait> l = m_panelLancementMoteur.getPanelBaseDeFait().getListFait();

        m_bdf = new BaseDeFait(l);
        BaseDeRegle bdr = new BaseDeRegle(m_bdr);
        MoteurGroupementParPaquets moteur = new MoteurGroupementParPaquets(bdr, m_bdf, mCh);


        if(moteur.rechercherBut(but)){
            m_panelLancementMoteur.setBackground(new Color(133, 255, 121));
        }
        else{
            m_panelLancementMoteur.setBackground(new Color(255, 65, 62));
        }

        m_panelLancementMoteur.getPanelOptionLancement().setTrace(moteur.getTrace());
    }

    public void editPaquet(){
        Paquet p = m_panelLancementMoteur.selectedPaquet();
        m_panelGestionPaquet = new PanelGestionPaquet(this);
        m_panelGestionPaquet.loadPaquet(p);
        currently_editing = m_panelLancementMoteur.getSelectedPaquetIndex();

        m_win.setContentPane(m_panelGestionPaquet);
        m_win.repaint();
        m_win.setVisible(false);
        m_win.setVisible(true);
    }

    public void addPaquet(){
        Paquet p = m_panelGestionPaquet.getPaquet();

        if(currently_editing != -1){
            m_panelLancementMoteur.getListModelPaquet().set(currently_editing, p);
        }
        else
            m_panelLancementMoteur.getListModelPaquet().addElement(p);

        m_win.setContentPane(m_panelLancementMoteur);
        m_win.repaint();
        m_win.setVisible(false);
        m_win.setVisible(true);
    }

    public void newPaquet(){
        currently_editing = -1;
        m_panelGestionPaquet = new PanelGestionPaquet(this);

        m_win.setContentPane(m_panelGestionPaquet);
        m_win.setVisible(false);
        m_win.setVisible(true);
    }

    public void deletePaquet(){
        m_panelLancementMoteur.getListModelPaquet().remove(m_panelLancementMoteur.getSelectedPaquetIndex());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().startsWith("load") || actionEvent.getActionCommand().startsWith("save")) {
            JFileChooser chooser = new JFileChooser();
            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();
                switch (actionEvent.getActionCommand()) {
                    case ACTION_LOAD_BDF:
                        loadBdFFile(file.getAbsolutePath());
                        break;
                    case ACTION_LOAD_BDR:
                        loadBdRFile(file.getAbsolutePath());
                        break;
                    case ACTION_SAVE_BDF:
                        saveBdFFile(file.getAbsolutePath());
                        break;
                    case ACTION_SAVE_BDR:
                        saveBdRFile(file.getAbsolutePath());
                        break;
                }
            }
        }
        else{
            switch (actionEvent.getActionCommand()){
                case ACTION_START:
                    this.startMoteur();
                    break;
                case ACTION_EDIT_PAQUET:
                    editPaquet();
                    break;
                case ACTION_NEW_PAQUET:
                    newPaquet();
                    break;
                case ACTION_ADD_PAQUET:
                    addPaquet();
                    break;
                case ACTION_DELETE_PAQUET:
                    deletePaquet();
                    break;
            }
        }
    }
}
