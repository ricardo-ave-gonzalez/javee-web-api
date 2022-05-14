package ar.com.eduit.curso.java.web.repositories.rest;

import ar.com.eduit.curso.java.web.entities.Articulo;
import ar.com.eduit.curso.java.web.entities.Cliente;
import ar.com.eduit.curso.java.web.repositories.interfaces.I_ArticuloRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ArticuloRepository implements I_ArticuloRepository {

    private String url;
    //private EntityManagerFactory emf;

    @PersistenceContext(unitName = "Articulo")
    private EntityManager em;

    public ArticuloRepository(EntityManager em) {
        this.em = em;
    }

    public ArticuloRepository() {
    }

    public ArticuloRepository(String url) {
        this.url = url;
    }

    @Override
    public void save(Articulo articulo) {
        if (articulo == null) {
            return;
        }
        String url2 = url + "/alta?id=" + articulo.getId() + "&descripcion=" + articulo.getDescripcion()
                + "&precio=" + articulo.getPrecio();
        System.out.println("url");
        Client.getResponse(url2);
        try {
            em.getTransaction().begin();
            em.persist(articulo);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Articulo articulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Articulo> getAll() {
        String query = "SELECT a FROM Articulo a";
        List<Articulo> articulos = em.createQuery(query).getResultList();
        return articulos;
    }    
    
    @Override
    public Articulo getById(int id) {
        Articulo articulo = new Articulo();
        try {
            articulo = new Gson()
                    .fromJson(Client.getResponse(url + "/byId?id=" + id),
                            new TypeToken<Articulo>() {
                            }.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return articulo;
    }

}
