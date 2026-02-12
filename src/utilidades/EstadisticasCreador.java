package utilidades;

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

    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
        this.episodiosPorTemporada = new HashMap<>();
        calcularEstadisticas();
    }

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

    private void calcularEstadisticas() {
        if (creador == null || creador.getEpisodios() == null) {
            return;
        }

        this.totalEpisodios = creador.getNumEpisodios();
        this.totalSuscriptores = creador.getSuscriptores();
        this.totalReproducciones = 0;
        this.totalLikes = 0;
        this.duracionTotalSegundos = 0;
        int maxRepros = 0;

        for (Podcast p : creador.getEpisodios()) {
            totalReproducciones += p.getReproducciones();
            totalLikes += p.getLikes();
            duracionTotalSegundos += p.getDuracionSegundos();

            // Episodio más popular
            if (p.getReproducciones() > maxRepros) {
                maxRepros = p.getReproducciones();
                episodioMasPopular = p;
            }

            // Contar episodios por temporada
            int temporada = p.getTemporada();
            episodiosPorTemporada.put(temporada, episodiosPorTemporada.getOrDefault(temporada, 0) + 1);
        }

        if (totalEpisodios > 0) {
            this.promedioReproducciones = (double) totalReproducciones / totalEpisodios;
        }
    }

    private String formatearDuracion(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;

        if (horas > 0) {
            return String.format("%d:%02d:%02d", horas, minutos, segs);
        } else {
            return String.format("%02d:%02d", minutos, segs);
        }
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== REPORTE DE ESTADÍSTICAS ===\n");
        sb.append("Canal: ").append(creador.getNombreCanal()).append("\n");
        sb.append("Total Episodios: ").append(totalEpisodios).append("\n");
        sb.append("Total Suscriptores: ").append(totalSuscriptores).append("\n");
        sb.append("Total Reproducciones: ").append(totalReproducciones).append("\n");
        sb.append("Promedio Reproducciones: ").append(String.format("%.2f", promedioReproducciones)).append("\n");
        sb.append("Duración Total: ").append(formatearDuracion(duracionTotalSegundos)).append("\n");
        if (episodioMasPopular != null) {
            sb.append("Episodio Más Popular: ").append(episodioMasPopular.getTitulo()).append("\n");
        }
        return sb.toString();
    }

    public double calcularEngagement() {
        if (totalSuscriptores == 0) {
            return 0;
        }
        return (double) totalReproducciones / totalSuscriptores;
    }

    public int estimarCrecimientoMensual() {
        // Estimación simple basada en el promedio de reproducciones
        return (int) (promedioReproducciones * 0.1);
    }

    @Override
    public String toString() {
        return generarReporte();
    }
}
