import java.util.ArrayList;


public class Regle {

	private  ArrayList<Proposition> m_premisses;
	private Proposition m_conclusion;
	
	public Regle(){
		m_premisses = new ArrayList<Proposition>();
		m_conclusion = new Proposition();
		
	}
	
	public Regle(ArrayList<Proposition> premisses, Proposition conclusion){
		
		m_premisses = new ArrayList<Proposition>(premisses);
		m_conclusion = conclusion;
		
	}
	
	public boolean estApplicable(ArrayList<Proposition> baseDeFait){
		boolean estApplicable = true;
		for(Proposition p : m_premisses){
		
			if(!baseDeFait.contains(p)){
				estApplicable=false;
				break;
			}
		}
		
		return estApplicable;
		
	}
	
	public void addPremisse(String variable, String valeur){
		m_premisses.add(new Proposition(variable,valeur));
	}
	
	public void setConclusion(String variable, String valeur){
		m_conclusion.setVariable(variable);
		m_conclusion.setValeur(valeur);
	}
	
	public ArrayList<Proposition> getPremisses(){
		return m_premisses;
	}
	
	public Proposition getConclusion(){
		return m_conclusion;
	}

	public String toString(){
		String regleToString="SI ";
		for(Proposition p : m_premisses){
			regleToString+=p.toString()+" ET";
		}
		
		regleToString+="ALORS "+m_conclusion.toString();
		return regleToString;
	}
}
