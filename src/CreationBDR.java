import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import MoteurInference.BaseDeRegle;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author Paulin VIOLETTE
 */
public class CreationBDR {
	
	public static BaseDeRegle creerBaseDeRegle(String file){
		
		try {
			String json = "";
			InputStream in = new FileInputStream(file);
		    BufferedReader reader =
		      new BufferedReader(new InputStreamReader(in));
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	json += line;
		    }
		    

			try {
			    JSONParser parser = new JSONParser();
			    JSONObject json_bdr;
				json_bdr = (JSONObject) parser.parse(json);
			    return BaseDeRegle.parseJSON(json_bdr);

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException x) {
		    System.err.println(x);
		}
		
		return null;
	}

}
