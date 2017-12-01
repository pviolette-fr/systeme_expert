package fr.univangers.vrpv.Controller;

import fr.univangers.vrpv.GUI.PanelLancementMoteur;
import fr.univangers.vrpv.MoteurInference.BaseDeFait;
import fr.univangers.vrpv.MoteurInference.BaseDeRegle;
import fr.univangers.vrpv.MoteurInference.Fait;
import fr.univangers.vrpv.MoteurInference.Paquet;
import fr.univangers.vrpv.MoteurInference.MoteurInferenceIO;

import javax.swing.*;

public class MoteurController {

    PanelLancementMoteur m_panel;
    BaseDeRegle m_bdr;
    BaseDeFait m_bdf;

    public MoteurController(){

    }

    public void setPanel(PanelLancementMoteur panel){
        m_panel = panel;
        redoPanel();
    }

    public void redoPanel(){
        if(m_bdr != null){
            DefaultListModel<Paquet> l = new DefaultListModel<>();
            for(Paquet p : m_bdr){
                l.addElement(p);
            }
            m_panel.setListModelPaquet(l);
        }
        if(m_bdf != null){

            DefaultListModel<Fait> l = new DefaultListModel<>();
            for(Fait f : m_bdf){
                l.addElement(f);
            }
            m_panel.getPanelBaseDeFait().setListModele(l);
        }
    }

    public void loadBdRFile(String path){
        MoteurInferenceIO.creerBaseDeRegle(path);
    }

    public void saveBdRFile(String path){

    }

    public void loadBdFFile(String path){

    }

    public void saveBdFFile(String path){

    }
}
