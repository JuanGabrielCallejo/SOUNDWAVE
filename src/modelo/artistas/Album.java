package modelo.artistas;

import enums.GeneroMusical;
import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class Album {

    private String id;
    private String titulo;
    private Artista artista;
    private Date fechaLanzamiento;
    private ArrayList<Cancion> canciones;
    private String portadaURL;
    private String discografica;
    private String tipoAlbum;

    private static final int MAX_CANCIONES = 20;

    public Album(String titulo, Artista artista, Date fechaLanzamiento) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
    }

    public Album(String titulo, Artista artista, Date fechaLanzamiento, String discografica, String tipoAlbum) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.artista = artista;
        this.fechaLanzamiento = fechaLanzamiento;
        this.canciones = new ArrayList<>();
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public ArrayList<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }

    public int getMaxCanciones() {
        return MAX_CANCIONES;
    }

    // COMPOSICIÓN: El álbum crea sus propias canciones
    public Cancion crearCancion(String titulo, int duracion, GeneroMusical genero) throws DuracionInvalidaException, AlbumCompletoException {
        if (canciones.size() >= MAX_CANCIONES) {
            throw new AlbumCompletoException("El álbum ya tiene el máximo de canciones permitidas");
        }

        Cancion cancion = new Cancion(titulo, duracion, artista, genero);
        cancion.setAlbum(this);
        canciones.add(cancion);
        artista.publicarCancion(cancion);
        return cancion;
    }

    public void eliminarCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion <= 0 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("La canción en la posición " + posicion + " no existe en el álbum.");
        }
        Cancion cancion = canciones.remove(posicion - 1);
        cancion.setAlbum(null);
    }

    public void eliminarCancion(Cancion cancion) {
        canciones.remove(cancion);
        cancion.setAlbum(null);
    }

    public int getNumCanciones() {
        return canciones.size();
    }

    public void ordenarPorPopularidad() {
        canciones.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));
    }

    public Cancion getCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion <= 0 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("La canción en la posición " + posicion + " no existe en el álbum.");
        }
        return canciones.get(posicion - 1);
    }

    public int getTotalReproducciones() {
        int total = 0;
        for (Cancion c : canciones) {
            total += c.getReproducciones();
        }
        return total;
    }

    public int getDuracionTotal() {
        int total = 0;
        for (Cancion c : canciones) {
            total += c.getDuracionSegundos();
        }
        return total;
    }

    public String getDuracionTotalFormateada() {
        int segundos = getDuracionTotal();
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;

        if (horas > 0) {
            return String.format("%d:%02d:%02d", horas, minutos, segs);
        } else {
            return String.format("%02d:%02d", minutos, segs);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return id != null && id.equals(album.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Album{titulo='" + titulo + "', artista=" + artista.getNombreArtistico() + ", canciones=" + canciones.size() + "}";
    }
}
