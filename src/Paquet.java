import java.util.ArrayList;


public class Paquet {
	
	private ArrayList<Regle> m_regles;
	
	public Paquet(){
		
		m_regles = new ArrayList<Regle>();
		
	}
	
	public Paquet(ArrayList<Regle> regles){
		
		m_regles = new ArrayList<Regle>(regles);
		
	}
	
	public void ajouterRegle(Regle r){
		
		m_regles.add(r);
		
	}
	
	public void retirerRegle(Regle r){
		m_regles.remove(r);
	}
	
	public ArrayList<Regle> getRegles(){
		
		return m_regles;
		
	}
	

}
