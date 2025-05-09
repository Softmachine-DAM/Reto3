public class SesionActiva {
    //Atributos
    private int id;
    private String contraseña;
    //Constructor
    public SesionActiva(int pId, String pContraseña){
        this.id = pId;
        this.contraseña = pContraseña;
    }
    public int getId() {
        return id;
    }
    public void setId(int pId) {
        this.id = pId;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String pContraseña) {
        this.contraseña = pContraseña;
    }
    @Override
    public String toString() {
        return "SesionActiva [id=" + id + ", contraseña=" + contraseña + "]";
    }
}
