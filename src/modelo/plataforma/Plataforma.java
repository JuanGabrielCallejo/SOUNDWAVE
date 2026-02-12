package modelo.plataforma;

import enums.CategoriaPodcast;
import enums.GeneroMusical;
import enums.TipoAnuncio;
import enums.TipoSuscripcion;
import excepciones.artista.AlbumCompletoException;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.plataforma.ArtistaNoEncontradoException;
import excepciones.plataforma.ContenidoNoEncontradoException;
import excepciones.plataforma.UsuarioYaExisteException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioGratuito;
import modelo.usuarios.UsuarioPremium;
import utilidades.RecomendadorIA;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

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
        this.usuarios = new HashMap<>();
        this.usuariosPorEmail = new HashMap<>();
        this.catalogo = new ArrayList<>();
        this.playlistPublicas = new ArrayList<>();
        this.artistas = new HashMap<>();
        this.creadores = new HashMap<>();
        this.albumes = new ArrayList<>();
        this.anuncios = new ArrayList<>();
        this.recomendador = new RecomendadorIA();
        this.totalAnunciosReproducidos = 0;

        // Crear algunos anuncios por defecto
        anuncios.add(new Anuncio("Spotify", TipoAnuncio.AUDIO, 100));
        anuncios.add(new Anuncio("Apple", TipoAnuncio.AUDIO, 200));
        anuncios.add(new Anuncio("Google", TipoAnuncio.AUDIO, 150));
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

    public void setPlaylistPublicas(ArrayList<Playlist> playlistPublicas) {
        this.playlistPublicas = playlistPublicas;
    }

    public HashMap<String, Artista> getArtistasMap() {
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

    private void validarEmail(String email) throws EmailInvalidoException {
        if (email == null || email.isEmpty()) {
            throw new EmailInvalidoException("Email vacío");
        }
        if (!email.contains("@")) {
            throw new EmailInvalidoException("Email sin @");
        }
        int atIndex = email.indexOf("@");
        if (atIndex == email.length() - 1) {
            throw new EmailInvalidoException("Email inválido");
        }
    }

    private void validarPassword(String password) throws PasswordDebilException {
        if (password == null || password.isEmpty()) {
            throw new PasswordDebilException("Password vacío");
        }
        if (password.length() < 8) {
            throw new PasswordDebilException("Password muy corto");
        }
    }

    private void verificarEmailDuplicado(String email) throws UsuarioYaExisteException {
        if (usuariosPorEmail.containsKey(email.toLowerCase())) {
            throw new UsuarioYaExisteException("Ya existe un usuario con ese email");
        }
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password, TipoSuscripcion tipo) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        validarEmail(email);
        validarPassword(password);
        verificarEmailDuplicado(email);

        UsuarioPremium usuario = new UsuarioPremium(nombre, email, password, tipo);
        usuario.setId(UUID.randomUUID().toString());
        usuarios.put(usuario.getId(), usuario);
        usuariosPorEmail.put(email.toLowerCase(), usuario);
        return usuario;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return registrarUsuarioPremium(nombre, email, password, TipoSuscripcion.PREMIUM);
    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password) throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        validarEmail(email);
        validarPassword(password);
        verificarEmailDuplicado(email);

        UsuarioGratuito usuario = new UsuarioGratuito(nombre, email, password, TipoSuscripcion.GRATUITO);
        usuario.setId(UUID.randomUUID().toString());
        usuarios.put(usuario.getId(), usuario);
        usuariosPorEmail.put(email.toLowerCase(), usuario);
        return usuario;
    }

    public ArrayList<UsuarioPremium> getUsuariosPremium() {
        ArrayList<UsuarioPremium> premiums = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            if (u instanceof UsuarioPremium) {
                premiums.add((UsuarioPremium) u);
            }
        }
        return premiums;
    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos() {
        ArrayList<UsuarioGratuito> gratuitos = new ArrayList<>();
        for (Usuario u : usuarios.values()) {
            if (u instanceof UsuarioGratuito) {
                gratuitos.add((UsuarioGratuito) u);
            }
        }
        return gratuitos;
    }

    public ArrayList<Usuario> getTodosLosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuariosPorEmail.get(email.toLowerCase());
    }

    //GESTIÓN DE ARTISTAS

    public Artista registrarArtista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado) {
        Artista artista = new Artista(nombreArtistico, nombreReal, paisOrigen);
        artista.setVerificado(verificado);
        artista.setId(UUID.randomUUID().toString());
        artista.setAlbumes(new ArrayList<>());
        artista.setDiscografia(new ArrayList<>());
        artistas.put(artista.getId(), artista);
        return artista;
    }

    public void registrarArtista(Artista artista) {
        if (artista.getId() == null) {
            artista.setId(UUID.randomUUID().toString());
        }
        artistas.put(artista.getId(), artista);
    }

    public ArrayList<Artista> getArtistasVerificados() {
        ArrayList<Artista> verificados = new ArrayList<>();
        for (Artista a : artistas.values()) {
            if (a.isVerificado()) {
                verificados.add(a);
            }
        }
        return verificados;
    }

    public ArrayList<Artista> getArtistasNoVerificados() {
        ArrayList<Artista> noVerificados = new ArrayList<>();
        for (Artista a : artistas.values()) {
            if (!a.isVerificado()) {
                noVerificados.add(a);
            }
        }
        return noVerificados;
    }

    public ArrayList<Artista> getArtistas() {
        return new ArrayList<>(artistas.values());
    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException {
        for (Artista a : artistas.values()) {
            if (a.getNombreArtistico().equalsIgnoreCase(nombre)) {
                return a;
            }
        }
        throw new ArtistaNoEncontradoException("Artista no encontrado: " + nombre);
    }

    // GESTIÓN DE ÁLBUMES

    public Album crearAlbum(Artista artista, String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        if (!artista.isVerificado()) {
            throw new ArtistaNoVerificadoException("El artista no está verificado");
        }

        // Verificar si ya existe un álbum con ese título para ese artista
        for (Album a : artista.getAlbumes()) {
            if (a.getTitulo().equalsIgnoreCase(titulo)) {
                throw new AlbumYaExisteException("Ya existe un álbum con ese título");
            }
        }

        Album album = new Album(titulo, artista, fecha);
        album.setId(UUID.randomUUID().toString());
        album.setCanciones(new ArrayList<>());
        artista.getAlbumes().add(album);
        albumes.add(album);
        return album;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    //GESTOR DE CANCIONES

    public Cancion crearCancion(String titulo, int duracion, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        Cancion cancion = new Cancion(titulo, duracion, artista, genero);
        artista.publicarCancion(cancion);
        return cancion;
    }

    public Cancion crearCancionAlbum(String titulo, int duracion, Artista artista, GeneroMusical genero, Album album) throws DuracionInvalidaException, AlbumCompletoException {
        Cancion cancion = new Cancion(titulo, duracion, artista, genero);
        cancion.setAlbum(album);
        album.getCanciones().add(cancion);
        artista.publicarCancion(cancion);
        return cancion;
    }

    public void agregarContenidoCatalogo(Contenido contenido) {
        if (!catalogo.contains(contenido)) {
            catalogo.add(contenido);
        }
    }

    public void agregarCancionCatalogo(Contenido contenido) {
        agregarContenidoCatalogo(contenido);
    }

    public ArrayList<Cancion> getCanciones() {
        ArrayList<Cancion> canciones = new ArrayList<>();
        for (Contenido c : catalogo) {
            if (c instanceof Cancion) {
                canciones.add((Cancion) c);
            }
        }
        return canciones;
    }

    //GESTIÓN DE CREADORES/PODCAST

    public Creador registrarCreador(String nombreCanal, String nombre, String descripcion) {
        Creador creador = new Creador(nombreCanal, nombre, descripcion);
        creador.setId(UUID.randomUUID().toString());
        creador.setEpisodios(new ArrayList<>());
        creador.setRedesSociales(new HashMap<>());
        creadores.put(creador.getId(), creador);
        return creador;
    }

    public void registrarCreador(Creador creador) {
        if (creador.getId() == null) {
            creador.setId(UUID.randomUUID().toString());
        }
        creadores.put(creador.getId(), creador);
    }

    public Podcast crearPodcast(String titulo, int duracion, Creador creador, int numEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException, LimiteEpisodiosException {
        Podcast podcast = new Podcast(titulo, duracion, creador, numEpisodio, temporada, categoria);
        podcast.setInvitados(new ArrayList<>());
        creador.getEpisodios().add(podcast);
        catalogo.add(podcast);
        return podcast;
    }

    public ArrayList<Podcast> getPodcasts() {
        ArrayList<Podcast> podcasts = new ArrayList<>();
        for (Contenido c : catalogo) {
            if (c instanceof Podcast) {
                podcasts.add((Podcast) c);
            }
        }
        return podcasts;
    }

    public ArrayList<Creador> getTodosLosCreadores() {
        return new ArrayList<>(creadores.values());
    }

    //GESTION DE PLAYLISTS PUBLICAS

    public Playlist crearPlaylistPublica(String nombre, Usuario creador) {
        Playlist playlist = new Playlist(nombre, creador, true, "");
        playlist.setId(UUID.randomUUID().toString());
        playlist.setContenidos(new ArrayList<>());
        playlist.setFechaCreacion(new Date());
        playlistPublicas.add(playlist);
        return playlist;
    }

    public ArrayList<Playlist> getPlaylistsPublicas() {
        return playlistPublicas;
    }

    //GESTION DE BUSQUEDAS

    public ArrayList<Contenido> buscarContenido(String termino) throws ContenidoNoEncontradoException {
        ArrayList<Contenido> resultados = new ArrayList<>();
        String terminoLower = termino.toLowerCase();

        for (Contenido c : catalogo) {
            if (c.getTitulo().toLowerCase().contains(terminoLower)) {
                resultados.add(c);
            }
        }

        if (resultados.isEmpty()) {
            throw new ContenidoNoEncontradoException("No se encontró contenido con: " + termino);
        }

        return resultados;
    }

    public ArrayList<Cancion> buscarPorGenero(GeneroMusical genero) throws ContenidoNoEncontradoException {
        ArrayList<Cancion> resultados = new ArrayList<>();

        for (Contenido c : catalogo) {
            if (c instanceof Cancion) {
                Cancion cancion = (Cancion) c;
                if (cancion.getGenero() == genero) {
                    resultados.add(cancion);
                }
            }
        }

        if (resultados.isEmpty()) {
            throw new ContenidoNoEncontradoException("No se encontraron canciones del género: " + genero);
        }

        return resultados;
    }

    public ArrayList<Podcast> buscarPorCategoria(CategoriaPodcast categoria) throws ContenidoNoEncontradoException {
        ArrayList<Podcast> resultados = new ArrayList<>();

        for (Contenido c : catalogo) {
            if (c instanceof Podcast) {
                Podcast podcast = (Podcast) c;
                if (podcast.getCategoria() == categoria) {
                    resultados.add(podcast);
                }
            }
        }

        if (resultados.isEmpty()) {
            throw new ContenidoNoEncontradoException("No se encontraron podcasts de la categoría: " + categoria);
        }

        return resultados;
    }

    public ArrayList<Contenido> obtenerTopContenidos(int cantidad) {
        ArrayList<Contenido> sorted = new ArrayList<>(catalogo);
        sorted.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));

        if (cantidad >= sorted.size()) {
            return sorted;
        }

        return new ArrayList<>(sorted.subList(0, cantidad));
    }

    // GESTIÓN DE ANUNCIOS

    public Anuncio obtenerAnuncioAleatorio() {
        if (anuncios.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return anuncios.get(random.nextInt(anuncios.size()));
    }

    public void incrementarAnunciosReproducidos() {
        totalAnunciosReproducidos++;
    }

    // GESTION DE ESTADISTICAS

    public String obtenerEstadisticasGenerales() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== ESTADÍSTICAS DE LA PLATAFORMA ===\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("\n--- Usuarios ---\n");
        sb.append("Total Usuarios: ").append(usuarios.size()).append("\n");
        sb.append("Usuarios Premium: ").append(getUsuariosPremium().size()).append("\n");
        sb.append("Usuarios Gratuitos: ").append(getUsuariosGratuitos().size()).append("\n");
        sb.append("\n--- Contenido ---\n");
        sb.append("Total Contenido: ").append(catalogo.size()).append("\n");
        sb.append("Canciones: ").append(getCanciones().size()).append("\n");
        sb.append("Podcasts: ").append(getPodcasts().size()).append("\n");
        sb.append("\n--- Artistas y Creadores ---\n");
        sb.append("Total Artistas: ").append(artistas.size()).append("\n");
        sb.append("Artistas Verificados: ").append(getArtistasVerificados().size()).append("\n");
        sb.append("Total Creadores: ").append(creadores.size()).append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Plataforma{nombre='" + nombre + "'}";
    }
}

