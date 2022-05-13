package ar.com.eduit.curso.java.web.repositories.interfaces;

import ar.com.eduit.curso.java.web.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface I_ClienteRepository {
    void save(Cliente cliente);
    void remove(Cliente cliente);
    void update(Cliente cliente);
    List<Cliente> getAll();
    
    default Stream<Cliente> getStream(){
        return getAll().stream();
    }
    
    default List<Cliente> getLikeApellido(String apellido){
        if(apellido==null) return new ArrayList<Cliente>();
        return getStream().
                filter(c->c.getApellido().toLowerCase().contains(apellido.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    default Cliente getById(int id){
        return getStream()
                .filter(c->c.getId()==id)
                .findAny()
                .orElse(new Cliente());
    }
}