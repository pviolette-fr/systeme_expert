import java.util.ArrayList;


public class ChainageArriere extends Chainage{

	public ChainageArriere(Paquet baseDeRegle, ArrayList<Fait> baseDeFait) {
		super(baseDeRegle, baseDeFait);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean rechercheBut(Fait but) {
		Paquet baseDeRegle= m_baseDeRegles;
		ArrayList<Fait> baseDeFaits = m_baseDeFaits;
		String trace = "";
		ArrayList<Fait> propositionsRecherchees = new ArrayList<Fait>();
		propositionsRecherchees.add(but);
		
		int tour = 1;
		while(!baseDeFaits.contains(but)){
		
			System.out.println(tour);
			System.out.println("====BASE DE REGLES====");
			for(Regle r : baseDeRegle.getRegles()){
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
			
			for(Regle r : baseDeRegle.getRegles()){
				
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
					baseDeRegle.retirerRegle(regleValide);
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
			return true;
		}
		else{
			trace+=" ECHEC";
			return false;
		}
	}

}
