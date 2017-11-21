package MoteurInference;

import java.util.LinkedList;

import MoteurInference.BaseDeFait;
import MoteurInference.Fait;


/**
 * @author Valentine Rahier
 */
public class ChainageAvantProfondeur extends Chainage {

	public ChainageAvantProfondeur(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
	}

	@Override
	public boolean rechercheBut(Fait but) {
		Paquet baseDeRegle= m_baseDeRegles;
		
		String trace = "";
		
		while(!m_baseDeFaits.contains(but)){	
			/*	TODO : PROBLEME REGLE modifier conditions de la chaine pour que l'on ne parcours pas deux fois la base de règle à chaque tours
			 * 
			 */
			
			Regle regleApplicable = rechercheRegleApplicable(baseDeRegle,m_baseDeFaits,LES_FAITS_DEDUITS_LES_PLUS_RECENTS);
			
			if(regleApplicable==null){
				break;
			}
			
			trace+=regleApplicable.toString()+" ======  ";
			
			baseDeRegle.retirerRegle(regleApplicable);
			
			for(Fait f : regleApplicable.getConclusion()){
				if(!m_baseDeFaits.contains(f)){
					m_baseDeFaits.add(f);
				}
			}
			
		}
		
		if(m_baseDeFaits.contains(but)){
			trace+=" SUCCES";
			return true;
		}
		
		trace+=" ECHEC";
		return false;
	}

}
