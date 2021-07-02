package ehu.isad.controllers.db;

import ehu.isad.model.Repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class URLKud {

    private static URLKud instance = new URLKud();

    private URLKud() {

    }

    public static URLKud getInstance() {
        return instance;
    }

    public String getVersion(String digest) {
        String query = "select version from checksums where idCMS = 1 " +
                " and md5= '" + digest + "'";

        String version = "";
        ResultSet rs = DBKud.getInstantzia().execSQL(query);
        try {
            if (rs.next()) {
                version = rs.getString("version");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return version;
    }

    public void setVersion(Repo repo) {

        String insert = "insert into repos (full_name, description, license, open_issues) " +
                "values (  '%s', '%s', '%s', %d  ) " +
                "ON CONFLICT(full_name) DO UPDATE set open_issues = %d";

        insert = String.format(insert, repo.full_name, repo.description, repo.license, repo.open_issues, repo.open_issues);
        DBKud.getInstantzia().execSQL(insert);
    }

    public List<Repo> getRepos(String reponame) {

        String query = "select full_name, description, license, open_issues from repos";
        if (!reponame.equals("")) {
            query += " where full_name = '" + reponame + "'";
        }

        ArrayList<Repo> repos = new ArrayList<>();

        ResultSet rs = DBKud.getInstantzia().execSQL(query);
        try {
            while (rs.next()) {
                String full_name = rs.getString("full_name");
                String description = rs.getString("description");
                String license = rs.getString("license");
                int open_issues = rs.getInt("open_issues");
                repos.add(new Repo(full_name, license, description, open_issues));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return repos;
    }
}
