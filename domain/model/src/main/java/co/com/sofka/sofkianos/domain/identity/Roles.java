package co.com.sofka.sofkianos.domain.identity;

public enum Roles {
    INVITED ("invited"),
    COLLABORATOR ("collaborator"),
    ADMIN("admin"),
    DIRECTOR("director");


    private String rol;

    Roles(String rolRol) {
        this.rol = rolRol;
    }

    public String getRol() {
        return rol;
    }
}
