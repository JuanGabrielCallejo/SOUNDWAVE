package modelo.plataforma;

import enums.CriterioOrden;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.UUID;

public class Playlist {

    private String id;
    private String nombre;
    private Usuario creador;
    private ArrayList<Contenido> contenidos;
    private boolean esPublica;
    private String descripcion;
    private String portadaURL;
    private Date fechaCreacion;
    private int maxContenidos;
    private int seguidores;

    private static final int MAX_CONTENIDOS_DEFAULT = 500;

    public Playlist(String nombre, Usuario creador) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.creador = creador;
        this.contenidos = new ArrayList<>();
        this.esPublica = false;
        this.maxContenidos = MAX_CONTENIDOS_DEFAULT;
        this.fechaCreacion = new Date();
        this.seguidores = 0;
    }

    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.creador = creador;
        this.contenidos = new ArrayList<>();
        this.esPublica = esPublica;
        this.descripcion = descripcion;
        this.maxContenidos = MAX_CONTENIDOS_DEFAULT;
        this.fechaCreacion = new Date();
        this.seguidores = 0;
    }

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

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public ArrayList<Contenido> getContenidos() {
        return contenidos;
    }

    public void setContenidos(ArrayList<Contenido> contenidos) {
        this.contenidos = contenidos;
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMaxContenidos() {
        return maxContenidos;
    }

    public void setMaxContenidos(int maxContenidos) {
        this.maxContenidos = maxContenidos;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public void agregarContenido(Contenido contenido) throws PlaylistLlenaException, ContenidoDuplicadoException {
        if (contenidos.size() >= maxContenidos) {
            throw new PlaylistLlenaException("La playlist está llena");
        }

        if (contenidos.contains(contenido)) {
            throw new ContenidoDuplicadoException("El contenido ya existe en la playlist");
        }

        contenidos.add(contenido);
    }

    public boolean eliminarContenido(String idContenido) {
        for (int i = 0; i < contenidos.size(); i++) {
            if (contenidos.get(i).getId().equals(idContenido)) {
                contenidos.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarContenido(Contenido contenido) {
        return contenidos.remove(contenido);
    }

    public void ordenarPor(CriterioOrden criterio) throws PlaylistVaciaException {
        if (contenidos.isEmpty()) {
            throw new PlaylistVaciaException("La playlist está vacía");
        }

        switch (criterio) {
            case POPULARIDAD:
                contenidos.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));
                break;
            case DURACION:
                contenidos.sort((c1, c2) -> Integer.compare(c1.getDuracionSegundos(), c2.getDuracionSegundos()));
                break;
            case ALFABETICO:
                contenidos.sort((c1, c2) -> c1.getTitulo().compareToIgnoreCase(c2.getTitulo()));
                break;
            case ALEATORIO:
                Collections.shuffle(contenidos);
                break;
            default:
                break;
        }
    }

    public int getDuracionTotal() {
        int total = 0;
        for (Contenido c : contenidos) {
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

    public void shuffle() {
        Collections.shuffle(contenidos);
    }

    public ArrayList<Contenido> buscarContenido(String termino) {
        ArrayList<Contenido> resultados = new ArrayList<>();
        String terminoLower = termino.toLowerCase();

        for (Contenido c : contenidos) {
            if (c.getTitulo().toLowerCase().contains(terminoLower)) {
                resultados.add(c);
            }
        }

        return resultados;
    }

    public void hacerPublica() {
        this.esPublica = true;
    }

    public void hacerPrivada() {
        this.esPublica = false;
    }

    public void incrementarSeguidores() {
        this.seguidores++;
    }

    public void decrementarSeguidores() {
        if (seguidores > 0) {
            this.seguidores--;
        }
    }

    public int getNumContenidos() {
        return contenidos.size();
    }

    public boolean estaVacia() {
        return contenidos.isEmpty();
    }

    public Contenido getContenido(int posicion) {
        if (posicion < 0 || posicion >= contenidos.size()) {
            return null;
        }
        return contenidos.get(posicion);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Playlist playlist = (Playlist) obj;
        return id != null && id.equals(playlist.id);
    }

    @Override
    public String toString() {
        return "Playlist{nombre='" + nombre + "', contenidos=" + contenidos.size() + ", esPublica=" + esPublica + "}";
    }
}
