package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Medicamento;
import trabajo02.UtilToSql;

public class MedicamentoImpl extends Conexion implements ICRUD<Medicamento> {

    @Override
    public void registrar(Medicamento modelo) throws Exception {
        String sql = "INSERT INTO MEDICAMENTO (PRESMED, GENMED, COMMED, PRECMED, FVMED, STOCMED, "
                + "LOTMED, IDPROV, ESTMED) "
                + "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getPRESMED());
            ps.setString(2, modelo.getGENMED());
            ps.setString(3, modelo.getCOMMED());
            ps.setDouble(4, modelo.getPRECMED());
            ps.setDate(5, UtilToSql.convertDate(modelo.getFVMED()));
            ps.setInt(6, modelo.getSTOCMED());
            ps.setString(7, modelo.getLOTMED());
            ps.setString(8, modelo.getIDPROV());
            ps.setString(9, "A");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en registrar MedicamentoImpl:" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    @Override
    public void modificar(Medicamento modelo) throws Exception {
        String sql = "UPDATE MEDICAMENTO SET PRESMED=?, GENMED=?, COMMED=?, PRECMED=?, FVMED=?, STOCMED=?, LOTMED=?, IDPROV=? WHERE IDMED=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getPRESMED());
            ps.setString(2, modelo.getGENMED());
            ps.setString(3, modelo.getCOMMED());
            ps.setDouble(4, modelo.getPRECMED());
            ps.setDate(5, UtilToSql.convertDate(modelo.getFVMED()));
            ps.setInt(6, modelo.getSTOCMED());
            ps.setString(7, modelo.getLOTMED());
            ps.setString(8, modelo.getIDPROV());
            ps.setInt(9, modelo.getIDMED());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en modificar MedicamentoImpl" + e.getMessage());
        }
    }

    public void eliminar(Medicamento modelo) throws Exception {
        String sql = "DELETE FROM MEDICAMENTO WHERE IDMED=?";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getIDMED());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en ELIMINAR MedicamentoImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public void desactivar(Medicamento modelo) throws Exception {
        String sql = "UPDATE MEDICAMENTO SET ESTMED=? WHERE IDMED=? ";
        try {
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDMED());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("Error en DESACTIVAR MedicamentoImpl" + e.getMessage());
        } finally {
            this.cerrar();
        }
    }

    public List<Medicamento> listar(int Listado) throws Exception {
        List<Medicamento> listado = null;
        Medicamento medi;
        String sql = "";
        switch (Listado) {
            case 1:
                sql = "SELECT * FROM MEDICAMENTO WHERE ESTMED = 'A' ORDER BY IDMED DESC";
                break;
            case 2:
                sql = "SELECT * FROM MEDICAMENTO WHERE ESTMED = 'I' ORDER BY IDMED DESC";
                break;
            case 3:
                sql = "SELECT * FROM MEDICAMENTO ORDER BY IDMED DESC";
                break;
        }
        try {
            listado = new ArrayList();
            Statement st = this.conectar().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                medi = new Medicamento();
                medi.setIDMED(rs.getInt("IDMED"));
                medi.setPRESMED(rs.getString("PRESMED"));
                medi.setGENMED(rs.getString("GENMED"));
                medi.setCOMMED(rs.getString("COMMED"));
                medi.setPRECMED(rs.getDouble("PRECMED"));
                medi.setFVMED(rs.getDate("FVMED"));
                medi.setSTOCMED(rs.getInt("STOCMED"));
                medi.setLOTMED(rs.getString("LOTMED"));
                medi.setIDPROV(rs.getString("IDPROV"));
                listado.add(medi);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            System.out.println("Error en listar MedicamentoImpl: " + e.getMessage());
        } finally {
            this.cerrar();
        }
        return listado;
    }

    public boolean existePresentación(Medicamento modelo, List<Medicamento> listaModelo) {
        for (Medicamento med : listaModelo) {
            if (modelo.getPRESMED().equals(med.getPRESMED())) {
                return true;
            }
        }
        return false;
    }

    public boolean existeGenerico(Medicamento modelo, List<Medicamento> listaModelo) {
        for (Medicamento med : listaModelo) {
            if (modelo.getGENMED().equals(med.getGENMED())) {
                return true;
            }
        }
        return false;
    }

    public boolean existeLote(Medicamento modelo, List<Medicamento> listaModelo) {
        for (Medicamento med : listaModelo) {
            if (modelo.getLOTMED().equals(med.getLOTMED())) {
                return true;
            }
        }
        return false;
    }

    public List<String> autocompleteProveedor(String consulta) throws SQLException, Exception {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT TOP 20 CONCAT(NOMPROV, ', ', RUCPROV, ', ',TIPPROV) AS PROVEEDORDESC FROM PROVEEDOR WHERE NOMPROV LIKE ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, "%" + consulta + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString("PROVEEDORDESC"));
            }
        } catch (Exception e) {
            System.out.println("Error en autocompleteProveedor" + e.getMessage());
        }
        return lista;
    }

    public String obtenerCodigoProveedor(String cadenaPro) throws SQLException, Exception {
        String sql = "SELECT IDPROV FROM PROVEEDOR WHERE CONCAT(NOMPROV, ', ', RUCPROV, ', ',TIPPROV) = ?";
        try {
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, cadenaPro);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString("IDPROV");
            }
            return rs.getString("IDPROV");
        } catch (Exception e) {
            System.out.println("Error en obtenerCodigoProveedor " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Medicamento> listar() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
