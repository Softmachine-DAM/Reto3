public class PrestamosPendientes {
    //Atributos
    private int id_prestamo1;
    private int id_prestamo2;
    private int id_prestamo3;
    private int id_libro1;
    private int id_libro2;
    private int id_libro3;
    //Constructor
    public PrestamosPendientes(int pIdPrestamo1, int pIdPrestamo2, int pIdPrestamo3, int pIdLibro1, int pIdLibro2, int pIdLibro3){
        this.id_prestamo1 = pIdPrestamo1;
        this.id_prestamo2 = pIdPrestamo2;
        this.id_prestamo3 = pIdPrestamo3;
        this.id_libro1 = pIdLibro1;
        this.id_libro2 = pIdLibro2;
        this.id_libro3 = pIdLibro3;
    }
    //Metodos
    public int getId1() {
        return id_prestamo1;
    }
    public void setId1(int pId1) {
        this.id_prestamo1 = pId1;
    }
    public int getId2() {
        return id_prestamo2;
    }
    public void setId2(int pId2) {
        this.id_prestamo2 = pId2;
    }
    public int getId3() {
        return id_prestamo3;
    }
    public void setId3(int pId3) {
        this.id_prestamo3 = pId3;
    }
    public int getId_libro1() {
        return id_libro1;
    }
    public void setId_libro1(int id_libro1) {
        this.id_libro1 = id_libro1;
    }
    public int getId_libro2() {
        return id_libro2;
    }
    public void setId_libro2(int id_libro2) {
        this.id_libro2 = id_libro2;
    }
    public int getId_libro3() {
        return id_libro3;
    }
    public void setId_libro3(int id_libro3) {
        this.id_libro3 = id_libro3;
    }
    @Override
    public String toString() {
        return "PrestamosPendientes [id_prestamo1=" + id_prestamo1 + ", id_prestamo2=" + id_prestamo2
                + ", id_prestamo3=" + id_prestamo3 + ", id_libro1=" + id_libro1 + ", id_libro2=" + id_libro2
                + ", id_libro3=" + id_libro3 + "]";
    } 
}
