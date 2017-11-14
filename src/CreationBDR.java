import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CreationBDR {

	public static Paquet creerBaseDeRegle(String nomFichier){
		Paquet baseDeRegles = new Paquet();
		try {
			InputStream ips = new FileInputStream(nomFichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			
			
			while((ligne=br.readLine())!=null){
				
				
				Regle r = new Regle();
				String []tab = ligne.split("ALORS ");
			
				String [] SIS = tab[0].split(" ");
				for(int i=1;i<SIS.length;i++){
					String [] SI = SIS[i].split("=");
					r.addPremisse(SI[0], SI[1]);
				}
				String [] ALORS = tab[1].split("=");
			
				r.setConclusion(ALORS[0], ALORS[1]);
			
				baseDeRegles.ajouterRegle(r);
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return baseDeRegles;
	}
	
	public static ArrayList<Paquet> creerBaseDeRegleParPaquets(String nomFichier){
		ArrayList<Paquet> baseDeRegles = new ArrayList<Paquet>();
		try {
			InputStream ips = new FileInputStream(nomFichier);
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			String ligne;
			
			ArrayList<Regle> regles = new ArrayList<Regle>();
			int i=0;
			while((ligne=br.readLine())!=null){
				
				if(i!=5){
					Regle r = new Regle();
					String []tab = ligne.split("ALORS ");
				
					String [] SIS = tab[0].split(" ");
					for(int j=1;i<SIS.length;i++){
						String [] SI = SIS[j].split("=");
						r.addPremisse(SI[0], SI[1]);
					}
					String [] ALORS = tab[1].split("=");
				
					r.setConclusion(ALORS[0], ALORS[1]);
					regles.add(r);
					i++;
				}
				else{
					
					Paquet p = new Paquet(regles);
					
					regles.clear();
					
					baseDeRegles.add(p);
					i=0;
					
				}
				
				
			
				
			}
			br.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return baseDeRegles;
	}

}
