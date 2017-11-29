package MoteurInference;


/**
 * @author Valentine Rahier
 */
public class MoteurGroupementParPaquets {

	private TypeChainage m_mode;
	
	private String m_trace;
	private String m_traceAbregee;
	
	private BaseDeRegle m_bdr;
	private BaseDeFait m_baseDefaits;
	
	public MoteurGroupementParPaquets(BaseDeRegle bdr, BaseDeFait baseDeFaits, TypeChainage mode){
		
		m_bdr = new BaseDeRegle(bdr);
		m_baseDefaits = new BaseDeFait(baseDeFaits);
		
		m_mode = mode;
		
		m_trace = "";
		m_traceAbregee = "";
		
	}
		
	public boolean rechercherBut(Fait but){
		
		
		for(Paquet p : m_bdr){
			System.out.println("Base de faits : ");
			System.out.println(m_baseDefaits);

			
			Chainage c = Chainage.getChainage(m_mode, p, m_baseDefaits);
			
			if(c.rechercheBut(but)){
				m_trace+=c.getTrace();
				m_traceAbregee+=c.getTraceAbregee();
				return true;
			}
			
			m_trace+=c.getTrace();
			m_traceAbregee+=c.getTraceAbregee();
			m_baseDefaits = c.getBaseDeFaits();
			
		}

		return false;
		
	}
	
	public String getTrace(){
		return m_trace;
	}
	
	public String getTraceAbregee(){
		return m_traceAbregee;
	}
	
}
