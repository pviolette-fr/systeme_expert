import java.util.ArrayList;


public abstract class Chainage {

	protected Paquet m_baseDeRegles;
	protected BaseDeFait m_baseDeFaits;
	
	
	public Chainage(Paquet baseDeRegle, BaseDeFait baseDeFait){
		m_baseDeRegles = baseDeRegle;
		m_baseDeFaits = new BaseDeFait(baseDeFait);
	}
	
	protected abstract boolean rechercheBut(Fait but);
	
	/*
	 * Pour le moment, retourne la première règle applicable qu'il trouve
	 */
	protected Regle rechercheRegleApplicable(Paquet baseDeRegle, BaseDeFait baseDeFaits){
		for(Regle r : baseDeRegle.getRegles()){
			
			if(r.estApplicable(baseDeFaits)){
				return r;
			}
		}
		return null;
	}
	
	static final int CHAINAGE_AVANT = 0;
	static final int CHAINAGE_ARRIERE = 1;
	
	static Chainage getChainage(int mode, Paquet baseDeRegle, BaseDeFait baseDeFait){
		switch(mode){
		case CHAINAGE_AVANT:
			return new ChainageAvant(baseDeRegle, baseDeFait);
		case CHAINAGE_ARRIERE:
			return new ChainageArriere(baseDeRegle,baseDeFait);
		default:
			return null;
		}
	}
	
}
