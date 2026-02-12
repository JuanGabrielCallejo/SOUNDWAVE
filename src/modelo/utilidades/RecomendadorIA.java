package modelo.utilidades;

import enums.AlgoritmoRecomendacion;
import excepciones.recomendacion.RecomendacionException;
import interfaces.Recomendador;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.ArrayList;
import java.util.HashMap;

public class RecomendadorIA implements Recomendador {

    private HashMap<String, ArrayList<String>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialalCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilitud;
    private boolean modeloEntrenado;
    private ArrayList<Contenido>  catalogoReferencia;

    private static final double UMBRAL_DEFAULT = 0.6;


    public HashMap<String, ArrayList<String>> getMatrizPreferencias() {
        return matrizPreferencias;
    }

    public void setMatrizPreferencias(HashMap<String, ArrayList<String>> matrizPreferencias) {
        this.matrizPreferencias = matrizPreferencias;
    }

    public HashMap<String, ArrayList<Contenido>> getHistorialalCompleto() {
        return historialalCompleto;
    }

    public void setHistorialalCompleto(HashMap<String, ArrayList<Contenido>> historialalCompleto) {
        this.historialalCompleto = historialalCompleto;
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

    public RecomendadorIA() {
    }

    public RecomendadorIA(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }


    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException {
        return null;
    }

    @Override
    public ArrayList<Contenido> obetenerSimilares(Contenido contenido) throws RecomendacionException {
        return null;
    }

    public void entrenarModelo(ArrayList<Usuario>usuarios){

    }

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo){}

    private double calcularSimilitud(Usuario u1, Usuario u2){
        return 0;
    }


    @Override
    public String toString() {
        return super.toString();
    }







}
