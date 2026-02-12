package modelo.contenido;

import enums.GeneroMusical;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.LetraNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Album;
import modelo.artistas.Artista;

import java.util.ArrayList;
import java.util.Date;

public class Cancion extends Contenido implements Reproducible, Descargable {

    private String letra;
    private Artista artista;
    private Album album;
    private GeneroMusical genero;
    private String audioURL;
    private boolean explicit;
    private String ISRC;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;
        this.ISRC = generarISRC();
    }

    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero, String letra, boolean explicit) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;
        this.letra = letra;
        this.explicit = explicit;
        this.ISRC = generarISRC();
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }

    public void setReproduciendo(boolean reproduciendo) {
        this.reproduciendo = reproduciendo;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public boolean isDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    private String generarISRC() {
        return "ISRC" + System.currentTimeMillis() % 1000000000;
    }

    public String obtenerLetra() throws LetraNoDisponibleException {
        if (letra == null || letra.isEmpty()) {
            throw new LetraNoDisponibleException("La letra no está disponible");
        }
        return letra;
    }

    public boolean esExplicit() {
        return explicit;
    }

    public void cambiarGenero(GeneroMusical generoMusical) {
        this.genero = generoMusical;
    }

    public void validarAudioURL() throws ArchivoAudioNoEncontradoException {
        if (audioURL == null || audioURL.isEmpty()) {
            throw new ArchivoAudioNoEncontradoException("No se ha encontrado la url de la canción");
        }
    }

    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (!isDisponible()) {
            throw new ContenidoNoDisponibleException("La canción no está disponible para reproducir.");
        }
        this.reproduciendo = true;
        aumentarReproducciones();
    }

    @Override
    public void play() {
        this.reproduciendo = true;
        this.pausado = false;
    }

    @Override
    public void pause() {
        this.reproduciendo = false;
        this.pausado = true;
    }

    @Override
    public void stop() {
        this.reproduciendo = false;
        this.pausado = false;
    }

    @Override
    public int getDuracion() {
        return getDuracionSegundos();
    }

    @Override
    public boolean descargar() throws ContenidoYaDescargadoException {
        if (descargado) {
            throw new ContenidoYaDescargadoException("La canción ya está descargada");
        }
        this.descargado = true;
        return true;
    }

    @Override
    public boolean eliminarDescarga() {
        if (descargado) {
            this.descargado = false;
            return true;
        }
        return false;
    }

    @Override
    public int espacioRequerido() {
        // Estimación: 1MB por minuto de audio
        return getDuracionSegundos() / 60 + 1;
    }

    @Override
    public String toString() {
        return "Cancion{titulo='" + getTitulo() + "', artista=" + (artista != null ? artista.getNombreArtistico() : "N/A") + ", genero=" + genero + "}";
    }
}
