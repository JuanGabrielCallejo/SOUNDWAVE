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
    }

    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero, String letra, boolean explicit) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;
        this.letra = letra;
        this.explicit = explicit;
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

    //Metodo privado para dar ISRC

    private String generarISRC() {
        // Lógica para generar un código ISRC único
        return "ISRC" + System.currentTimeMillis() % 1000000000;
    }

    //Metodos propios publicos

    public String obtenerLetra() throws LetraNoDisponibleException{};

    public boolean esExplicit(){
        if(esExplicit()){
            return true;
        }else{
            return false;
        }
    };

    public void cambiarGenero (GeneroMusical generomusical){

    }

    public void validarAudioURL() throws ArchivoAudioNoEncontradoException{
        if(audioURL == null) {
            throw new ArchivoAudioNoEncontradoException("No se ha encontrado la url de la canción");
        }

    }







    //OVERRIDES

    //overrride que viene de contenido

    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (!isDisponible()){
            throw new ContenidoNoDisponibleException("La cancion no esta disponible para reproducir.");
        } else {
            this.reproduciendo = true;
            //me queda llamar a aumentar reproducciones;

        }
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int getDuracion() {
        return 0;
    }

    @Override
    public boolean descargar() throws  ContenidoYaDescargadoException {
        return false;
    }

    @Override
    public boolean eliminarDescarga() {
        return false;
    }

    @Override
    public int espacioRequerido() {
        return 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
