package fr.univangers.vrpv.MoteurInference;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * @author Valentine Rahier
 *
 */

public class Fait implements JSONAware {
	static final String JSON_VAR_KEY = "var";
	static final String JSON_VALUE_KEY = "value";
	static final String JSON_EGAL_KEY = "=";
	
	private String m_variable;
	private String m_valeur;
	private boolean m_egalite;

    /**
     * @param variable le nom de la variable associé
     * @param valeur la valeur de la variable
     * m_egalite par defaut à vrai
     */
	public Fait(String variable, String valeur){
		m_variable = variable;
		m_valeur = valeur;
		m_egalite = true;
	}
	
	
	public Fait(String variable,String valeur,boolean egalite){
		
		m_variable = variable;
		m_valeur = valeur;
		m_egalite = egalite;
	
	}
	
	public String getVar(){
		return m_variable;
	}
	
	public String getVal(){
		return m_valeur;
	}
	
	public boolean egalite(){
		return m_egalite;
	}
	
	public String toString(){
		if(m_egalite){
			return '"' + m_variable+"\" = \""+m_valeur + '"';
		}
		else{
			return '"' + m_variable+"\" != \""+m_valeur + '"';
		}
		
	}

	public boolean equals(Object arg0) {
		return (((Fait)arg0).getVar().equals(m_variable)&&((Fait)arg0).getVal().equals(m_valeur)&&((Fait)arg0).egalite()==m_egalite);
	}

    /**
     * @param obj un JSONObject à transformer en Fait
     * @return un objet Fait correspondant au JSONObject passé en paramètre.
     */

	public static Fait parseJSON(JSONObject obj){
		return new Fait( (String) obj.get(JSON_VAR_KEY), (String) obj.get(JSON_VALUE_KEY), (boolean)obj.get(JSON_EGAL_KEY));
	}

    /**
     * @return Un JSONObject représentant l'objet.
     */

	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject();
		json.put(JSON_VAR_KEY, m_variable);
		json.put(JSON_VALUE_KEY, m_valeur);
		json.put(JSON_EGAL_KEY,m_egalite);
		return json;
	}

	@Override
	public String toJSONString() {
		return toJSONObject().toJSONString();
	}
}