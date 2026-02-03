package modelo.contenido;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public abstract class Contenido {

    private String id;
    private String titulo;
    private int reproducciones;
    private int likes;
    private int duracionSegundos;
    private ArrayList<String> tags;
    private boolean disponible;
    private Date fechaPublicacion;


    public Contenido(String titulo,int duracionSegundos) throws DuracionInvalidaException {

        setDuracionSegundos(duracionSegundos);

        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.reproducciones = reproducciones;
        this.likes = 0;
        this.duracionSegundos = duracionSegundos;
        this.tags = new ArrayList<>();
        this.disponible = true;
        this.fechaPublicacion = new Date();
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

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) throws DuracionInvalidaException {
        if(duracionSegundos <=0){
            throw new DuracionInvalidaException("La duracion debe ser umayor que 0.");
        }
        this.duracionSegundos = duracionSegundos;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public abstract void reproducir() throws ContenidoNoDisponibleException;

    public void aumentarReproducciones() {
        this.reproducciones++;
    }

    public void agregarLike() {
        this.likes++;
    }

    public boolean esPopular(){
        if(reproducciones > 100000){
            return true;
        } else {
            return false;
        }

    }

    public void validarDuracion() throws DuracionInvalidaException{
        if(duracionSegundos <=0){
            throw new DuracionInvalidaException("La duracion debe ser mayor que 0.");
        }
    }

    public void agregarTag(String tag){};

    public boolean tieneTag(String tag){
        return tags.contains(tag);
    }

    public void marcarNoDisponible(){
        this.disponible = false;
    }

    public void marcarDisponible(){
        this.disponible = true;
    }

    public String getDuracionFormateada(int duracionSegundos){
        int minutos = duracionSegundos / 60;
        int segundos = duracionSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
