package MoteurInference;

import MoteurInference.BaseDeFait;
import MoteurInference.Fait;


/**
 * @author Valentine Rahier
 */
public class ChainageAvant extends Chainage {

	public ChainageAvant(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
	}

	@Override
	public boolean rechercheBut(Fait but) {
		Paquet baseDeRegle= m_baseDeRegles;
		BaseDeFait baseDeFaits = new BaseDeFait( m_baseDeFaits );
		String trace = "";
		
		while(!baseDeFaits.contains(but)){	
			/*	TODO : PROBLEME REGLE modifier conditions de la chaine pour que l'on ne parcours pas deux fois la base de règle à chaque tours
			 * 
			 */
			
			Regle regleApplicable = rechercheRegleApplicable(baseDeRegle,baseDeFaits);
			
			if(regleApplicable==null){
				break;
			}
			
			trace+=regleApplicable.toString()+" ======  ";
			
			baseDeRegle.retirerRegle(regleApplicable);
			baseDeFaits.addAll(regleApplicable.getConclusion());	
			
		}
		
		if(baseDeFaits.contains(but)){
			trace+=" SUCCES";
			return true;
		}
		
		trace+=" ECHEC";
		return false;
	}

}
