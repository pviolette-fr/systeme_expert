import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Moteur {
	
	private ArrayList<Regle> m_baseDeRegles;
	private ArrayList<Proposition> m_baseDeFaits;
	
	public Moteur(ArrayList<Regle> bdr, ArrayList<Proposition> bdf){
		
		m_baseDeRegles = bdr;
		m_baseDeFaits = bdf;
		
	}

	public String chainageAvant(Proposition but){
		
		ArrayList<Regle> baseDeRegle= new ArrayList<Regle>(m_baseDeRegles);
		ArrayList<Proposition> baseDeFaits = new ArrayList<Proposition>(m_baseDeFaits);
		String trace = "";
		
		while(!baseDeFaits.contains(but)&&existeRegleApplicable(baseDeRegle, baseDeFaits)){	
			/*	TODO : modifier conditions de la chaine pour que l'on ne parcours pas deux fois la base de règle à chaque tours
			 */
			
			Regle regleApplicable = null;
			
			for(Regle r : baseDeRegle){
				
				if(r.estApplicable(baseDeFaits)){
					regleApplicable = r;
					trace+=regleApplicable.toString()+" ======  ";
					break;
				}
				
			}
			
			baseDeRegle.remove(regleApplicable);
			baseDeFaits.add(regleApplicable.getConclusion());	
			
		}
		
		if(baseDeFaits.contains(but)){
			trace+=" SUCCES";
			return trace;
		}
		
		trace+=" ECHEC";
		return trace;
	}
	
	public String chainageArriere(Proposition but){
		ArrayList<Regle> baseDeRegle= new ArrayList<Regle>(m_baseDeRegles);
		ArrayList<Proposition> baseDeFaits = new ArrayList<Proposition>(m_baseDeFaits);
		String trace = "";
		ArrayList<Proposition> propositionsRecherchees = new ArrayList<Proposition>();
		propositionsRecherchees.add(but);
		
		int tour = 1;
		while(!baseDeFaits.contains(but)){
		
			System.out.println(tour);
			System.out.println("====BASE DE REGLES====");
			for(Regle r : baseDeRegle){
				System.out.println(r);
			}
			System.out.println("====================");
			System.out.println("====BASE DE FAITS====");
			for(Proposition p : baseDeFaits){
				
				System.out.println(p);
			}
			System.out.println("====================");
			System.out.println("Propositions recherchées");
			for(Proposition p : propositionsRecherchees){
				System.out.println(p);
			}
			System.out.println("====================");
			
			ArrayList<Regle> reglesValide = new ArrayList<Regle>();
			
			for(Regle r : baseDeRegle){
				
				if(propositionsRecherchees.contains(r.getConclusion())){
					reglesValide.add(r);
					System.out.println("Règle dont la conclusion est une proposition recherchée : "+r);
				}
				
			}
			
			if(reglesValide.isEmpty()){
				break;
			}
			
			for(Regle regleValide : reglesValide){
				if(baseDeFaits.containsAll(regleValide.getPremisses())){
					baseDeFaits.add(regleValide.getConclusion());
					System.out.println("On ajoute "+regleValide.getConclusion()+" à la base de faits car "+regleValide.getPremisses()+ "appartiennent à la base de fait");
					baseDeRegle.remove(regleValide);
					System.out.println("On retire : "+regleValide+" à la base de règle");
					propositionsRecherchees.remove(regleValide.getConclusion());
					
				}
				else{
					propositionsRecherchees.addAll(regleValide.getPremisses());
				}
			}
			
			tour++;
			
			
		}
		
		if(baseDeFaits.contains(but)){
			trace+=" SUCCES";
		}
		else{
			trace+=" ECHEC";
		}
		
		
		return trace;
	}
	
	public boolean existeRegleApplicable(ArrayList<Regle> baseDeRegle, ArrayList<Proposition> baseDeFaits){
		for(Regle r : baseDeRegle){
			
			if(r.estApplicable(baseDeFaits)){
				return true;
			}
		}
		return false;
	}

}