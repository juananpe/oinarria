package ehu.isad.model;

public class Repo {

    public Repo(String full_name, String license, String description,  int open_issues) {
        this.full_name = full_name;
        this.description = description;
        this.open_issues = open_issues;
        this.license = new License(license, license);
    }

    public class License {
        public License(String name, String spdx_id) {
            this.name = name;
            this.spdx_id = spdx_id;
        }

        public String toString(){
            return name;
        }

        public String name;
        public String spdx_id;

    }

    public String full_name;
    public String description;
    public int open_issues;
    public License license;

    @Override
    public String toString() {
        return "Repo{" +
                "fullname='" + full_name + '\'' +
                ", description='" + description + '\'' +
                ", open_issues=" + open_issues + ' ' + license.toString() +
                '}';
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }
}
