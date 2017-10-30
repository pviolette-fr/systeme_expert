import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CreationBDR {

	public static ArrayList<Regle> creerBaseDeRegle(String nomFichier){
		ArrayList<Regle> baseDeRegles = new ArrayList<Regle>();
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
			
				baseDeRegles.add(r);
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
