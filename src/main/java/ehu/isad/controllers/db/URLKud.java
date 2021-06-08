package ehu.isad.controllers.db;

import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void setVersion(String md5, String version) {

        String insert = "insert into checksums (idCMS, version, md5, path) values (1, '"+version+"','"+md5+"','README')";
        DBKud.getInstantzia().execSQL(insert);
    }
}
