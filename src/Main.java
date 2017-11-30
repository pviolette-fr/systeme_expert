import java.util.Map.Entry;
import java.util.Scanner;

import MoteurInference.*;
import Utilities.CreationBDR;

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

		System.out.println(bdr);

		System.out.println(Coherence.estCoherent(bdr));
		Scanner scan = new Scanner(System.in);
		int reponse = scan.nextInt();
		scan.close();
		System.out.println(reponse);
		switch(reponse){
		case 1:
			Chainage cav = Chainage.getChainage(ModeChainage.AVANT_PROFONDEUR, bdr.get(0), bdf);
			System.out.println(cav.rechercheBut(but));
			System.out.println(cav.getTrace());
			break;
		case 2:
			Chainage cavl = Chainage.getChainage(ModeChainage.AVANT_LARGEUR, bdr.get(0), bdf);
			System.out.println(cavl.rechercheBut(but));
			System.out.println(cavl.getTrace());
			break;
		case 3:
			Chainage car = Chainage.getChainage(ModeChainage.ARRIERE, bdr.get(0), bdf);
			System.out.println(car.rechercheBut(but));
			System.out.println(car.getTrace());
			break;
		case 4:
			MoteurGroupementParPaquets m = new MoteurGroupementParPaquets(bdr, bdf, ModeChainage.DEFAULT);
			m.rechercherBut(but);
			System.out.println(m.getTraceAbregee());
			break;
		default:
			System.out.println("Erreur");
			break;
		}
	}

}
