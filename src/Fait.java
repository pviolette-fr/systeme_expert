
public class Fait {
	
	
	private String m_variable;
	private String m_valeur;
	
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
		return m_variable+"="+m_valeur;
	}

	public boolean equals(Object arg0) {
		return (((Fait)arg0).getVar().equals(m_variable)&&((Fait)arg0).getVal().equals(m_valeur));
	}
	
}