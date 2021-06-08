package ehu.isad.model;

import javafx.scene.control.Hyperlink;

public class ServerCMSModel {

    private String url;
    private String md5;
    private String version;

    public ServerCMSModel(String url, String md5, String version){
        this.url = url;
        this.md5 = md5;
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public String getMd5() {
        return md5;
    }

    public String getVersion() { return version; }

    public void setVersion(String version) {
        this.version = version;
    }
}
