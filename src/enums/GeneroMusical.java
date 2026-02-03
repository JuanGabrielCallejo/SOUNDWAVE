package enums;

public enum GeneroMusical {

    POP("Pop", "Música popular contemporánea"),
    ROCK("Rock", "Rock clásico y moderno"),
    HIPHOP("Hip Hop", "Hip hop y rap"),
    JAZZ("Jazz", "Jazz clásico y contemporáneo"),
    ELECTRONICA("Electrónica", "Música electrónica y EDM"),
    REGGAETON("Reggaetón", "Reggaetón y música urbana latina"),
    INDIE("Indie", "Música independiente"),
    CLASICA("Clásica", "Música clásica"),
    COUNTRY("Country", "Música country"),
    METAL("Metal", "Heavy metal y subgéneros"),
    RNB("R&B", "Rhythm and Blues"),
    SOUL("Soul", "Música soul"),
    BLUES("Blues", "Blues clásico y contemporáneo"),
    TRAP("Trap", "Trap y música urbana");

    private String genero;
    private String descripcion;

    GeneroMusical(String genero, String descripcion) {
        this.genero = genero;
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return genero;
    }

    public String getDescription(){
        return descripcion;
    }


    @Override
    public String toString() {
        return super.toString();
    }
}
