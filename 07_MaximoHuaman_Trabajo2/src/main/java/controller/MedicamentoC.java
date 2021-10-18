package controller;

import dao.MedicamentoImpl;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.sql.SQLException;
import model.Medicamento;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import lombok.Data;

@Data
@Named(value = "medicamentoC")
@ConversationScoped
public class MedicamentoC implements Serializable {

    Medicamento med;
    MedicamentoImpl dao;
    List<Medicamento> listadomed;
    int Listado = 1;

    public MedicamentoC() {
        med = new Medicamento();
        dao = new MedicamentoImpl();
        listadomed = new ArrayList<>();
    }

    public void registrar() throws Exception {
        try {
            if (!dao.existePresentación(med, listadomed) && !dao.existeLote(med, listadomed)) {
                med.setIDPROV(dao.obtenerCodigoProveedor(med.getIDPROV()));
                dao.registrar(med);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Registro exitoso"));
                listar();
                limpiar();
            } else {
                if (dao.existePresentación(med, listadomed)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "ADVENTENCIA", "Presentación existente"));
                }
                if (dao.existeLote(med, listadomed)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "ADVENTENCIA", "Lote existente"));
                }
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Registro fallido"));
            System.out.println("Error en REGISTRAR MedicamentoC" + e.getMessage());
        }
        med = new Medicamento();
        listar();
    }

    public void modificar() throws Exception {
        try {
            dao.modificar(med);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Modificado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Modificación fallida"));
            System.out.println("Error en MODIFICAR MedicamentoC" + e.getMessage());
        }
    }

    public void eliminar() throws Exception {
        try {
            dao.eliminar(med);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "GENIAL", "Eliminado con éxito"));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Eliminación fallida"));
            System.out.println("Error en ELIMINAR MedicamentoC" + e.getMessage());
        }
    }

    public void Desactivar() throws Exception {
        try {
            dao.desactivar(med);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "OK", "Desactivado con éxito"));
            limpiar();
            listar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "Desactivación fallida"));
            System.out.println("Error en DESACTIVAR MedicamentoC " + e.getMessage());
        }
    }

    public List<String> completeTextProveedor(String query) throws SQLException, Exception {
        MedicamentoImpl daoPro = new MedicamentoImpl();
        return daoPro.autocompleteProveedor(query);
    }

    public void limpiar() {
        med = new Medicamento();

    }

    public void listar() {
        try {
            listadomed = dao.listar(Listado);
        } catch (Exception e) {
            System.out.println("Error en LISTAR MedicamentoC: " + e.getMessage());
        }
    }

    @PostConstruct
    public void construir() {
        listar();
    }

}
