package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Anuncio;

import java.util.Date;

public class UsuarioGratuito extends Usuario {

    private int anunciosEscuchados;
    private Date ultimoAnuncion;
    private int reproduccionesHoy;
    private int limiteReproduccions;
    private int cancionesSinAnuncio;
    private Date fechaUltimaReproduccion;

    private static final int LIMITE_DIARIO =50;
    private static final int CANCIONES_ENTRE_ANUNCIOS =3;

    public UsuarioGratuito(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
    }

    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public void setAnunciosEscuchados(int anunciosEscuchados) {
        this.anunciosEscuchados = anunciosEscuchados;
    }

    public Date getUltimoAnuncion() {
        return ultimoAnuncion;
    }

    public void setUltimoAnuncion(Date ultimoAnuncion) {
        this.ultimoAnuncion = ultimoAnuncion;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproduccions() {
        return limiteReproduccions;
    }

    public void setLimiteReproduccions(int limiteReproduccions) {
        this.limiteReproduccions = limiteReproduccions;
    }

    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    public Date getFechaUltimaReproduccion() {
        return fechaUltimaReproduccion;
    }

    public void setFechaUltimaReproduccion(Date fechaUltimaReproduccion) {
        this.fechaUltimaReproduccion = fechaUltimaReproduccion;
    }

    public void verAnuncio(){
        this.anunciosEscuchados++;
        this.cancionesSinAnuncio = 0;
        this.ultimoAnuncion = new Date();
    }

    public void verAnuncio (Anuncio anuncio){}

    public boolean puedeReproducir(){
        if (reproduccionesHoy >= LIMITE_DIARIO) {
            return false;
        }
        if (cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS) {
            return false;
        }
        return true;
    }

    public boolean debeVerAnuncio(){
        if (cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS) {
            return true;
        }
        return false;
    }

    public void reiniciarContadorDiario(){
        this.reproduccionesHoy = 0;
    }

    public int getReproduccionesRestaten(){
        return LIMITE_DIARIO - reproduccionesHoy;
    }

    public int getCancionesHastaAnuncio(){
        return CANCIONES_ENTRE_ANUNCIOS - cancionesSinAnuncio;
    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
