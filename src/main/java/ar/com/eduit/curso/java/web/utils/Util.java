package ar.com.eduit.curso.java.web.utils;

public class Util {
    
    public static void controlarError(Runnable porcionCodigo) {
        try {
            ver("Realizando una consulta");
            porcionCodigo.run();
        } catch (Exception e) {
            ver(e.getMessage(), e);
        }
    }
    
    public static void ver(Object... params) {
        for (Object o : params) {
            System.out.println(o);
        }
    }
}
