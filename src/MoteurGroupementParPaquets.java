import java.util.ArrayList;


public class MoteurGroupementParPaquets {

	private int m_mode;
	
	private ArrayList<Paquet> m_paquets;
	private ArrayList<Fait> m_baseDefaits;
	
	public MoteurGroupementParPaquets(ArrayList<Paquet> paquets, ArrayList<Fait> baseDeFaits, int mode){
		
		m_paquets = new ArrayList<Paquet>(paquets);
		m_baseDefaits = new ArrayList<Fait>(baseDeFaits);
		
		m_mode = mode;
		
	}
	
	public boolean rechercherBut(Fait but){
		
		for(Paquet p : m_paquets){
			
			Chainage c = Chainage.getChainage(m_mode, p, m_baseDefaits);
			
			if(c.rechercheBut(but)){
				return true;
			}
			
		}

		
		return false;
		
	}
	
	
}
