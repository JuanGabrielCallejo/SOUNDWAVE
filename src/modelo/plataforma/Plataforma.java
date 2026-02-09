package modelo.plataforma;

import enums.TipoSuscripcion;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.plataforma.ArtistaNoEncontradoException;
import excepciones.plataforma.UsuarioYaExisteException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioPremium;
import modelo.utilidades.RecomendadorIA;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Plataforma {

    private static Plataforma instancia;
    private String nombre;
    private HashMap<String, Usuario> usuarios;
    private HashMap<String, Usuario> usuariosPorEmail;
    private ArrayList<Contenido> catalogo;
    private ArrayList<Playlist> playlistPublicas;
    private HashMap<String, Artista> artistas;
    private HashMap<String, Creador> creadores;
    private ArrayList<Album> albumes;
    private ArrayList<Anuncio> anuncios;
    private RecomendadorIA recomendador;
    private int totalAnunciosReproducidos;


    private Plataforma(String nombre) {
        this.nombre = nombre;
    }



    public static void setInstancia(Plataforma instancia) {
        Plataforma.instancia = instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashMap<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public HashMap<String, Usuario> getUsuariosPorEmail() {
        return usuariosPorEmail;
    }

    public void setUsuariosPorEmail(HashMap<String, Usuario> usuariosPorEmail) {
        this.usuariosPorEmail = usuariosPorEmail;
    }

    public ArrayList<Contenido> getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(ArrayList<Contenido> catalogo) {
        this.catalogo = catalogo;
    }

    public ArrayList<Playlist> getPlaylistPublicas() {
        return playlistPublicas;
    }

    public void setPlaylistPublicas(ArrayList<Playlist> playlistPublicas) {
        this.playlistPublicas = playlistPublicas;
    }

    public HashMap<String, Artista> getArtistas() {
        return artistas;
    }

    public void setArtistas(HashMap<String, Artista> artistas) {
        this.artistas = artistas;
    }

    public HashMap<String, Creador> getCreadores() {
        return creadores;
    }

    public void setCreadores(HashMap<String, Creador> creadores) {
        this.creadores = creadores;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    public ArrayList<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(ArrayList<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }

    public RecomendadorIA getRecomendador() {
        return recomendador;
    }

    public void setRecomendador(RecomendadorIA recomendador) {
        this.recomendador = recomendador;
    }

    public int getTotalAnunciosReproducidos() {
        return totalAnunciosReproducidos;
    }

    public void setTotalAnunciosReproducidos(int totalAnunciosReproducidos) {
        this.totalAnunciosReproducidos = totalAnunciosReproducidos;
    }

    public static synchronized Plataforma getInstancia(String nombre){
        if (instancia == null) {
            instancia = new Plataforma(nombre);
        }
        return instancia;
    }

    public static synchronized Plataforma getInstancia(){
        if (instancia == null) {
            throw new IllegalStateException("La plataforma no ha sido inicializada. Llama a getInstancia(String nombre) primero.");
        }
        return instancia;
    }

    public static synchronized void reiniciarInstancia(){
        instancia = null;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password, TipoSuscripcion tipo) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException{
        return null;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException{
        return null;
    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException{
        return null;
    }

    public ArrayList<UsuarioPremium> getUsuariosPremium(){
        return null;
    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos() {
        return null;
    }

    public ArrayList<Usuario> getTodosLosUsuarios() {
        return null;
    }

    public Usuario buscarUsuarioPorEmail (String email){
        return null;
    }

    //GESTIÓN DE ARTISTAS

    public Artista registrarArtista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado, String nombreReal, String paisOrigen, ) {
        return null;
    }

    public void registrarArtista(Artista artista) {

    }

    public ArrayList<Artista> getArtistasVerificados(){
        return null;
    }

    public ArrayList<Artista> getArtistasNoVerificados() {
        return null;
    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException{
        return null;
    }

    // GESTIÓN DE ÁLBUMES

    public Album crearAlbum(Artista artista, String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException{
        return null;
    }





}










}
