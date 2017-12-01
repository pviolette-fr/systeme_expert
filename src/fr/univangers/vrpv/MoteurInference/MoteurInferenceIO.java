package fr.univangers.vrpv.MoteurInference;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 * @author Paulin VIOLETTE
 */
public class MoteurInferenceIO {

    public static String getFileContent(String filepath) throws IOException {
        String content = "";
        InputStream in = new FileInputStream(filepath);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ((line = reader.readLine()) != null) {
            content += line;
        }
        return content;
    }

    public static BaseDeRegle creerBaseDeRegle(String file) {

        try {
            String json = getFileContent(file);

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

    public static BaseDeFait importerBaseDeFait(String filepath) throws IOException {
        String json = getFileContent(filepath);
        try {
            JSONParser parser = new JSONParser();
            JSONObject json_bdf = (JSONObject) parser.parse(json);
            return BaseDeFait.parseJSON(json_bdf);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
