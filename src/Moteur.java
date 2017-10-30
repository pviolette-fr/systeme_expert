import java.util.ArrayList;


public class Moteur {
	
	private ArrayList<Regle> m_baseDeRegles;
	private ArrayList<Fait> m_baseDeFaits;
	
	public Moteur(ArrayList<Regle> bdr, ArrayList<Fait> bdf){
		
		m_baseDeRegles = bdr;
		m_baseDeFaits = bdf;
		
	}

	public String chainageAvant(Fait but){
		
		ArrayList<Regle> baseDeRegle= new ArrayList<Regle>(m_baseDeRegles);
		ArrayList<Fait> baseDeFaits = new ArrayList<Fait>(m_baseDeFaits);
		String trace = "";
		
		while(!baseDeFaits.contains(but)){	
			/*	TODO : PROBLEME REGLE modifier conditions de la chaine pour que l'on ne parcours pas deux fois la base de règle à chaque tours
			 * 
			 */
			
			Regle regleApplicable = rechercheRegleApplicable(baseDeRegle,baseDeFaits);
			
			if(regleApplicable==null){
				break;
			}
			
			trace+=regleApplicable.toString()+" ======  ";
			
			baseDeRegle.remove(regleApplicable);
			baseDeFaits.addAll(regleApplicable.getConclusion());	
			
		}
		
		if(baseDeFaits.contains(but)){
			trace+=" SUCCES";
			return trace;
		}
		
		trace+=" ECHEC";
		return trace;
	}
	
	public String chainageArriere(Fait but){
		ArrayList<Regle> baseDeRegle= new ArrayList<Regle>(m_baseDeRegles);
		ArrayList<Fait> baseDeFaits = new ArrayList<Fait>(m_baseDeFaits);
		String trace = "";
		ArrayList<Fait> propositionsRecherchees = new ArrayList<Fait>();
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
			for(Fait p : baseDeFaits){
				
				System.out.println(p);
			}
			System.out.println("====================");
			System.out.println("Propositions recherchées");
			for(Fait p : propositionsRecherchees){
				System.out.println(p);
			}
			System.out.println("====================");
			
			ArrayList<Regle> reglesValide = new ArrayList<Regle>();
			
			for(Regle r : baseDeRegle){
				
				if(propositionsRecherchees.containsAll(r.getConclusion())){
					reglesValide.add(r);
					System.out.println("Règle dont la conclusion est une proposition recherchée : "+r);
				}
				
			}
			
			if(reglesValide.isEmpty()){
				break;
			}
			
			for(Regle regleValide : reglesValide){
				if(baseDeFaits.containsAll(regleValide.getPremisses())){
					baseDeFaits.addAll(regleValide.getConclusion());
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
	
	
	/*
	 * Pour le moment, retourne la première règle applicable qu'il trouve
	 */
	public Regle rechercheRegleApplicable(ArrayList<Regle> baseDeRegle, ArrayList<Fait> baseDeFaits){
		for(Regle r : baseDeRegle){
			
			if(r.estApplicable(baseDeFaits)){
				return r;
			}
		}
		return null;
	}

}