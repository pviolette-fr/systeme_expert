package MoteurInference;

import java.util.ArrayList;


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
	
	
	public Chainage(Paquet baseDeRegle, BaseDeFait baseDeFait){
		m_baseDeRegles = baseDeRegle;
		m_baseDeFaits = baseDeFait;
	}
	
	public abstract boolean rechercheBut(Fait but);
	
	protected Regle rechercheRegleApplicable(Paquet baseDeRegle, BaseDeFait baseDeFaits, int choixRegle){
		
		System.out.println("Appel méthode");
		
		
		switch(choixRegle){
		case LE_PLUS_DE_PREMISSES:
			
			
			int positionRegle=-1, nbPremisses = 0;
			for(int i=0;i<baseDeRegle.getRegles().size();i++){
				if(baseDeRegle.getRegles().get(i).estApplicable(baseDeFaits)&&(baseDeRegle.getRegles().get(i).getPremisses().size()>nbPremisses)){
					positionRegle = i;
					nbPremisses = baseDeRegle.getRegles().get(i).getPremisses().size();
				}
				System.out.println("Regle "+i);
				System.out.println(baseDeRegle.getRegles().get(i));
			}
			
			if(positionRegle!=(-1)){
				return baseDeRegle.getRegles().get(positionRegle);
			}
			
			break;
			
		case LES_FAITS_DEDUITS_LES_PLUS_RECENTS:
			
			
			int i=1;
			BaseDeFait baseDeFaitTmp = new BaseDeFait();			
			
			while(baseDeFaits.size()!=baseDeFaitTmp.size()){
			
				baseDeFaitTmp.add(m_baseDeFaits.get(m_baseDeFaits.size()-i));
				System.out.println("Base de faits tmp : ");
				System.out.println(baseDeFaitTmp);
				
				for(Regle r : baseDeRegle.getRegles()){
					
					if(r.estApplicable(baseDeFaitTmp)){
						System.out.println("Regle retournee : ");
						System.out.println(r);
						return r;
					}
					
				}
				i++;
				baseDeFaitTmp.add(m_baseDeFaits.get(m_baseDeFaits.size()-i));
			}			
			
			break;

		default:
			
		
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
	
}
