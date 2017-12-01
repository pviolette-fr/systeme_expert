package fr.univangers.vrpv.Controller;

import fr.univangers.vrpv.GUI.PanelGestionPaquet;
import fr.univangers.vrpv.GUI.PanelLancementMoteur;
import fr.univangers.vrpv.MoteurInference.BaseDeFait;
import fr.univangers.vrpv.MoteurInference.BaseDeRegle;
import fr.univangers.vrpv.MoteurInference.Fait;
import fr.univangers.vrpv.MoteurInference.Paquet;
import fr.univangers.vrpv.MoteurInference.MoteurInferenceIO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MoteurController implements ActionListener{

    private PanelLancementMoteur m_panelLancementMoteur;
    private PanelGestionPaquet m_panelGestionPaquet;
    private BaseDeRegle m_bdr;
    private BaseDeFait m_bdf;

    public static final String ACTION_SAVE_BDR = "save-bdr";
    public static final String ACTION_SAVE_BDF = "save-bdf";
    public static final String ACTION_LOAD_BDR = "load-bdr";
    public static final String ACTION_LOAD_BDF = "load-bdf";

    public static final String ACTION_EDIT_PAQUET = "edit-paquet";
    public static final String ACTION_NEW_PAQUET = "new-paquet";
    public static final String ACTION_ADD_PAQUET = "add-paquet";
    public static final String ACTION_DELETE_PAQUET = "del-paquet";

    public MoteurController(){

    }

    public void setPanelLancementMoteur(PanelLancementMoteur panelLancementMoteur){
        m_panelLancementMoteur = panelLancementMoteur;
        redoPanel();
    }

    public void redoPanel(){
        if(m_bdr != null){
            DefaultListModel<Paquet> l = new DefaultListModel<>();
            for(Paquet p : m_bdr){
                l.addElement(p);
            }
            m_panelLancementMoteur.setListModelPaquet(l);
        }
        if(m_bdf != null){

            DefaultListModel<Fait> l = new DefaultListModel<>();
            for(Fait f : m_bdf){
                l.addElement(f);
            }
            m_panelLancementMoteur.getPanelBaseDeFait().setListModele(l);
        }
    }

    public void redoPaquetList(){
        DefaultListModel<Paquet> l = m_panelLancementMoteur.getListModelPaquet();
        l.removeAllElements();
        for(Paquet p : m_bdr){
            l.addElement(p);
        }
    }

    public void loadBdRFile(String path){
        m_bdr = MoteurInferenceIO.creerBaseDeRegle(path);
        redoPaquetList();
    }

    public void saveBdRFile(String path){
        try{
            MoteurInferenceIO.exporterBaseDeRegle(path, m_bdr);
        }catch (IOException e){
            System.err.println(e);
        }
    }

    public void loadBdFFile(String path){
        try{
            m_bdf = MoteurInferenceIO.importerBaseDeFait(path);
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public void saveBdFFile(String path){
        m_bdf = new BaseDeFait(m_panelLancementMoteur.getPanelBaseDeFait().getListFait());
        try{
            MoteurInferenceIO.exporterBaseDeFait(path, m_bdf);
        }catch(IOException e){
            System.err.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand().startsWith("load") || actionEvent.getActionCommand().startsWith("save")){
            JFileChooser chooser = new JFileChooser();
            int returnValue = chooser.showOpenDialog( null ) ;
            if( returnValue == JFileChooser.APPROVE_OPTION ) {
                File file = chooser.getSelectedFile() ;
                switch (actionEvent.getActionCommand()){
                    case ACTION_LOAD_BDF : loadBdFFile(file.getAbsolutePath());
                    break;
                    case ACTION_LOAD_BDR : loadBdRFile(file.getAbsolutePath());
                    break;
                    case ACTION_SAVE_BDF :saveBdFFile(file.getAbsolutePath());
                    break;
                    case ACTION_SAVE_BDR : loadBdRFile(file.getAbsolutePath());
                    break;
                }
            }
        }
    }
}
