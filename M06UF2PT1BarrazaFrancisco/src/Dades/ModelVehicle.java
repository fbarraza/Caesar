package Dades;

public class ModelVehicle {
    
    private int IDVe;
    private String Matricula;
    private String Nmotor;
    private String NBastidor;
    private String DataAlta;
    private String TipusBaixa;
    private String DataBaixa;

    public ModelVehicle() {

    }

    public ModelVehicle(int IDVe, String Matricula, String Nmotor, String NBastidor, String DataAlta, String TipusBaixa, String DataBaixa) {
        this.IDVe = IDVe;
        this.Matricula = Matricula;
        this.Nmotor = Nmotor;
        this.NBastidor = NBastidor;
        this.DataAlta = DataAlta;
        this.TipusBaixa = TipusBaixa;
        this.DataBaixa = DataBaixa;
    }

    public int getIDVe() {
        return IDVe;
    }

    public void setIDVe(int IDVe) {
        this.IDVe = IDVe;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String Matricula) {
        this.Matricula = Matricula;
    }

    public String getNmotor() {
        return Nmotor;
    }

    public void setNmotor(String Nmotor) {
        this.Nmotor = Nmotor;
    }

    public String getNBastidor() {
        return NBastidor;
    }

    public void setNBastidor(String NBastidor) {
        this.NBastidor = NBastidor;
    }

    public String getDataAlta() {
        return DataAlta;
    }

    public void setDataAlta(String DataAlta) {
        this.DataAlta = DataAlta;
    }

    public String getTipusBaixa() {
        return TipusBaixa;
    }

    public void setTipusBaixa(String TipusBaixa) {
        this.TipusBaixa = TipusBaixa;
    }

    public String getDataBaixa() {
        return DataBaixa;
    }

    public void setDataBaixa(String DataBaixa) {
        this.DataBaixa = DataBaixa;
    }   
}
