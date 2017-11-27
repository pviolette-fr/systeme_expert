package MoteurInference;


/**
 * @author Valentine Rahier
 */
public abstract class Chainage {

	
	public static final int CHAINAGE_AVANT_PROF = 0;
	public static final int CHAINAGE_AVANT_LARG = 1;
	public static final int CHAINAGE_ARRIERE = 2;
	
	public static final int LE_PLUS_DE_PREMISSES = 0;
	public static final int LES_FAITS_DEDUITS_LES_PLUS_RECENTS =1;
	
	protected Paquet m_baseDeRegles;
	protected BaseDeFait m_baseDeFaits;
	
	protected String m_trace;
	protected String m_traceAbregee;
	
	public Chainage(Paquet baseDeRegle, BaseDeFait baseDeFait){
		m_baseDeRegles = baseDeRegle;
		m_baseDeFaits = baseDeFait;
		
		m_trace = "";
		m_traceAbregee = "";
	}
	
	public abstract boolean rechercheBut(Fait but);
	
	protected Regle rechercheRegleApplicable(Paquet baseDeRegle, BaseDeFait baseDeFaits, int choixRegle){		
		
		switch(choixRegle){
		case LE_PLUS_DE_PREMISSES:
			
			
			int positionRegle=-1, nbPremisses = 0;
			for(int i=0;i<baseDeRegle.getRegles().size();i++){
				if(baseDeRegle.getRegles().get(i).estApplicable(baseDeFaits)&&(baseDeRegle.getRegles().get(i).getPremisses().size()>nbPremisses)){
					positionRegle = i;
					nbPremisses = baseDeRegle.getRegles().get(i).getPremisses().size();
				}
			}
			
			if(positionRegle!=(-1)){
				return baseDeRegle.getRegles().get(positionRegle);
			}
			
			break;
			
		case LES_FAITS_DEDUITS_LES_PLUS_RECENTS:
			
			BaseDeFait baseDeFaitsTmp = new BaseDeFait();
			
			for(int i=m_baseDeFaits.size()-1;i>=0;i--){
				
				baseDeFaitsTmp.add(m_baseDeFaits.get(i));
				
				for(Regle r: baseDeRegle.getRegles()){
					
					if(r.estApplicable(baseDeFaitsTmp)){
						return r;
					}
					
				}
				
			}	
			
			break;
		}
		
		return null;
	}

	
	public static Chainage getChainage(int mode, Paquet baseDeRegle, BaseDeFait baseDeFait){
		switch(mode){
		case CHAINAGE_AVANT_PROF:
			return new ChainageAvantProfondeur(baseDeRegle, baseDeFait);
		case CHAINAGE_AVANT_LARG:
			return new ChainageAvantLargeur(baseDeRegle, baseDeFait);
		case CHAINAGE_ARRIERE:
			return new ChainageArriere(baseDeRegle,baseDeFait);
		default:
			return null;
		}
	}
	
	public BaseDeFait getBaseDeFaits(){
		return m_baseDeFaits;
	}
	
	public String getTrace(){
		return m_trace;
	}
	
	public String getTraceAbregee(){
		return m_traceAbregee;
	}

}
