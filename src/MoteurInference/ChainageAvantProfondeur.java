package MoteurInference;


import java.util.LinkedList;

import MoteurInference.BaseDeFait;
import MoteurInference.Fait;


/**
 * @author Valentine Rahier
 */
public class ChainageAvantProfondeur extends ChainageAvant {

	public ChainageAvantProfondeur(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
	}

	@Override
	public boolean rechercheBut(Fait but) {
		m_trace+="But : "+but+"\r\n"+
				"Chainage avant profondeur\r\n"+
				"Base de règles : \r\n"+
				m_baseDeRegles+"\r\n"+
				"Base de faits : \r\n"+
				m_baseDeFaits+"\r\n";
		
		m_traceAbregee+="But : "+but+"\r\n"+
				"Chainage avant profondeur\r\n";
		
		Paquet bdr= m_baseDeRegles;
		while(!m_baseDeFaits.contains(but)){	
			
			Regle regleApplicable = rechercheRegleApplicable(bdr,m_baseDeFaits,LES_FAITS_DEDUITS_LES_PLUS_RECENTS);
			m_trace+="Règle applicable : \r\n"+
					regleApplicable+"\r\n";
			
			
			m_traceAbregee+="Base de faits : \r\n"+ m_baseDeFaits;
			if(regleApplicable==null){
				break;
			}
			
			
			
			bdr.retirerRegle(regleApplicable);
			m_trace+="On retire la règle : "+regleApplicable+" de la base de règles\r\n";
			m_traceAbregee +="On applique la règle : "+regleApplicable+"\r\n";
			for(Fait f : regleApplicable.getConclusion()){
				if(!m_baseDeFaits.contains(f)){
					m_trace+="On ajoute la conclusion : "+f+" à la base de faits\r\n";
					m_baseDeFaits.add(f);
				}
			}
			
		}
		
		if(m_baseDeFaits.contains(but)){
			m_trace+="SUCCES\r\n";
			m_traceAbregee+="SUCCES\r\n";
			return true;
		}
		
		m_trace+="ECHEC\r\n";
		m_traceAbregee+="ECHEC\r\n";
		return false;
	}

}
