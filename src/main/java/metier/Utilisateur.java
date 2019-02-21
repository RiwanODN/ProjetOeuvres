package metier;

public class Utilisateur {
    private Integer numUtil;
    private String nomUtil;
    private String motPasse;
    private String role;

    public Utilisateur(Integer numUtil, String nomUtil, String motPasse, String role) {
        this.numUtil = numUtil;
        this.nomUtil = nomUtil;
        this.motPasse = motPasse;
        this.role = role;
    }

    public Utilisateur() {

    }

    public Integer getNumUtil() {
        return numUtil;
    }

    public void setNumUtil(Integer numUtil) {
        this.numUtil = numUtil;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getMotPasse() {
        return motPasse;
    }

    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
