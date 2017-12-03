package fr.univangers.vrpv;

import java.util.Scanner;

import fr.univangers.vrpv.MoteurInference.*;
import fr.univangers.vrpv.MoteurInference.MoteurInferenceIO;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
					
		BaseDeFait bdf = new BaseDeFait();
		bdf.add(new Fait("epoque","20"));
				
		
		Fait but = new Fait("livre","11/22/63");
			
		
		System.out.println("Choix de la stratégie d'exploitation des règles : ");
		System.out.println("1: Chainage avant profondeur");
		System.out.println("2: Chainage avant largeur");
		System.out.println("3: Chaine arrière");
		System.out.println("4: Groupement par paquets");
		
		
		BaseDeRegle bdr = MoteurInferenceIO.creerBaseDeRegle("base_de_regle.json");

		System.out.println(bdr);
		
		System.out.println(bdr.getCoherence());


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
			System.out.println(m.getTrace());
			break;
		default:
			System.out.println("Erreur");
			break;
		}
	}

}
