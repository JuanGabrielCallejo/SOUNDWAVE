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
import java.util.UUID;

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
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
        this.misPlaylists = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.fechaRegistro = new Date();
        this.playlistSeguidas = new ArrayList<>();
        this.contenidosLiked = new ArrayList<>();
    }

    public Usuario() {
        this.id = UUID.randomUUID().toString();
        this.misPlaylists = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.fechaRegistro = new Date();
        this.playlistSeguidas = new ArrayList<>();
        this.contenidosLiked = new ArrayList<>();
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

    public ArrayList<Playlist> getPlaylistsSeguidas() {
        return playlistSeguidas;
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

    public abstract void reproducir(Contenido contenido) throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException;

    public Playlist crearPlaylist(String nombrePlaylist) {
        Playlist playlist = new Playlist(nombrePlaylist, this);
        playlist.setId(UUID.randomUUID().toString());
        playlist.setContenidos(new ArrayList<>());
        playlist.setFechaCreacion(new Date());
        misPlaylists.add(playlist);
        return playlist;
    }

    public void seguirPlaylist(Playlist playlist) {
        if (!playlistSeguidas.contains(playlist)) {
            playlistSeguidas.add(playlist);
            playlist.incrementarSeguidores();
        }
    }

    public void dejarDeSeguirPlaylist(Playlist playlist) {
        if (playlistSeguidas.remove(playlist)) {
            playlist.decrementarSeguidores();
        }
    }

    public void darLike(Contenido contenido) {
        if (!contenidosLiked.contains(contenido)) {
            contenidosLiked.add(contenido);
            contenido.agregarLike();
        }
    }

    public void quitarLike(Contenido contenido) {
        contenidosLiked.remove(contenido);
    }

    public boolean validarEmail() throws EmailInvalidoException {
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new EmailInvalidoException("Email inválido");
        }
        return true;
    }

    public boolean validarPassword() throws PasswordDebilException {
        if (password == null || password.length() < 8) {
            throw new PasswordDebilException("Password débil");
        }
        return true;
    }

    public void agregarAlHistorial(Contenido contenido) {
        if (!historial.contains(contenido)) {
            historial.add(contenido);
        }
    }

    public void limpiarHistorial() {
        historial.clear();
    }

    public boolean esPremium() {
        return suscripcion != TipoSuscripcion.GRATUITO;
    }

    @Override
    public String toString() {
        return "Usuario{nombre='" + nombre + "', email='" + email + "', suscripcion=" + suscripcion + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Usuario usuario = (Usuario) obj;
        return id != null && id.equals(usuario.id);
    }
}
