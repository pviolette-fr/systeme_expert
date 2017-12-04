package fr.univangers.vrpv.MoteurInference;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;

/**
 * @author Valentine Rahier
 */

public class Fait implements JSONAware {
    public static final String JSON_VAR_KEY = "var";
    public static final String JSON_VALUE_KEY = "value";
    public static final String JSON_EGAL_KEY = "=";
    public static final String JSON_PROBA_KEY = "proba";

    private String m_variable;
    private String m_valeur;
    private boolean m_egalite;
    private double m_proba;

    /**
     * @param variable le nom de la variable associé
     * @param valeur   la valeur de la variable
     *                 m_egalite par defaut à vrai
     */
    public Fait(String variable, String valeur) {
        m_variable = variable;
        m_valeur = valeur;
        m_egalite = true;
        m_proba = 1;
    }


    public Fait(String variable, String valeur, boolean egalite) {

        m_variable = variable;
        m_valeur = valeur;
        m_egalite = egalite;
        m_proba = 1;
    }

    public Fait(String variable, String valeur, boolean egalite, double proba) {
        m_variable = variable;
        m_valeur = valeur;
        m_egalite = egalite;
        if(proba >= 0){
            if(proba <= 1)
                m_proba = proba;
            else
                m_proba = 1;
        }
        else
            m_proba = 0;
    }

    public String getVar() {
        return m_variable;
    }

    public String getVal() {
        return m_valeur;
    }

    public boolean egalite() {
        return m_egalite;
    }

    public double getProba() {
        return m_proba;
    }

    public String toString() {
        return '"' + m_variable + "\" " + (m_egalite ? "=" : "!=") + " \"" + m_valeur + "\" (" + m_proba + ")";
    }

    public boolean equals(Object arg0) {
        return (((Fait) arg0).getVar().equals(m_variable) && ((Fait) arg0).getVal().equals(m_valeur) && ((Fait) arg0).egalite() == m_egalite);
    }

    /**
     * @param obj un JSONObject à transformer en Fait
     * @return un objet Fait correspondant au JSONObject passé en paramètre.
     */
    public static Fait parseJSON(JSONObject obj) {
        boolean egalite = true;
        double proba = 1;
        if (obj.get(JSON_EGAL_KEY) != null) {
            egalite = (boolean) obj.get(JSON_EGAL_KEY);
        }
        if (obj.get(JSON_PROBA_KEY) != null){
            proba = (double) obj.get(JSON_PROBA_KEY);
        }
        return new Fait((String) obj.get(JSON_VAR_KEY), (String) obj.get(JSON_VALUE_KEY), egalite, proba);
    }

    /**
     * @return Un JSONObject représentant l'objet.
     */
    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        json.put(JSON_VAR_KEY, m_variable);
        json.put(JSON_VALUE_KEY, m_valeur);
        json.put(JSON_EGAL_KEY, m_egalite);
        json.put(JSON_PROBA_KEY, m_proba);
        return json;
    }

    @Override
    public String toJSONString() {
        return toJSONObject().toJSONString();
    }
}