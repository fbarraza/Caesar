package Dades;

public class ModelHistoric {

    private int IDH;
    private int IDC;
    private int IDV;
    private String DataAlta;
    private String DataBaixa;

    public ModelHistoric() {

    }
    
    public ModelHistoric(int IDH, int IDC, int IDV, String DataAlta, String DataBaixa) {
        this.IDH = IDH;
        this.IDC = IDC;
        this.IDV = IDV;
        this.DataAlta = DataAlta;
        this.DataBaixa = DataBaixa;
    }

    public int getIDH() {
        return IDH;
    }

    public void setIDH(int IDH) {
        this.IDH = IDH;
    }

    public int getIDC() {
        return IDC;
    }

    public void setIDC(int IDC) {
        this.IDC = IDC;
    }

    public int getIDV() {
        return IDV;
    }

    public void setIDV(int IDV) {
        this.IDV = IDV;
    }

    public String getDataAlta() {
        return DataAlta;
    }

    public void setDataAlta(String DataAlta) {
        this.DataAlta = DataAlta;
    }

    public String getDataBaixa() {
        return DataBaixa;
    }

    public void setDataBaixa(String DataBaixa) {
        this.DataBaixa = DataBaixa;
    }
    
}
