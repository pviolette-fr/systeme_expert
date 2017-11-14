import java.util.ArrayList;


public abstract class Chainage {

	protected Paquet m_baseDeRegles;
	protected ArrayList<Fait> m_baseDeFaits;
	
	
	public Chainage(Paquet baseDeRegle, ArrayList<Fait> baseDeFait){
		m_baseDeRegles = baseDeRegle;
		m_baseDeFaits = new ArrayList<Fait>(baseDeFait);
	}
	
	protected abstract boolean rechercheBut(Fait but);
	
	/*
	 * Pour le moment, retourne la première règle applicable qu'il trouve
	 */
	protected Regle rechercheRegleApplicable(Paquet baseDeRegle, ArrayList<Fait> baseDeFaits){
		for(Regle r : baseDeRegle.getRegles()){
			
			if(r.estApplicable(baseDeFaits)){
				return r;
			}
		}
		return null;
	}
	
	static Chainage getChainage(int mode, Paquet baseDeRegle, ArrayList<Fait> baseDeFait){
		switch(mode){
		case 0:
			return new ChainageAvant(baseDeRegle, baseDeFait);
		case 1:
			return new ChainageArriere(baseDeRegle,baseDeFait);
		default:
			return null;
		}
	}
	
}
