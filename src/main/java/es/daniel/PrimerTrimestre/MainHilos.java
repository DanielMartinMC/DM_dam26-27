package es.daniel.PrimerTrimestre;

public class MainHilos {

    public static void main(String[] args) {
        PracticaHilos descargas = new PracticaHilos();


        descargas.crearDescargas();
        descargas.esperarFinal();
        descargas.debug();
    }

}
