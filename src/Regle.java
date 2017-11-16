import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Regle {

	private  List<Fait> m_premisses;
	private List<Fait> m_conclusions;
	
	public Regle(){
		m_premisses = new ArrayList<Fait>();
		m_conclusions = new ArrayList<Fait>();	
	}

	public Regle(List<Fait> premisses, List<Fait> conclusion){
		
		m_premisses = new ArrayList<Fait>(premisses);
		m_conclusions = new ArrayList<Fait>(conclusion);
		
	}
	
	public boolean estApplicable(List<Fait> baseDeFait){
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
		
	public void addConclusion(String variable, String valeur){
		m_conclusions.add(new Fait(variable,valeur));
	}
	
	public List<Fait> getPremisses(){
		return m_premisses;
	}
	
	public List<Fait> getConclusion(){
		return m_conclusions;
	}

	public static Regle parseJSON(JSONObject obj){

		List<Fait> premisses = new LinkedList<Fait>();
		List<Fait> conclusions = new LinkedList<Fait>();
		
		JSONArray json_premisse = (JSONArray) obj.get("premisse");
		for(Object o : json_premisse){
			premisses.add(Fait.parseJSON((JSONObject) o));
		}
		JSONArray json_conclusion = (JSONArray) obj.get("conclusion");
		for(Object o : json_conclusion){
			conclusions.add(Fait.parseJSON((JSONObject) o));
		}
				
		return new Regle(premisses, conclusions);
	}
	
	public String toString(){
		String regleToString="SI ";
		Iterator<Fait> it = m_premisses.iterator();
		while(it.hasNext()){
			regleToString += it.next().toString();
			if(it.hasNext()){
				regleToString += " ET ";
			}
		}
		
		regleToString+= System.lineSeparator() + "\t\t ALORS ";
		it = m_conclusions.iterator();
		while(it.hasNext()){
			regleToString += it.next().toString();
			if(it.hasNext()){
				regleToString += " ET ";
			}
		}
		
				
		return regleToString;
	}
}
