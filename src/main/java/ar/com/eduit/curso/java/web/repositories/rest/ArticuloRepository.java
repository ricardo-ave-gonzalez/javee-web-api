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
import javax.persistence.TypedQuery;

public class ArticuloRepository implements I_ArticuloRepository {

    private String url;
    private EntityManagerFactory emf;

    public ArticuloRepository() {
    }

    public ArticuloRepository(EntityManagerFactory emf) {
        this.emf = emf;
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
            EntityManager em = emf.createEntityManager();
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
        List<Articulo> list = new ArrayList();
        try {
            EntityManager em = emf.createEntityManager();
            final String query = "SELECT c from Articulo c";
            TypedQuery<Articulo> query1 = em.createQuery(query, Articulo.class);
            list = query1.getResultList();
            

            //EntityManager em = emf.createEntityManager();
            //list = (List<Articulo>) em.createNamedQuery("Alumno.findAll").getResultList();
            //list = new Gson()
            //        .fromJson(Client.getResponse(url + "/all"),
            //                new TypeToken<List<Articulo>>() {
            //                }.getType());

            em.close();
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
    }

    @Override
    public List<Articulo> getLikeDescripcion(String descripcion) {
        List<Articulo> list = new ArrayList();
        if (descripcion == null) {
            return list;
        }
        try {

            EntityManager em = emf.createEntityManager();
            list = (List<Articulo>) em.createNamedQuery("Alumno.findAll").getResultList();
            em.close();
            list = new Gson()
                    .fromJson(Client.getResponse(url + "/likeDescripcion?descripcion=" + descripcion),
                            new TypeToken<List<Articulo>>() {
                            }.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
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
