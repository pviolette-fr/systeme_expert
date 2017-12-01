package fr.univangers.vrpv.MoteurInference;

import org.json.simple.JSONObject;


/**
 * @author Valentine Rahier
 *
 */

public class Fait {

	static final String JSON_VAR_KEY = "var";
	static final String JSON_VALUE_KEY = "value";
	
	private String m_variable;
	private String m_valeur;

    /**
     *
     * @param variable le nom de la variable associé
     * @param valeur la valeur de la variable
     */
	public Fait(String variable,String valeur){
		
		m_variable = variable;
		m_valeur = valeur;
	
	}
	
	public String getVar(){
		return m_variable;
	}
	
	public String getVal(){
		return m_valeur;
	}
	
	public String toString(){
		return '"' + m_variable+"\" = \""+m_valeur + '"';
	}

	public boolean equals(Object arg0) {
		return (((Fait)arg0).getVar().equals(m_variable)&&((Fait)arg0).getVal().equals(m_valeur));
	}

    /**
     * @param obj un JSONObject à transformer en Fait
     * @return un objet Fait correspondant au JSONObject passé en paramètre.
     */

	public static Fait parseJSON(JSONObject obj){
		return new Fait( (String) obj.get(JSON_VAR_KEY), (String) obj.get(JSON_VALUE_KEY));
	}

    /**
     * @return Un JSONObject représentant l'objet.
     */

	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject();
		json.put(JSON_VAR_KEY, m_variable);
		json.put(JSON_VALUE_KEY, m_variable);
		return json;
	}
}