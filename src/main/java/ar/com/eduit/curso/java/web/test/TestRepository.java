package ar.com.eduit.curso.java.web.test;

import ar.com.eduit.curso.java.web.entities.Articulo;
import ar.com.eduit.curso.java.web.entities.Cliente;
import ar.com.eduit.curso.java.web.repositories.interfaces.I_ArticuloRepository;
import ar.com.eduit.curso.java.web.repositories.rest.ArticuloRepository;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TestRepository {

    public static void main(String[] args) {

        
        
        System.out.println("1*************************************************");
        System.out.println(LocalTime.now());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAPU");
        System.out.println("2*************************************************");
        System.out.println(LocalTime.now());
        EntityManager em = emf.createEntityManager();
        I_ArticuloRepository ar = new ArticuloRepository(em);

        //Curso curso=new Curso("Jardineria","Rios","JUEVES","NOCHE");
        /*
        Cliente cliente = new Cliente("Richard", "Gonzalez", 33, 1);
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        
        Articulo articulo = new Articulo("Laptop DELL", 150000, 3);
        em.getTransaction().begin();
        em.persist(articulo);
        em.getTransaction().commit();
        
        em.getTransaction().begin();
        Articulo articulo = new Articulo("Laptop3 DELL", 150000, 4);       
        
        em.persist(articulo);
        //Query query=em.createNamedQuery("Articulo.findById");
        Cliente cliente = new Cliente("Richard2", "Gonzalez", 33, articulo);
        em.persist(cliente);
        //em.remove(query.getSingleResult());
        em.getTransaction().commit();
         
        */
        ar.getAll().forEach(System.out::println);
        emf.close();
    }
}
