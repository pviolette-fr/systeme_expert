package fr.univangers.vrpv.MoteurInference;

import java.io.*;

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

    public static void writeContentToFile(String filepath, String content) throws IOException{
        System.out.println(content);
        OutputStream out = new FileOutputStream(filepath);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

        writer.write(content);

        writer.close();
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

    public static void exporterBaseDeRegle(String filepath, BaseDeRegle bdr) throws IOException{
        writeContentToFile(filepath, bdr.toJSONString());
    }

    public static void exporterBaseDeFait(String filepath, BaseDeFait bdf) throws IOException{
        writeContentToFile(filepath, bdf.toJSONString());
    }

}
