package modelo.artistas;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Creador {

    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios;
    private int suscriptores;
    private String descripcion;
    private HashMap<String, String> redesSociales;
    private ArrayList<CategoriaPodcast> categoriasPrincipales;

    private static final int MAX_EPISODIOS = 500;

    public Creador(String nombreCanal, String nombre) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.episodios = new ArrayList<>();
        this.redesSociales = new HashMap<>();
        this.categoriasPrincipales = new ArrayList<>();
    }

    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.episodios = new ArrayList<>();
        this.redesSociales = new HashMap<>();
        this.categoriasPrincipales = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Podcast> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(ArrayList<Podcast> episodios) {
        this.episodios = episodios;
    }

    public int getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public HashMap<String, String> getRedesSociales() {
        return redesSociales;
    }

    public void setRedesSociales(HashMap<String, String> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public ArrayList<CategoriaPodcast> getCategoriasPrincipales() {
        return categoriasPrincipales;
    }

    public void setCategoriasPrincipales(ArrayList<CategoriaPodcast> categoriasPrincipales) {
        this.categoriasPrincipales = categoriasPrincipales;
    }

    public int getNumEpisodios() {
        return episodios.size();
    }

    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if (episodios.size() >= MAX_EPISODIOS) {
            throw new LimiteEpisodiosException("Has alcanzado el límite de episodios");
        }
        episodios.add(episodio);
    }

    public EstadisticasCreador obtenerEstadisticas() {
        return new EstadisticasCreador(this);
    }

    public void agregarRedSocial(String red, String usuario) {
        redesSociales.put(red.toLowerCase(), usuario);
    }

    public double calcularPromedioReproducciones() {
        if (episodios == null || episodios.isEmpty()) {
            return 0;
        }

        int total = 0;
        for (Podcast p : episodios) {
            total += p.getReproducciones();
        }

        return (double) total / episodios.size();
    }

    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException {
        boolean encontrado = false;
        for (int i = 0; i < episodios.size(); i++) {
            if (episodios.get(i).getId().equals(idEpisodio)) {
                episodios.remove(i);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            throw new EpisodioNoEncontradoException("Episodio no encontrado: " + idEpisodio);
        }
    }

    public int getTotalReproducciones() {
        int total = 0;
        for (Podcast p : episodios) {
            total += p.getReproducciones();
        }
        return total;
    }

    public void incrementarSuscriptores() {
        this.suscriptores++;
    }

    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad) {
        ArrayList<Podcast> copia = new ArrayList<>(episodios);
        copia.sort((p1, p2) -> Integer.compare(p2.getReproducciones(), p1.getReproducciones()));

        if (cantidad >= copia.size()) {
            return copia;
        }

        return new ArrayList<>(copia.subList(0, cantidad));
    }

    public int getUltimaTemporada() {
        int maxTemporada = 0;
        for (Podcast p : episodios) {
            if (p.getTemporada() > maxTemporada) {
                maxTemporada = p.getTemporada();
            }
        }
        return maxTemporada;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Creador creador = (Creador) obj;
        return id != null && id.equals(creador.id);
    }

    @Override
    public String toString() {
        return "Creador{nombreCanal='" + nombreCanal + "', suscriptores=" + suscriptores + "}";
    }
}
