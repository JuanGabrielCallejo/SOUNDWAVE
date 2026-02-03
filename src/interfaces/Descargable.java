package interfaces;

import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;

public interface Descargable {
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException;
    public boolean eliminarDescarga();
    public int espacioRequerido();
}
