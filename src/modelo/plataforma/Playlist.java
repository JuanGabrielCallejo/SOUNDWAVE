package modelo.plataforma;

import enums.CriterioOrden;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.playlist.PlaylistLlenaException;
import modelo.contenido.Contenido;

import java.util.ArrayList;
import java.util.Date;

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

    private static final int MAX_CONTENIDOS_DEFAULT = 500;

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

    public Playlist(String nombre, Usuario creador) {
        this.nombre = nombre;
        this.creador = creador;
    }

    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.nombre = nombre;
        this.creador = creador;
        this.esPublica = esPublica;
        this.descripcion = descripcion;
    }

    public void agregarContenido(Contenido contenido) throws PlaylistLlenaException, ContenidoNoDisponibleException {

    }

    public boolean eliminarContenido(String idContenido) {
        return false;
    }

     public boolean eliminarContenido (Contenido contenido){
        return false;
     }

     public void ordenarPor(CriterioOrden criterio) throws PlaylistLlenaException{}

    public int getDuracionTotal(){
        return 0;
    }
    public String getduracionTotalFormateada(){
        return "";
    }

    public void shuffle(){}

    public ArrayList<Contenido> buscarContenido(String termino){
        return new ArrayList<>();
    }

    public void hacerPublica(){
        this.esPublica = true;
    }

    public void hacerPrivada(){
        this.esPublica = false;
    }

    public void incrementarSeguidores(){

    }

    public void decrementarSeguidores(){

    }

     public int getNumContenidos(){
        return 0;
     }

     public boolean estaVacia(){
        return false;
     }

     public Contenido getContenido(int posicion){
        return null;
     }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
