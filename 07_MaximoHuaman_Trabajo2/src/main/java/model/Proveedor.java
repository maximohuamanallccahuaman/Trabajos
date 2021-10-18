package model;

public class Proveedor {
    
    private int ide;
    private String nombre, ruc, telefono, email, tipo, ncomercial, abreviatura, direccion, ubigeo, estado;


    public Proveedor() {
    }

    public Proveedor(int ide, String nombre, String ruc, String telefono, String email, String tipo, String ncomercial, String abreviatura, String direccion, String ubigeo, String estado) {
        this.ide = ide;
        this.nombre = nombre;
        this.ruc = ruc;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.ncomercial = ncomercial;
        this.abreviatura = abreviatura;
        this.direccion = direccion;
        this.ubigeo = ubigeo;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "ide=" + ide + ", nombre=" + nombre + ", ruc=" + ruc + ", telefono=" + telefono + ", email=" + email + ", tipo=" + tipo + ", ncomercial=" + ncomercial + ", abreviatura=" + abreviatura + ", direccion=" + direccion + ", ubigeo=" + ubigeo + ", estado=" + estado + '}';
    }

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNcomercial() {
        return ncomercial;
    }

    public void setNcomercial(String ncomercial) {
        this.ncomercial = ncomercial;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    


    
  
}
