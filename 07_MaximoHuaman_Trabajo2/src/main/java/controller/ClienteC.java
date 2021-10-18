package controller;

import dao.ClienteImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import lombok.Data;
import model.Cliente;

@Data

@Named(value = "clienteC")
@SessionScoped
public class ClienteC implements Serializable {

    Cliente cli;
    ClienteImpl dao;
    List<Cliente> listadoCli;
    int Listado = 1;

    public ClienteC() {
        cli = new Cliente();
        dao = new ClienteImpl();
        listadoCli = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            if (!dao.existe(cli, listadoCli)) {
                cli.setNOMCLI(CamelCase(cli.getNOMCLI()));
                cli.setAPECLI(CamelCase(cli.getAPECLI()));
                dao.registrar(cli);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Registro exitoso"));
                limpiar();
                listar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL, "ADVENTENCIA", "DNI existente"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Registro fallido"));
            System.out.println("Error en REGISTRAR ClienteC " + e.getMessage());
        }
        cli = new Cliente();
        listar();
    }

    public void limpiar() {
        cli = new Cliente();
    }

    public void listar() {
        try {
            listadoCli = dao.listar(Listado);
        } catch (Exception e) {
            System.out.println("Error en LISTAR ClienteC: " + e.getMessage());
        }
    }

    public String CamelCase(String camelcase) {
        char ch[] = camelcase.toCharArray();
        for (int i = 0; i < camelcase.length(); i++) {
            if (i == 0 && ch[i] != ' ' || ch[i] != ' ' && ch[i - 1] == ' ') {
                if (ch[i] >= 'a' && ch[i] <= 'z') {
                    ch[i] = (char) (ch[i] - 'a' + 'A');
                }
            } else if (ch[i] >= 'A' && ch[i] <= 'Z') {
                ch[i] = (char) (ch[i] + 'a' - 'A');
            }
        }
        String st = new String(ch);
        return st;
    }

    @PostConstruct
    public void construir() {
        listar();
    }
}
