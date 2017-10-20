
public class Proposition{

	private String m_variable;
	private String m_valeur;
	
	
	public Proposition(String variable, String valeur){
		m_variable = variable;
		m_valeur = valeur;
	}
	
	public Proposition(){
		
	}
	
	public String getVariable(){
		return m_variable;
	}
	
	public String getValeur(){
		return m_valeur;
	}
	
	public void setVariable(String var){
		m_variable = var;
	}
	
	public void setValeur(String val){
		m_valeur = val;
	}

	public boolean equals(Object arg0) {
		return (((Proposition)arg0).getVariable().equals(m_variable)&&((Proposition)arg0).getValeur().equals(m_valeur));
	}
	
	public String toString(){
		return m_variable+"="+m_valeur;
	}

}
