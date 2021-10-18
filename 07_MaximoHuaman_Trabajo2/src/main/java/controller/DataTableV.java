
package controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import model.Cliente;
import org.primefaces.event.DragDropEvent;
import services.ClienteServices;


public class DataTableV {
    
    @Inject
    private ClienteServices service;

    private List<Cliente> cliente;

    private List<Cliente> droppedCliente;

    private Cliente selectedCliente;

    @PostConstruct
    public void init() {
        cliente = service.getProducts(9);
        droppedCliente = new ArrayList<>();
    }

    public void onProductDrop(DragDropEvent<Cliente> ddEvent) {
        Cliente cliente = ddEvent.getData();

        droppedCliente.add(cliente);
        cliente.remove(cliente);
    }

    public void setService(ClienteServices service) {
        this.service = service;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public List<Cliente> getDroppedCliente() {
        return droppedCliente;
    }

    public Cliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedProduct(Cliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }
}
