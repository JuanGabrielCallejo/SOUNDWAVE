package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;

import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    private boolean descargasOfline;
    private int maxDescargas;
    private ArrayList<Contenido> dscargardos;
    private String calidadAudio;


    public boolean isDescargasOfline() {
        return descargasOfline;
    }

    public void setDescargasOfline(boolean descargasOfline) {
        this.descargasOfline = descargasOfline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    public void setMaxDescargas(int maxDescargas) {
        this.maxDescargas = maxDescargas;
    }

    public ArrayList<Contenido> getDscargardos() {
        return dscargardos;
    }

    public void setDscargardos(ArrayList<Contenido> dscargardos) {
        this.dscargardos = dscargardos;
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    private static final int MAX_DESCARGAS_DEFAULT = 1000;

    public UsuarioPremium(String nombre, String email, String password) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, TipoSuscripcion.PREMIUM);
    }

    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {

    }


    public void descargar(Contenido contenido) throws LimiteDescargasException, ContenidoYaDescargadoException{}

    public boolean eliminarDescarga(Contenido contenido){
        return false;
    }

    public boolean verificarEspacioDescarga(){
        return false;
    }

    public int geDescargasRestantes(){
        return 0;
    }

    public void cambiarCalidadAudio(String calidad){}

    public void limpiarDescargas(){}



    @Override
    public String toString() {
        return super.toString();
    }

}
