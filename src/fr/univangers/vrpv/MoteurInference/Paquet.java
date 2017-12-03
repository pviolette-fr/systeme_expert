package fr.univangers.vrpv.MoteurInference;

import java.util.*;
import java.util.function.Consumer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Paquet implements Iterable<Regle>, Cloneable {

	static final String JSON_MODE_KEY = "mode";
	static final String JSON__REGLES_KEY = "regles";
	
	private List<Regle> m_regles;
    ModeChainage m_modeChainagePreferenciel;

    public Paquet(){
		
		m_regles = new ArrayList<Regle>();
		m_modeChainagePreferenciel = ModeChainage.DEFAULT;
	}
	
	public Paquet(List<Regle> regles){
		
		m_regles = new ArrayList<Regle>(regles);
		
	}

	public Paquet(Paquet other){
	    m_regles = new ArrayList<>(other.m_regles);
	    m_modeChainagePreferenciel = other.m_modeChainagePreferenciel;
    }

	public Paquet(List<Regle> regles, ModeChainage modeChainagePreferentielle){
	    m_regles = new ArrayList<>(regles);
	    m_modeChainagePreferenciel = modeChainagePreferentielle;
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

	public int size(){
		return m_regles.size();
	}

    public ModeChainage getModeChainagePreferenciel() {
        return m_modeChainagePreferenciel;
    }

    public void setModeChainagePreferenciel(ModeChainage mCh){
	    m_modeChainagePreferenciel = mCh;
    }

	public String toString(){
		String res = "=============PAQUET=============" + System.lineSeparator()
                + "Mode Preferentiel :\t" + m_modeChainagePreferenciel + System.lineSeparator();
		for(Regle r : m_regles){
			res += "\t*" + r.toString() + System.lineSeparator();
		}		
		res +="================================";
		return res;
	}
	
	public static Paquet parseJSON(JSONObject obj){
		
		List<Regle> regles = new LinkedList<Regle>();
		JSONArray json_regles = (JSONArray) obj.get(JSON__REGLES_KEY);
		for(Object o : json_regles) {
            regles.add(Regle.parseJSON((JSONObject) o));
        }

		ModeChainage mCh = ModeChainage.parseString(((String) obj.get(JSON_MODE_KEY)).toUpperCase());
		if(mCh == null){
			System.err.println("Paquet.parseJSON : no mode specified. Setting DEFAULT");
			mCh = ModeChainage.DEFAULT;
		}
		return new Paquet(regles, mCh);
	}

	public JSONObject toJSONObject(){
		JSONObject json = new JSONObject();

		JSONArray regles = new JSONArray();

		for(Regle r : m_regles){
			regles.add(r.toJSONObject());
		}

		json.put(JSON_MODE_KEY, m_modeChainagePreferenciel);
		json.put(JSON__REGLES_KEY, regles);


		return json;
	}

    @Override
    public Iterator<Regle> iterator() {
        return m_regles.iterator();
    }

    @Override
    public void forEach(Consumer<? super Regle> consumer) {
        m_regles.forEach(consumer);
    }

    @Override
    public Spliterator<Regle> spliterator() {
        return m_regles.spliterator();
    }

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Paquet(m_regles, m_modeChainagePreferenciel);
	}
}
