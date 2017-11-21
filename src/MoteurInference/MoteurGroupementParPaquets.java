package MoteurInference;


/**
 * @author Valentine Rahier
 */
public class MoteurGroupementParPaquets {

	private int m_mode;
	
	private BaseDeRegle m_bdr;
	private BaseDeFait m_baseDefaits;
	
	public MoteurGroupementParPaquets(BaseDeRegle bdr, BaseDeFait baseDeFaits, int mode){
		
		m_bdr = new BaseDeRegle(bdr);
		m_baseDefaits = new BaseDeFait(baseDeFaits);
		
		m_mode = mode;
		
	}
		
	public boolean rechercherBut(Fait but){
		
		for(Paquet p : m_bdr){
			
			Chainage c = Chainage.getChainage(m_mode, p, m_baseDefaits);
			
			if(c.rechercheBut(but)){
				return true;
			}
			
		}

		
		return false;
		
	}
	
	
}
