
package services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import model.Cliente;

public class ClienteServices {

    private List<Cliente> clientes;

    @PostConstruct
    public void init() {
        clientes = new ArrayList<>();
        clientes.add(new Cliente(1, "PEDRO CARLOS", "VARGAS CAMPOS", "45367892", "M"));
        clientes.add(new Cliente(2, "ANA LUCIA", "VEGAS RODRIGUEZ", "69067892", "F"));
        clientes.add(new Cliente(3, "FRANCISCO EDUARDO", "LLOSA GOMEZ", "40923892", "M"));
        clientes.add(new Cliente(4, "PEDRO PABLO", "GONZALES GODINEZ", "45360012", "M"));
        clientes.add(new Cliente(5, "MARIA PAULA", "RODRIGUEZ GONZALES", "56701292", "F"));
        clientes.add(new Cliente(6, "FERNANDO DANTE", "QUISPE AVALOS", "67677892", "M"));
    }

    public List<Cliente> getCliente() {
        return new ArrayList<>(clientes);
    }

    public List<Cliente> getProducts(int size) {

        if (size > clientes.size()) {
            Random rand = new Random();

            List<Cliente> randomList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int randomIndex = rand.nextInt(clientes.size());
                randomList.add(clientes.get(randomIndex));
            }

            return randomList;
        } else {
            return new ArrayList<>(clientes.subList(0, size));
        }

    }

//    public List<Cliente> getClonedProducts(int size) {
//        List<Cliente> results = new ArrayList<>();
//        List<Cliente> originals = getProducts(size);
//        for (Cliente original : originals) {
//            results.add(original.clone());
//        }
//        return results;
//    }
}
