package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.model.Repo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Utils {
    public static Properties lortuEzarpenak()  {
        Properties properties = null;

        try (InputStream in = Utils.class.getResourceAsStream("/setup.properties")) {
            properties = new Properties();
            properties.load(in);

        } catch (
                IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

    public static Repo readFromUrl(String repository) throws IOException {

        String inputLine;

        URL coinmarket = new URL("https://api.github.com/repos/"
                + repository );
        URLConnection yc = coinmarket.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        inputLine = in.readLine();
        in.close();

        Gson gson = new Gson();
        return gson.fromJson(inputLine, Repo.class);

    }


    public static void main(String[] args) {
        try {
           Repo repo = Utils.readFromUrl("darkreader/darkreader");
            System.out.println(repo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
