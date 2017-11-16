import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Paquet {
	
	private List<Regle> m_regles;
	
	public Paquet(){
		
		m_regles = new ArrayList<Regle>();
		
	}
	
	public Paquet(List<Regle> regles){
		
		m_regles = new ArrayList<Regle>(regles);
		
	}
	
	public void ajouterRegle(Regle r){
		
		m_regles.add(r);
		
	}
	
	public void retirerRegle(Regle r){
		m_regles.remove(r);
	}
	
	public List<Regle> getRegles(){
		
		return m_regles;
		
	}
	
	public String toString(){
		String res = "=============PAQUET=============" + System.lineSeparator();
		for(Regle r : m_regles){
			res += "\t*" + r.toString() + System.lineSeparator();
		}		
		res +="================================";
		return res;
	}
	
	public static Paquet parseJSON(JSONObject obj){
		
		List<Regle> regles = new LinkedList<Regle>();
		JSONArray json_regles = (JSONArray) obj.get("regles");
		for(Object o : json_regles){
			regles.add( Regle.parseJSON((JSONObject) o));
		}
		
		return new Paquet(regles);
	}
}
