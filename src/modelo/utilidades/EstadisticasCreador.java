package modelo.utilidades;

import modelo.artistas.Creador;
import modelo.contenido.Podcast;

import java.util.HashMap;

public class EstadisticasCreador {

    private Creador creador;
    private int totalEpisodios;
    private int totalReproducciones;
    private double promedioReproducciones;
    private int totalSuscriptores;
    private int totalLikes;
    private int duracionTotalSegundos;
    private Podcast episodioMasPopular;
    private HashMap<Integer, Integer> episodiosPorTemporada;




    public Creador getCreador() {
        return creador;
    }

    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    public void setTotalEpisodios(int totalEpisodios) {
        this.totalEpisodios = totalEpisodios;
    }

    public int getTotalReproducciones() {
        return totalReproducciones;
    }

    public void setTotalReproducciones(int totalReproducciones) {
        this.totalReproducciones = totalReproducciones;
    }

    public double getPromedioReproducciones() {
        return promedioReproducciones;
    }

    public void setPromedioReproducciones(double promedioReproducciones) {
        this.promedioReproducciones = promedioReproducciones;
    }

    public int getTotalSuscriptores() {
        return totalSuscriptores;
    }

    public void setTotalSuscriptores(int totalSuscriptores) {
        this.totalSuscriptores = totalSuscriptores;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public int getDuracionTotalSegundos() {
        return duracionTotalSegundos;
    }

    public void setDuracionTotalSegundos(int duracionTotalSegundos) {
        this.duracionTotalSegundos = duracionTotalSegundos;
    }

    public Podcast getEpisodioMasPopular() {
        return episodioMasPopular;
    }

    public void setEpisodioMasPopular(Podcast episodioMasPopular) {
        this.episodioMasPopular = episodioMasPopular;
    }

    public HashMap<Integer, Integer> getEpisodiosPorTemporada() {
        return episodiosPorTemporada;
    }

    public void setEpisodiosPorTemporada(HashMap<Integer, Integer> episodiosPorTemporada) {
        this.episodiosPorTemporada = episodiosPorTemporada;
    }

    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
    }

    private void calcularEstadisticas(){}

    private String formatearDuracion(int segundos){
        return null;
    }

    public String generarReporte(){
        return null;
    }

    public double calcularEngagement(){
        return 0;
    }

    public int estimarCrecimientoMensual(){ return 0; };


    @Override
    public String toString() {
        return super.toString();
    }
}
