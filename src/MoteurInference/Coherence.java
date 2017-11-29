package MoteurInference;

import java.util.Set;
/**
 * Gère la cohérence
 * @author Valentine
 *
 */
public class Coherence {

	
	/**
	 * Teste si une base de règle est cohérence grâce aux variables uniques
	 * ex : un livre n'appartient qu'à une seule époque
	 * @param bdr
	 * @return
	 */
	
	public static boolean estCoherent(BaseDeRegle bdr){
		
		
		Set<String> variablesUniques = bdr.getCoherence().keySet();
		
		for(Paquet p : bdr){
			
			for(Regle r : p.getRegles()){
				
				for(String varUnique : variablesUniques){
					
					int nbApparitions = 0;
					
					for(Fait conclusion : r.getConclusion()){
						
						if(conclusion.getVar().equals(varUnique)){
							nbApparitions++;
						}
					}
					
					if(nbApparitions>1){
						return false;
					}
					
					
				}
				
			}
		}
		
		
		return true;
	}
}
