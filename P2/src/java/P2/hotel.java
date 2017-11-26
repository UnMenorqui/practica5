package P2;



public class hotel {
    private Integer id_hotel;
    private String nombrehotel;
    private String cadenahotelera;
    private String calle;
    private Integer numero;
    private Integer codigopostal;
    private String ciudad;
    private String provincia;
    private String pais;
    private Integer num_habitaciones;
    
    public hotel (Integer id_hotel, String nombrehotel, String cadenahotelera, String calle, Integer numero, Integer codigopostal, String ciudad, String provincia, String pais, Integer num_habitaciones) {
        this.id_hotel = id_hotel;
        this.nombrehotel = nombrehotel;
        this.cadenahotelera = cadenahotelera;
        this.calle = calle;
        this.numero = numero;
        this.codigopostal = codigopostal;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.pais = pais;
        this.num_habitaciones = num_habitaciones;
    }
    
    public Integer getIdhotel() {return id_hotel;}
    
    public String getNombrehotel() {return nombrehotel;}
    
    public String getCadenaHotelera() {return cadenahotelera;}
    
    public String getCalle() {return calle;}
    
    public Integer getNumero() {return numero;}
    
    public Integer getCodigoPostal() {return codigopostal;}
    
    public String getCiudad() {return ciudad;}
    
    public String getProvincia() {return provincia;}
    
    public String getPais() {return pais;}
    
    public Integer getNum_Habitaciones() {return num_habitaciones;}
    
    public void setIdHotel(Integer id_hotel) {this.id_hotel = id_hotel;}
    
    public void setNombreHotel(String nombrehotel) {this.nombrehotel = nombrehotel;}
    
    public void setCadenaHotelera(String cadenahotelera) {this.cadenahotelera = cadenahotelera;}
    
    public void setCalle (String calle) {this.calle = calle;}
    
    public void setNumero (Integer numero) {this.numero = numero;}
    
    public void setCodigoPostal (String codigopostal) {this.calle = codigopostal;}
    
    public void setCiudad (String ciudad) {this.ciudad = ciudad;}
    
    public void setProvincia (String provincia) {this.calle = provincia;}
    
    public void setPais(String pais) {this.pais = pais;}
    
    public void setNum_Habitaciones(Integer num_habitaciones) {this.num_habitaciones = num_habitaciones;}
    
}
