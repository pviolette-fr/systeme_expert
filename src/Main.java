import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Regle> bdr = CreationBDR.creerBaseDeRegle("base_de_regle.txt");
		
		
		ArrayList<Fait> bdf = new ArrayList<Fait>();
		bdf.add(new Fait("auteur","Boris_Vian"));
		bdf.add(new Fait("theme","amour"));
		bdf.add(new Fait("theme","vengeance"));
		bdf.add(new Fait("livre","1984"));
		
		
		
		Fait but = new Fait("livre","J_irai_cracher_sur_vos_tombes");
		
		
		Moteur m = new Moteur(bdr, bdf);
		
		System.out.println("Choix de la stratégie d'exploitation des règles : ");
		System.out.println("1: Chainage avant");
		System.out.println("2: Chaine arrière");
		System.out.println("3: Groupement par paquets");
		
		Scanner scan = new Scanner(System.in);
		int reponse = scan.nextInt();
		scan.close();
		System.out.println(reponse);
		switch(reponse){
		case 1:
			System.out.println(m.chainageAvant(but));
			break;
		case 2:
			System.out.println(m.chainageArriere(but));
			break;
		case 3:
			break;
		default:
			System.out.println("Erreur");
			break;
		}
		
		
		
		
		
		
		

	}

}
