import java.util.ArrayList;


public class Regle {

	private  ArrayList<Fait> m_premisses;
	private ArrayList<Fait> m_conclusions;
	
	public Regle(){
		m_premisses = new ArrayList<Fait>();
		m_conclusions = new ArrayList<Fait>();	
	}

	public Regle(ArrayList<Fait> premisses, ArrayList<Fait> conclusion){
		
		m_premisses = new ArrayList<Fait>(premisses);
		m_conclusions = new ArrayList<Fait>(conclusion);
		
	}
	
	public boolean estApplicable(ArrayList<Fait> baseDeFait){
		boolean estApplicable = true;
		for(Fait p : m_premisses){
		
			if(!baseDeFait.contains(p)){
				estApplicable=false;
				break;
			}
		}
		
		return estApplicable;
		
	}
	
	public void addPremisse(String variable, String valeur){
		m_premisses.add(new Fait(variable,valeur));
	}
	
	public void setConclusion(String variable, String valeur){
		m_conclusions.add(new Fait(variable,valeur));
	}
	
	public ArrayList<Fait> getPremisses(){
		return m_premisses;
	}
	
	public ArrayList<Fait> getConclusion(){
		return m_conclusions;
	}

	public String toString(){
		String regleToString="SI ";
		for(Fait p : m_premisses){
			regleToString+=p.toString()+" ET";
		}
		
		regleToString+="ALORS ";
		for(Fait p : m_conclusions){
			regleToString+=m_conclusions.toString()+" ET";
		}
				
		return regleToString;
	}
}
