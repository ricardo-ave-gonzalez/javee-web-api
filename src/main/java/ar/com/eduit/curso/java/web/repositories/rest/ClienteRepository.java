package ar.com.eduit.curso.java.web.repositories.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import ar.com.eduit.curso.java.web.entities.Cliente;
import ar.com.eduit.curso.java.web.repositories.interfaces.I_ClienteRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ClienteRepository implements I_ClienteRepository {

    private String url;
    private EntityManagerFactory emf;

    public ClienteRepository(String url) {
        this.url = url;
    }
    
    public ClienteRepository(String url, EntityManagerFactory emf) {
        this.url = url;
        this.emf = emf;
    }

    @Override
    public void save(Cliente cliente) {
        if (cliente == null) {
            return;
        }
        String url2 = url + "/alta?nombre=" + cliente.getNombre() + "&apellido=" + cliente.getApellido();
        //+"&direccion="+cliente.getDireccion()+"&comentarios="+cliente.getComentarios();
        String id = Client.getResponse(url2);
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void remove(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Cliente> getAll() {
        List<Cliente> list = new ArrayList();
        try {
            list = new Gson()
                    .fromJson(Client.getResponse(url + "/all"),
                            new TypeToken<List<Cliente>>() {
                            }.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
    }

    @Override
    public List<Cliente> getLikeApellido(String apellido) {
        List<Cliente> list = new ArrayList();
        if (apellido == null) {
            return list;
        }
        try {
            list = new Gson()
                    .fromJson(Client.getResponse(url + "/likeApellido?apellido=" + apellido),
                            new TypeToken<List<Cliente>>() {
                            }.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
    }

    @Override
    public Cliente getById(int id) {
        Cliente cliente = new Cliente();
        try {
            cliente = new Gson()
                    .fromJson(Client.getResponse(url + "/byId?id=" + id),
                            new TypeToken<Cliente>() {
                            }.getType());
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return cliente;
    }

}
