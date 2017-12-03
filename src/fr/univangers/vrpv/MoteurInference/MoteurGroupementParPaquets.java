package fr.univangers.vrpv.MoteurInference;

import java.util.Set;


/**
 * @author Valentine Rahier
 */
public class MoteurGroupementParPaquets {

    private ModeChainage m_mode;
    private int m_priorite;

    private String m_trace;
    private String m_traceAbregee;

    private BaseDeRegle m_bdr;
    private BaseDeFait m_baseDefaits;


    static final int PRIORITE_MOTEUR = 0;
    static final int PRIORITE_PAQUET = 1;

    public MoteurGroupementParPaquets(BaseDeRegle bdr, BaseDeFait baseDeFaits, ModeChainage mode) {
        m_bdr = new BaseDeRegle(bdr);
        m_baseDefaits = new BaseDeFait(baseDeFaits);

        m_mode = mode;
        m_priorite = PRIORITE_MOTEUR;

        m_trace = "";
        m_traceAbregee = "";
    }

    public MoteurGroupementParPaquets(BaseDeRegle bdr, BaseDeFait baseDeFaits, ModeChainage mode, int priorite) {
        m_bdr = new BaseDeRegle(bdr);
        m_baseDefaits = new BaseDeFait(baseDeFaits);

        m_mode = mode;
        m_priorite = priorite;

        m_trace = "";
        m_traceAbregee = "";
    }

    private ModeChainage selectMode(ModeChainage modePrefPaquet) {
        if (m_mode == ModeChainage.DEFAULT && modePrefPaquet == ModeChainage.DEFAULT) {
            return ModeChainage.DEFAULT;
        }

        if (m_priorite == PRIORITE_MOTEUR) {
            if (m_mode == ModeChainage.DEFAULT) {
                return modePrefPaquet;
            } else {
                return m_mode;
            }
        } else { //Priorite Paquet
            if (modePrefPaquet == ModeChainage.DEFAULT) {
                return m_mode;
            } else {
                return modePrefPaquet;
            }
        }
    }

    public boolean rechercherBut(Fait but) {
        for (Paquet p : m_bdr) {
            System.out.println("Base de faits : ");
            System.out.println(m_baseDefaits);

            Chainage c = Chainage.getChainage(selectMode(p.getModeChainagePreferenciel()), p, m_baseDefaits);

            if (c.rechercheBut(but)) {
                m_trace += c.getTrace();
                m_traceAbregee += c.getTraceAbregee();
                return true;
            }

            m_trace += c.getTrace();
            m_traceAbregee += c.getTraceAbregee();
            m_baseDefaits = c.getBaseDeFaits();
            
            if(!coherenceUnicite()||!coherenceEgalite()){
            	m_trace+="Attention la base de fait : \r\n"+
            			m_baseDefaits+
            			" n'est pas cohérente.\r\n";
            }
        }
        return false;
    }

    /**
     * Teste si la bdf est cohérente
     * Si bdf contient {var=val1} et {var=val2} alors @return false
     */
    
	public boolean coherenceUnicite(){

		Set<String> variablesUniques = m_bdr.getCoherence().keySet();
		
		for(String varUnique : variablesUniques){
			
			int nbApparitions = 0;
			
			for(Fait f : m_baseDefaits){
			
				if(f.getVar().equals(varUnique)){
					nbApparitions++;
				}
				
			}
			
			if(nbApparitions>1){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Teste si la bdf est coherente
	 * Si bdf contient {var=val} et {var!=val} alors @return false
	 */
	
	public boolean coherenceEgalite(){
		
		for(Fait f : m_baseDefaits){
			
			Fait opposé = new Fait(f.getVar(),f.getVal(),!f.egalite());
			
			if(m_baseDefaits.contains(opposé)){
				return false;
			}
			
		}
		
		return true;
		
	}

    public String getTrace() {
        return m_trace;
    }

    public String getTraceAbregee() {
        return m_traceAbregee;
    }

}
