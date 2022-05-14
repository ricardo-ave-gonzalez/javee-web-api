package ar.com.eduit.curso.java.web.repositories.interfaces;

import ar.com.eduit.curso.java.web.entities.Articulo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface I_ArticuloRepository {

    void save(Articulo articulo);                   // insert

    void remove(Articulo articulo);                 // delete

    List<Articulo> getAll();                        // select * from articulos

    default Stream<Articulo> getStream() {
        return getAll().stream();
    }

    default List<Articulo> getLikeDescripcion(String descripcion) {
        // select * from articulos where descripcion like '%descripcion%';
        /*
        List<Articulo>list=new ArrayList();
        if(descripcion==null) return list;
        for(Articulo a:getAll()){
            if(a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                list.add(a);
        }
        return list;
         */

        //Api Stream desde JDK 8
        if (descripcion == null) {
            return new ArrayList<Articulo>();
        }
        return getAll()
                .stream()
                .filter(a -> a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                .collect(Collectors.toList());
    }

    default Articulo getById(int id) {
        return getAll()
                .stream()
                .filter(a -> a.getId() == id)
                .findAny()
                .orElse(new Articulo());
    }
}
