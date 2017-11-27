package MoteurInference;

public abstract class ChainageAvant extends Chainage {

	public ChainageAvant(Paquet baseDeRegle, BaseDeFait baseDeFait) {
		super(baseDeRegle, baseDeFait);
		// TODO Auto-generated constructor stub
	}

	@Override
	public abstract boolean rechercheBut(Fait but);
	
	protected Paquet getReglesApplicables(BaseDeFait bdf){
		
		Paquet p = new Paquet();
		
		for(Regle r : m_baseDeRegles.getRegles()){
			
			if(r.estApplicable(bdf)){
				p.ajouterRegle(r);
			}
			
		}
		
		return p;
		
	}

}
