package fr.univangers.vrpv.MoteurInference;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valentine Rahier
 */
public class ChainageArriere extends Chainage {
	
	
	private static int NB_ITERATIONS_MAX = 100;

	public ChainageArriere(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
	}

	@Override
	public boolean rechercheBut(Fait but) {
		
		int i =0;
		m_trace+="But : "+but+"\r\n"+
				"Chainage arrière profondeur\r\n";
		
		m_traceAbregee+=m_trace;
		Paquet baseDeRegle= m_baseDeRegles;
		List<Fait> propositionsRecherchees = new ArrayList<Fait>();
		propositionsRecherchees.add(but);

		while(!m_baseDeFaits.contains(but)&&(i<NB_ITERATIONS_MAX)){

			m_trace+="Base de règles : \r\n" +
					baseDeRegle+"\r\n " +
					"Base de fait : \r\n"+
					m_baseDeFaits+"\r\n ";
			
			m_traceAbregee+="Base de faits : \r\n"+
							m_baseDeFaits+"\r\n";
			for(Fait p : propositionsRecherchees){
				m_trace+=p+"\n\r";
			}
			m_trace+="=======================\r\n";
			
			ArrayList<Regle> reglesValides = new ArrayList<Regle>();
			
			for(Regle r : baseDeRegle.getRegles()){
				
				if(propositionsRecherchees.containsAll(r.getConclusion())){
					reglesValides.add(r);
					m_trace+="Règle dont la conclusion est une proposition recherchée : "+r+"\r\n";
				}
				
			}
			
			if(reglesValides.isEmpty()){
				break;
			}
			
			for(Regle regleValide : reglesValides){
				if(m_baseDeFaits.containsAll(regleValide.getPremisses())){
					m_baseDeFaits.addAll(regleValide.getConclusion());
					m_trace+="On ajoute "+regleValide.getConclusion()+" à la base de faits car "+regleValide.getPremisses()+ "appartiennent à la base de fait\r\n";
					baseDeRegle.retirerRegle(regleValide);
					m_trace+="On retire : "+regleValide+" à la base de règle\r\n";
					m_traceAbregee+="Règle appliquée : "+regleValide+"\r\n";
					propositionsRecherchees.remove(regleValide.getConclusion());
					
				}
				else{
					propositionsRecherchees.addAll(regleValide.getPremisses());
				}
			}

			i++;
			
			
		}
		
		if(m_baseDeFaits.contains(but)){
			m_trace+="SUCCES";
			return true;
		}
		else{
			m_trace+="ECHEC";
			return false;
		}
	}
	

}
