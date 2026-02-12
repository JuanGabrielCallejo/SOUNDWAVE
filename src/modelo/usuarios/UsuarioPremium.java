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

    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;

    private static final int MAX_DESCARGAS_DEFAULT = 1000;

    public UsuarioPremium(String nombre, String email, String password) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, TipoSuscripcion.PREMIUM);
        this.descargasOffline = true;
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();
        this.calidadAudio = "alta";
    }

    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.descargasOffline = true;
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();
        this.calidadAudio = "alta";
    }

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    public void setMaxDescargas(int maxDescargas) {
        this.maxDescargas = maxDescargas;
    }

    public ArrayList<Contenido> getDescargados() {
        return descargados;
    }

    public void setDescargados(ArrayList<Contenido> descargados) {
        this.descargados = descargados;
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    public int getNumDescargados() {
        return descargados.size();
    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {
        if (!contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible");
        }

        contenido.aumentarReproducciones();
        agregarAlHistorial(contenido);
    }

    public void descargar(Contenido contenido) throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (descargados.contains(contenido)) {
            throw new ContenidoYaDescargadoException("El contenido ya está descargado");
        }

        if (descargados.size() >= maxDescargas) {
            throw new LimiteDescargasException("Has alcanzado el límite de descargas");
        }

        descargados.add(contenido);
    }

    public boolean eliminarDescarga(Contenido contenido) {
        return descargados.remove(contenido);
    }

    public boolean verificarEspacioDescarga() {
        return descargados.size() < maxDescargas;
    }

    public int getDescargasRestantes() {
        return maxDescargas - descargados.size();
    }

    public void cambiarCalidadAudio(String calidad) {
        this.calidadAudio = calidad;
    }

    public void limpiarDescargas() {
        descargados.clear();
    }

    @Override
    public String toString() {
        return "UsuarioPremium{nombre='" + nombre + "', email='" + email + "', suscripcion=" + suscripcion + "}";
    }
}
