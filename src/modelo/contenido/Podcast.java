package modelo.contenido;

import enums.CategoriaPodcast;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.EpisodioNoEncontradoException;
import excepciones.contenido.TranscripcionNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Creador;

import java.util.ArrayList;

public class Podcast extends Contenido implements Reproducible, Descargable {

    private Creador creador;
    private int numeroEpisodio;
    private int temporada;
    private String descripcion;
    private CategoriaPodcast categoria;
    private ArrayList<String> invitados;
    private String transcripcion;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.invitados = new ArrayList<>();
    }

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria, String descripcion) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.invitados = new ArrayList<>();
    }

    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaPodcast getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;
    }

    public ArrayList<String> getInvitados() {
        return invitados;
    }

    public void setInvitados(ArrayList<String> invitados) {
        this.invitados = invitados;
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
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

    public String obtenerDescripcion() {
        return descripcion;
    }

    public void agregarInvitado(String nombre) {
        if (invitados == null) {
            invitados = new ArrayList<>();
        }
        if (!invitados.contains(nombre)) {
            invitados.add(nombre);
        }
    }

    public boolean esTemporadaNueva() {
        return numeroEpisodio == 1;
    }

    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException {
        if (transcripcion == null || transcripcion.isEmpty()) {
            throw new TranscripcionNoDisponibleException("No se ha podido recuperar la transcripción");
        }
        return transcripcion;
    }

    public void validarEpisodio() throws EpisodioNoEncontradoException {
        if (numeroEpisodio <= 0) {
            throw new EpisodioNoEncontradoException("Número de episodio inválido");
        }
    }

    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (!isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible");
        }
        this.reproduciendo = true;
        aumentarReproducciones();
    }

    @Override
    public boolean descargar() throws ContenidoYaDescargadoException {
        if (descargado) {
            throw new ContenidoYaDescargadoException("El podcast ya está descargado");
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
        return getDuracionSegundos() / 60 + 1;
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
    public String toString() {
        return "Podcast{titulo='" + getTitulo() + "', creador=" + (creador != null ? creador.getNombreCanal() : "N/A") + ", temporada=" + temporada + ", episodio=" + numeroEpisodio + "}";
    }
}
