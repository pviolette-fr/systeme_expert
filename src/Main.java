import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		
		
		ArrayList<Fait> bdf = new ArrayList<Fait>();
		bdf.add(new Fait("auteur","Boris_Vian"));
		bdf.add(new Fait("theme","amour"));
		bdf.add(new Fait("theme","vengeance"));
		bdf.add(new Fait("livre","1984"));
		
		
		
		Fait but = new Fait("livre","J_irai_cracher_sur_vos_tombes");
	
		
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
			Paquet bdr = CreationBDR.creerBaseDeRegle("base_de_regle.txt");
			ChainageAvant c = new ChainageAvant(bdr, bdf);
			System.out.println(c.rechercheBut(but));
			break;
		case 2:
			Paquet bdrr = CreationBDR.creerBaseDeRegle("base_de_regle.txt");
			ChainageArriere car = new ChainageArriere(bdrr, bdf);
			System.out.println(car.rechercheBut(but));
			break;
		case 3:
			ArrayList<Paquet> bdrp = CreationBDR.creerBaseDeRegleParPaquets("base_de_regle.txt");
			MoteurGroupementParPaquets m = new MoteurGroupementParPaquets(bdrp, bdf, 1);
			System.out.println(m.rechercherBut(but));
			break;
		default:
			System.out.println("Erreur");
			break;
		}
		
		
		
		
		
		
		

	}

}
