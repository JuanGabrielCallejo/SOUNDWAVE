package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Playlist;

import java.util.ArrayList;
import java.util.Date;

public abstract class Usuario {

    protected String id;
    protected String nombre;
    protected String email;
    protected String password;
    protected TipoSuscripcion suscripcion;
    protected ArrayList<Playlist> misPlaylists;
    protected ArrayList<Contenido> historial;
    protected Date fechaRegistro;
    protected ArrayList<Playlist> playlistSeguidas;
    protected ArrayList<Contenido> contenidosLiked;

    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {

        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
    }

    //getters y setters


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public ArrayList<Playlist> getMisPlaylists() {
        return misPlaylists;
    }

    public void setMisPlaylists(ArrayList<Playlist> misPlaylists) {
        this.misPlaylists = misPlaylists;
    }

    public ArrayList<Contenido> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<Contenido> historial) {
        this.historial = historial;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ArrayList<Playlist> getPlaylistSeguidas() {
        return playlistSeguidas;
    }

    public void setPlaylistSeguidas(ArrayList<Playlist> playlistSeguidas) {
        this.playlistSeguidas = playlistSeguidas;
    }

    public ArrayList<Contenido> getContenidosLiked() {
        return contenidosLiked;
    }

    public void setContenidosLiked(ArrayList<Contenido> contenidosLiked) {
        this.contenidosLiked = contenidosLiked;
    }

    public abstract void reproducir (Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException;


    public Playlist crearPlaylist(String nombrePlaylist){
        return null;
    }

    public void  seguirPlaylist(Playlist playlist){
    }

    public void dejarDeSeguirPlaylist(Playlist playlist){
    }

    public void darLike(Contenido contenido){

    }

    public void quitarLike(Contenido contenido){

    }

    public boolean validarEmail() throws EmailInvalidoException {
        return false;
    }

    public boolean validarPassword() throws PasswordDebilException {
            return false;
    }

    public void agregarAlHistorial(Contenido contenido){}

    public void limpiarHistorial(){}

    public boolean esPremium(){
        return false;
    }

    public Usuario() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}















