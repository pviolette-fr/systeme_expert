import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
					
		BaseDeFait bdf = new BaseDeFait();
		bdf.add(new Fait("auteur","Boris_Vian"));
		bdf.add(new Fait("theme","amour"));
		bdf.add(new Fait("theme","vengeance"));
		bdf.add(new Fait("livre","1984"));
		
		
		
		Fait but = new Fait("livre","J_irai_cracher_sur_vos_tombes");
	
		
		System.out.println("Choix de la stratégie d'exploitation des règles : ");
		System.out.println("1: Chainage avant");
		System.out.println("2: Chaine arrière");
		System.out.println("3: Groupement par paquets");
		
		
		BaseDeRegle bdr = CreationBDR.creerBaseDeRegle("base_de_regle.json");
		
		Scanner scan = new Scanner(System.in);
		int reponse = scan.nextInt();
		scan.close();
		System.out.println(reponse);
		switch(reponse){
		case 1:
			Chainage cav = Chainage.getChainage(Chainage.CHAINAGE_AVANT, bdr.get(0), bdf);
			System.out.println(cav.rechercheBut(but));
			break;
		case 2:
			Chainage car = Chainage.getChainage(Chainage.CHAINAGE_ARRIERE, bdr.get(0), bdf);
			System.out.println(car.rechercheBut(but));
			break;
		case 3:
			MoteurGroupementParPaquets m = new MoteurGroupementParPaquets(bdr, bdf, 1);
			System.out.println(m.rechercherBut(but));
			break;
		default:
			System.out.println("Erreur");
			break;
		}
		
		

	}

}
