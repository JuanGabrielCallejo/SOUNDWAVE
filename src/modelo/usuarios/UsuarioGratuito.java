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
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;
    private int cancionesSinAnuncio;
    private Date fechaUltimaReproduccion;

    private static final int LIMITE_DIARIO = 50;
    private static final int CANCIONES_ENTRE_ANUNCIOS = 3;

    public UsuarioGratuito(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.anunciosEscuchados = 0;
        this.reproduccionesHoy = 0;
        this.cancionesSinAnuncio = 0;
        this.limiteReproducciones = LIMITE_DIARIO;
    }

    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public void setAnunciosEscuchados(int anunciosEscuchados) {
        this.anunciosEscuchados = anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public void setUltimoAnuncio(Date ultimoAnuncio) {
        this.ultimoAnuncio = ultimoAnuncio;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public void setLimiteReproducciones(int limiteReproducciones) {
        this.limiteReproducciones = limiteReproducciones;
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

    public void verAnuncio() {
        this.anunciosEscuchados++;
        this.cancionesSinAnuncio = 0;
        this.ultimoAnuncio = new Date();
    }

    public void verAnuncio(Anuncio anuncio) {
        this.anunciosEscuchados++;
        this.cancionesSinAnuncio = 0;
        this.ultimoAnuncio = new Date();
    }

    public boolean puedeReproducir() {
        return reproduccionesHoy < LIMITE_DIARIO;
    }

    public boolean debeVerAnuncio() {
        return cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS;
    }

    public void reiniciarContadorDiario() {
        this.reproduccionesHoy = 0;
        this.cancionesSinAnuncio = 0;
    }

    public int getReproduccionesRestantes() {
        return LIMITE_DIARIO - reproduccionesHoy;
    }

    public int getCancionesHastaAnuncio() {
        return CANCIONES_ENTRE_ANUNCIOS - cancionesSinAnuncio;
    }

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {
        if (!contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible");
        }

        if (!puedeReproducir()) {
            throw new LimiteDiarioAlcanzadoException("Has alcanzado el límite diario de reproducciones");
        }

        if (debeVerAnuncio()) {
            throw new AnuncioRequeridoException("Debes ver un anuncio antes de continuar");
        }

        contenido.aumentarReproducciones();
        reproduccionesHoy++;
        cancionesSinAnuncio++;
        fechaUltimaReproduccion = new Date();
        agregarAlHistorial(contenido);
    }

    @Override
    public String toString() {
        return "UsuarioGratuito{nombre='" + nombre + "', email='" + email + "', reproduccionesHoy=" + reproduccionesHoy + "}";
    }
}
