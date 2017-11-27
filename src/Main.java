import java.util.Scanner;

import MoteurInference.*;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
					
		BaseDeFait bdf = new BaseDeFait();
		bdf.add(new Fait("livre","1984"));
		
		
		
		
		Fait but = new Fait("livre","La ferme des animaux");
	
		
		System.out.println("Choix de la stratégie d'exploitation des règles : ");
		System.out.println("1: Chainage avant profondeur");
		System.out.println("2: Chainage avant largeur");
		System.out.println("3: Chaine arrière");
		System.out.println("4: Groupement par paquets");
		
		
		BaseDeRegle bdr = CreationBDR.creerBaseDeRegle("base_de_regle.json");
		
		Scanner scan = new Scanner(System.in);
		int reponse = scan.nextInt();
		scan.close();
		System.out.println(reponse);
		switch(reponse){
		case 1:
			Chainage cav = Chainage.getChainage(Chainage.CHAINAGE_AVANT_PROF, bdr.get(0), bdf);
			System.out.println(cav.rechercheBut(but));
			System.out.println(cav.getTrace());
			break;
		case 2:
			Chainage cavl = Chainage.getChainage(Chainage.CHAINAGE_AVANT_LARG, bdr.get(0), bdf);
			System.out.println(cavl.rechercheBut(but));
			System.out.println(cavl.getTrace());
			break;
		case 3:
			Chainage car = Chainage.getChainage(Chainage.CHAINAGE_ARRIERE, bdr.get(0), bdf);
			System.out.println(car.rechercheBut(but));
			System.out.println(car.getTrace());
			break;
		case 4:
			MoteurGroupementParPaquets m = new MoteurGroupementParPaquets(bdr, bdf, Chainage.CHAINAGE_AVANT_PROF);
			m.rechercherBut(but);
			System.out.println(m.getTrace());
			break;
		default:
			System.out.println("Erreur");
			break;
		}
		
		

	}

}
