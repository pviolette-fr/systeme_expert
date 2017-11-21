package MoteurInference;

import java.util.LinkedList;

/**
 * 
 * @author Valentine Rahier
 *
 */

public class ChainageAvantLargeur extends Chainage {

	public ChainageAvantLargeur(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
		
	}

	@Override
	public boolean rechercheBut(Fait but) {
		
		Paquet bdr = m_baseDeRegles;
		LinkedList<BaseDeFait> fileBaseDeFait = new LinkedList<BaseDeFait>();
		fileBaseDeFait.addLast(m_baseDeFaits);
		
		while(!fileBaseDeFait.isEmpty()){
			BaseDeFait bdf = fileBaseDeFait.removeFirst();
			System.out.println("Base de fait traitée : ");
			System.out.println(bdf);
			Paquet reglesApplicables = getReglesApplicables(bdf);
			
			for(Regle r : reglesApplicables.getRegles()){
				
				for(Fait conclusion : r.getConclusion()){
					if(!m_baseDeFaits.contains(conclusion)){
						m_baseDeFaits.add(conclusion);
					}
				}
				
				if(m_baseDeFaits.contains(but)){
					return true;
				}
				else{
					bdr.retirerRegle(r);
					BaseDeFait nbdf = new BaseDeFait(m_baseDeFaits);
					nbdf.addAll(r.getConclusion());
					
					fileBaseDeFait.addLast(nbdf);
				}
				
			}
			
			
		}		
		
		return false;
	}

	
	private Paquet getReglesApplicables(BaseDeFait bdf){
		
		Paquet p = new Paquet();
		
		for(Regle r : m_baseDeRegles.getRegles()){
			
			if(r.estApplicable(bdf)){
				p.ajouterRegle(r);
			}
			
		}
		
		return p;
		
	}

}
