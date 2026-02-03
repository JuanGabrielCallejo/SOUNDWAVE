package interfaces;

import java.util.ArrayList;

public interface Recomendador {
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException;

    public ArrayList<Contenido> obetenerSimilares (Contenido contenido) throws RecomendacionException;

}
