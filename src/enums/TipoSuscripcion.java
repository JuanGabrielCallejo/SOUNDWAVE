package enums;

public enum TipoSuscripcion {

    GRATUITO(0.0, false, 50, false),
    PREMIUM(9.99, true, -1, true),
    FAMILIAR(14.99, true, -1, true),
    ESTUDIANTE(4.99, true, -1, true);

    private double precioMensual;
    private boolean sinAnuncios;
    private int limiteReproducciones;
    private boolean descargasOffline;


    TipoSuscripcion(double precioMensual, boolean sinAnuncios, int  limiteReproducciones, boolean descargasOffline) {
        this.precioMensual = precioMensual;
        this.sinAnuncios = sinAnuncios;
        this.limiteReproducciones = limiteReproducciones;
        this.descargasOffline = descargasOffline;
    }

    private double getPrecioMensual(){
        return precioMensual;
    };

    private boolean isSinAnuncios(){
        return false;
    }

    private int getLimiteReproducciones(){
        return limiteReproducciones;
    };

    private boolean isDescargasOffline(){
        return descargasOffline;
    }

    private boolean  tieneReproduccionesIlimitadas(){
        if(limiteReproducciones == -1){
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return name() + " : " + this.getPrecioMensual() + " €/mes";
    }
}
