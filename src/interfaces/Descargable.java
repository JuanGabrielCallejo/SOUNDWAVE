package interfaces;

public interface Descargable {
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException;
    public boolean eliminarDescarga();
    public int espacioRequerido();
}
