package modelo.utilidades;

import enums.AlgoritmoRecomendacion;
import interfaces.Recomendador;
import modelo.contenido.Contenido;

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


    public RecomendadorIA() {
    }

    public RecomendadorIA(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }
}
