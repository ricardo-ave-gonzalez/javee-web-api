package ar.com.eduit.curso.java.web.managed.bean;


import ar.com.eduit.curso.java.web.repositories.interfaces.I_ArticuloRepository;
import ar.com.eduit.curso.java.web.repositories.rest.ArticuloRepository;
import ar.com.eduit.curso.java.web.entities.Articulo;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.persistence.EntityManagerFactory;
    

@Named("articuloMB")
@SessionScoped
public class ArticuloManagedBean implements Serializable {
    
    private Articulo articulo= new Articulo();
    private String mensaje="";
    private String buscarDescripcion="";
    private I_ArticuloRepository ar=new ArticuloRepository("http://localhost:8086/javee-web-api-1.0/resources/articulos/v1");
    
    public void save(){
        try {
            articulo.setDescripcion(articulo.getDescripcion().replace(" ", "_"));
            ar.save(articulo);
            mensaje="Se dio de alta un articulo!!";
            articulo=new Articulo();
        } catch (Exception e) {
            mensaje="Ocurrio un error!!";
        }
    }
    
    public List<Articulo> getAll(){
        return ar.getAll();
    }
    
    public List<Articulo> getLikeDescripcion(){
        return ar.getLikeDescripcion(buscarDescripcion);
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscarDescripcion() {
        return buscarDescripcion;
    }

    public void setBuscarDescripcion(String buscarDescripcion) {
        this.buscarDescripcion = buscarDescripcion;
    }
}