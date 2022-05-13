package ar.com.eduit.curso.java.web.managed.bean;

import ar.com.eduit.curso.java.web.entities.Articulo;
import ar.com.eduit.curso.java.web.entities.Cliente;
import ar.com.eduit.curso.java.web.repositories.interfaces.I_ClienteRepository;
import ar.com.eduit.curso.java.web.repositories.rest.ClienteRepository;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named("clienteMB")
@SessionScoped
public class ClienteManagedBean implements Serializable {

    String url = "http://localhost:8086/javee-web-api-1.0/resources/clientes/v1";
    private I_ClienteRepository cr = new ClienteRepository(url);
    private Cliente cliente = new Cliente();
    private String mensaje = "";
    private String buscarApellido = "";

    public void save() {
        try {
            cr.save(cliente);
            mensaje = "Se dio de alta un cliente id: " + cliente.getId();
            addMessage(FacesMessage.SEVERITY_INFO, "Información", mensaje);
            cliente = new Cliente();
        } catch (Exception e) {
            mensaje = "Ocurrio un error!!";
        }
    }

    public List<Cliente> getAll() {
        return cr.getAll();
    }

    public List<Cliente> getLikeApellido() {
        return cr.getLikeApellido(buscarApellido);
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public I_ClienteRepository getCr() {
        return cr;
    }

    public void setCr(I_ClienteRepository cr) {
        this.cr = cr;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getBuscarApellido() {
        return buscarApellido;
    }

    public void setBuscarApellido(String buscarApellido) {
        this.buscarApellido = buscarApellido;
    }

}
