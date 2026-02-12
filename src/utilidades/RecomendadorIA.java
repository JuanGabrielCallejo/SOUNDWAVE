package utilidades;

import enums.AlgoritmoRecomendacion;
import enums.GeneroMusical;
import excepciones.recomendacion.HistorialVacioException;
import excepciones.recomendacion.ModeloNoEntrenadoException;
import excepciones.recomendacion.RecomendacionException;
import interfaces.Recomendador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class RecomendadorIA implements Recomendador {

    private HashMap<String, ArrayList<String>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilitud;
    private boolean modeloEntrenado;
    private ArrayList<Contenido> catalogoReferencia;
    private ArrayList<Usuario> usuariosReferencia;

    private static final double UMBRAL_DEFAULT = 0.6;

    public RecomendadorIA() {
        this.matrizPreferencias = new HashMap<>();
        this.historialCompleto = new HashMap<>();
        this.modeloEntrenado = false;
        this.umbralSimilitud = UMBRAL_DEFAULT;
    }

    public RecomendadorIA(AlgoritmoRecomendacion algoritmo) {
        this();
        this.algoritmo = algoritmo;
    }

    public HashMap<String, ArrayList<String>> getMatrizPreferencias() {
        return matrizPreferencias;
    }

    public void setMatrizPreferencias(HashMap<String, ArrayList<String>> matrizPreferencias) {
        this.matrizPreferencias = matrizPreferencias;
    }

    public HashMap<String, ArrayList<Contenido>> getHistorialCompleto() {
        return historialCompleto;
    }

    public void setHistorialCompleto(HashMap<String, ArrayList<Contenido>> historialCompleto) {
        this.historialCompleto = historialCompleto;
    }

    public AlgoritmoRecomendacion getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getUmbralSimilitud() {
        return umbralSimilitud;
    }

    public void setUmbralSimilitud(double umbralSimilitud) {
        this.umbralSimilitud = umbralSimilitud;
    }

    public boolean isModeloEntrenado() {
        return modeloEntrenado;
    }

    public void setModeloEntrenado(boolean modeloEntrenado) {
        this.modeloEntrenado = modeloEntrenado;
    }

    public ArrayList<Contenido> getCatalogoReferencia() {
        return catalogoReferencia;
    }

    public void setCatalogoReferencia(ArrayList<Contenido> catalogoReferencia) {
        this.catalogoReferencia = catalogoReferencia;
    }

    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException {
        if (!modeloEntrenado) {
            throw new ModeloNoEntrenadoException("El modelo no ha sido entrenado");
        }

        if (usuario.getHistorial() == null || usuario.getHistorial().isEmpty()) {
            throw new HistorialVacioException("El usuario no tiene historial");
        }

        ArrayList<Contenido> recomendaciones = new ArrayList<>();
        ArrayList<Contenido> historial = usuario.getHistorial();

        // Obtener géneros preferidos del historial
        HashMap<GeneroMusical, Integer> generosPreferidos = new HashMap<>();
        for (Contenido c : historial) {
            if (c instanceof Cancion) {
                GeneroMusical genero = ((Cancion) c).getGenero();
                generosPreferidos.put(genero, generosPreferidos.getOrDefault(genero, 0) + 1);
            }
        }

        // Recomendar contenido similar que no esté en el historial
        if (catalogoReferencia != null) {
            for (Contenido c : catalogoReferencia) {
                if (!historial.contains(c)) {
                    if (c instanceof Cancion) {
                        GeneroMusical genero = ((Cancion) c).getGenero();
                        if (generosPreferidos.containsKey(genero)) {
                            recomendaciones.add(c);
                        }
                    }
                }
            }
        }

        return recomendaciones;
    }

    @Override
    public ArrayList<Contenido> obetenerSimilares(Contenido contenido) throws RecomendacionException {
        return obtenerSimilares(contenido);
    }

    public ArrayList<Contenido> obtenerSimilares(Contenido contenido) throws RecomendacionException {
        ArrayList<Contenido> similares = new ArrayList<>();

        if (catalogoReferencia == null) {
            return similares;
        }

        if (contenido instanceof Cancion) {
            GeneroMusical genero = ((Cancion) contenido).getGenero();
            for (Contenido c : catalogoReferencia) {
                if (c instanceof Cancion && c != contenido) {
                    if (((Cancion) c).getGenero() == genero) {
                        similares.add(c);
                    }
                }
            }
        } else if (contenido instanceof Podcast) {
            var categoria = ((Podcast) contenido).getCategoria();
            for (Contenido c : catalogoReferencia) {
                if (c instanceof Podcast && c != contenido) {
                    if (((Podcast) c).getCategoria() == categoria) {
                        similares.add(c);
                    }
                }
            }
        }

        return similares;
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios) {
        this.usuariosReferencia = usuarios;
        this.modeloEntrenado = true;

        // Construir matriz de preferencias
        for (Usuario u : usuarios) {
            if (u.getHistorial() != null) {
                ArrayList<String> preferencias = new ArrayList<>();
                for (Contenido c : u.getHistorial()) {
                    preferencias.add(c.getId());
                }
                matrizPreferencias.put(u.getId(), preferencias);
                historialCompleto.put(u.getId(), u.getHistorial());
            }
        }
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = catalogo;
        entrenarModelo(usuarios);
    }

    public void actualizarPreferencias(Usuario usuario) {
        if (usuario.getHistorial() != null) {
            ArrayList<String> preferencias = new ArrayList<>();
            for (Contenido c : usuario.getHistorial()) {
                preferencias.add(c.getId());
            }
            matrizPreferencias.put(usuario.getId(), preferencias);
            historialCompleto.put(usuario.getId(), usuario.getHistorial());
        }
    }

    public double calcularSimilitud(Usuario u1, Usuario u2) {
        if (u1.getHistorial() == null || u2.getHistorial() == null) {
            return 0.0;
        }

        if (u1.getHistorial().isEmpty() || u2.getHistorial().isEmpty()) {
            return 0.0;
        }

        int comunes = 0;
        for (Contenido c1 : u1.getHistorial()) {
            for (Contenido c2 : u2.getHistorial()) {
                if (c1.getId().equals(c2.getId())) {
                    comunes++;
                    break;
                }
            }
        }

        int total = u1.getHistorial().size() + u2.getHistorial().size() - comunes;
        if (total == 0) {
            return 0.0;
        }

        return (double) comunes / total;
    }

    public HashMap<GeneroMusical, Integer> obtenerGenerosPopulares() {
        HashMap<GeneroMusical, Integer> generosPopulares = new HashMap<>();

        if (catalogoReferencia != null) {
            for (Contenido c : catalogoReferencia) {
                if (c instanceof Cancion) {
                    GeneroMusical genero = ((Cancion) c).getGenero();
                    generosPopulares.put(genero, generosPopulares.getOrDefault(genero, 0) + c.getReproducciones());
                }
            }
        }

        return generosPopulares;
    }

    @Override
    public String toString() {
        return "RecomendadorIA{" +
                "modeloEntrenado=" + modeloEntrenado +
                ", algoritmo=" + algoritmo +
                '}';
    }
}
