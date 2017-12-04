package fr.univangers.vrpv.MoteurInference;

import java.util.LinkedList;

/**
 * @author Valentine Rahier
 *
 */

public class ChainageAvantLargeur extends ChainageAvant {

	public ChainageAvantLargeur(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
		
	}

	@Override
	public boolean rechercheBut(Fait but) {
		m_trace+="But : "+but+"\r\n"+
				"Chainage avant largeur\r\n";
		m_traceAbregee+=m_trace;
		Paquet bdr = m_baseDeRegles;
		LinkedList<BaseDeFait> fileBaseDeFait = new LinkedList<BaseDeFait>();
		fileBaseDeFait.addLast(m_baseDeFaits);
		
		while(!fileBaseDeFait.isEmpty()){
			BaseDeFait bdf = fileBaseDeFait.removeFirst();
			m_trace+="Base de fait traitée : \r\n"+
					bdf+"\r\n";
			
			m_traceAbregee+="Base de fait traitée : \r\n"+
							bdf+"\r\n";

			
			Paquet reglesApplicables = getReglesApplicables(bdf);
			
			m_trace+="Règles applicables :\r\n"+
					reglesApplicables+"\r\n";
			
			
			for(Regle r : reglesApplicables.getRegles()){
				
				m_traceAbregee += "On applique la règle : "+r+"\r\n";
				for(Fait conclusion : r.getConclusion()){
					if(!m_baseDeFaits.contains(conclusion)){
						m_trace+="On ajoute la conclusion "+conclusion+" à la base de fait\r\n";
						m_baseDeFaits.add(conclusion);
					}
				}
				
				if(m_baseDeFaits.contains(but)){
					m_trace+="SUCCES avec Proba " + m_baseDeFaits.get(m_baseDeFaits.indexOf(but)).getProba();
					m_traceAbregee+="SUCCES avec Proba " + m_baseDeFaits.get(m_baseDeFaits.indexOf(but)).getProba();
					return true;
				}
				else{
					m_trace+="On retire la règle "+r+"\r\n";
					bdr.retirerRegle(r);
					BaseDeFait nbdf = new BaseDeFait(m_baseDeFaits);
					m_trace+="Nouvelle base de fait ajoutée à la file :"+"\r\n"+
							nbdf+"\r\n";
					nbdf.addAll(r.getConclusion());
					
					
					fileBaseDeFait.addLast(nbdf);
				}
				
			}
			
			
		}		
		m_trace+="ECHEC\r\n";
		m_traceAbregee+="ECHEC\r\n";
		return false;
	}

}
