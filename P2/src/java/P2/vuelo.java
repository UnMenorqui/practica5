package P2;



public class vuelo {
    private Integer id_vuelo;
    private String compania;
    private String origen;
    private String hora_salida;
    private String destino;
    private String hora_llegada;
    
    public vuelo (Integer id_vuelo, String compania, String origen, String hora_salida, String destino, String hora_llegada) {
        this.id_vuelo = id_vuelo;
        this.compania = compania;
        this.origen = origen;
        this.hora_salida = hora_salida;
        this.destino = destino;
        this.hora_llegada = hora_llegada;
    }
    
    public Integer getIdvuelo() {return id_vuelo;}
    
    public String getCompania() {return compania;}
    
    public String getOrigen() {return origen;}
    
    public String getHoraSalida() {return hora_salida;}
    
    public String getDestino() {return destino;}
    
    public String getHoraLlegada() {return hora_llegada;}
    
    public void setIdVuelo(Integer id_vuelo) {this.id_vuelo = id_vuelo;}
    
    public void setCompania(String compania) {this.compania = compania;}
    
    public void setOrigen(String origen) {this.origen = origen;}
    
    public void setHoraSalida(String hora_salida) {this.hora_salida = hora_salida;}
    
    public void setDestino(String destino) {this.destino = destino;}
    
    public void setHoraLlegada(String hora_llegada) {this.hora_llegada = hora_llegada;}
    
}
