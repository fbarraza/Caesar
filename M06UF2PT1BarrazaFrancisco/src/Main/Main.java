package Main;

import Connexio.Connexio;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;
import Dades.ModelContribuent;
import Dades.ModelVehicle;
import Dades.ModelHistoric;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String args[]) throws SQLException, ClassNotFoundException, Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Què vols fer?\n C de consulta, I de inserció, o M de Modificació?\n");
        char mod = s.next().charAt(0);

        Connection con = connect();

        switch (mod) {
            case 'C':
                select(con);
                tancar(con);
                break;
            case 'I':
                insert(con);
                tancar(con);
                break;
            case 'M':
                update(connect());
                tancar(con);
                break;
            default:
                tancar(con);
        }

    }

    private static Connection connect() throws SQLException, ClassNotFoundException, Exception {

        Connexio cc = new Connexio();
        Connection con = cc.ConnectionJDBC();

        return con;
    }

    private static void tancar(Connection con) throws SQLException {
        con.close();
    }

    private static void select(Connection con) throws SQLException {

        Statement stm = null;
        ResultSet rst = null;
        PreparedStatement pst = null;
        stm = con.createStatement();
        

        Scanner eleccio = new Scanner(System.in);
        System.out.println("Quina taula vols consultar?\n'contribuent', 'vehicle' o 'historic'?");
        String taula = eleccio.nextLine();

        switch (taula) {
            case "contribuent":
                ModelContribuent mc = new ModelContribuent();
                pst = con.prepareStatement("SELECT * from "+taula);
                rst = pst.executeQuery();
                
                while (rst.next()) {
                    int IDCon = rst.getInt(mc.getIDCon());
                    String NIF = rst.getString(String.valueOf(mc.getNIF()));
                    String Nom = rst.getString(mc.getNom());
                    String Domicili = rst.getString(mc.getDomicili());
                    System.out.println("IDCon: " + IDCon + ". NIF: " + NIF + ". Nom: " + Nom + ". Domicili: " + Domicili);
                }
                rst.close();
                stm.close();
                break;
            case "vehicle":
                ModelVehicle mv = new ModelVehicle();
                pst = con.prepareStatement("Select * from "+taula);
                rst = pst.executeQuery();
                while (rst.next()) {
                    int IDVe = rst.getInt(mv.getIDVe());
                    String Matricula = rst.getString(mv.getMatricula());
                    String Nmotor = rst.getString(mv.getNmotor());
                    String Nbastidor = rst.getString(mv.getNBastidor());
                    Date DataAlta = rst.getDate(mv.getDataAlta());
                    String TipusBaixa = rst.getString(mv.getTipusBaixa());
                    Date DataBaixa = rst.getDate(mv.getDataBaixa());
                    System.out.println("IDVe: " + IDVe + ". Matricula: " + Matricula + ". Motor: " + Nmotor + ". Bastidor: " + Nbastidor + ". Data d'alta: " + DataAlta + ". Tipus de baixa: " + TipusBaixa + ". Data de baixa: " + DataBaixa);
                }
                rst.close();
                stm.close();
                break;
            case "historic":
                ModelHistoric mh = new ModelHistoric();
                pst = con.prepareStatement("Select * from "+taula);
                rst = pst.executeQuery();
                while (rst.next()) {
                    int IDH = rst.getInt(mh.getIDH());
                    int IDC = rst.getInt(mh.getIDC());
                    int IDV = rst.getInt(mh.getIDV());
                    Date DataAlta = rst.getDate(mh.getDataAlta());
                    Date DataBaixa = rst.getDate(mh.getDataBaixa());

                    System.out.println("IDH: " + IDH + ". IDC: " + IDC + ". IDV: " + IDV + ". Data d'alta: " + DataAlta + ". Data de baixa: " + DataBaixa);
                }
                rst.close();
                stm.close();
                break;
            default:
                System.out.println("\nCUIDÁO.\n");
        }
    }

    private static void insert(Connection con) throws SQLException {
        ResultSet rst = null;
        Scanner t = new Scanner(System.in);

        Scanner eleccio = new Scanner(System.in);
        System.out.println("Quina en quina taula vols inserir dades?\n 'contribuent', 'vehicle' o 'historic'?\n");
        String taula = eleccio.nextLine();

        switch (taula) {
            case "contribuent":
                PreparedStatement psc = con.prepareStatement("INSERT INTO " + taula + " VALUES(null,?,?,?)");
                System.out.println("Introdueixi el nom: \n");
                psc.setString(1, t.nextLine());
                System.out.println("Introdueixi el NIF: \n");
                psc.setString(2, t.nextLine());
                System.out.println("Introdueixi el Domicili: \n");
                psc.setString(3, t.nextLine());
                System.out.println("Dades inserides correctament");
                psc.close();
                break;
            case "vehicle":
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate avui = LocalDate.now();

                PreparedStatement psv = con.prepareStatement("INSERT INTO " + taula + " VALUES(null,?,?,?,?,?,?)");
                System.out.println("Introdueixi la matrícula: \n");
                psv.setString(1, t.nextLine());
                System.out.println("Introdueixi el número de motor: \n");
                psv.setString(2, t.nextLine());
                System.out.println("Introdueixi el bastidor: \n");
                psv.setString(3, t.nextLine());
                System.out.println("Introdueixi la data d'alta: \n");
                psv.setString(4, t.nextLine());
                System.out.println("Introdueixi el tipus de baixa: \n");
                if (t.nextLine().equals("BD") || t.nextLine().equals("BT") || t.nextLine().equals("")) {
                    psv.setString(5, t.nextLine());
                } else {
                    System.err.println("Ni se t'acudeixi...");
                }
                System.out.println("Introdueixi la data de baixa: \n");
                psv.setString(6, t.nextLine());
                System.out.println("Dades inserides correctament");
                psv.close();
                break;
            case "historic":
                ModelVehicle mv2 = new ModelVehicle();
                String DataAlta = rst.getString(mv2.getDataAlta());
                String DataBaixa = rst.getString(mv2.getDataBaixa());
                PreparedStatement psh = con.prepareStatement("INSERT INTO " + taula + " VALUES(null,null,null,?,?,?,?)");
                System.out.println("Introdueixi la data d'alta: \n");
                psh.setString(1, DataAlta);
                System.out.println("Introdueixi la data de baixa: \n");
                psh.setString(2, DataBaixa);
                System.out.println("Dades inserides correctament");
                psh.close();
                break;
            default:
                System.err.println("Taula? On?");
        }
    }

    private static void update(Connection con) throws SQLException {
        ResultSet rst = null;
        Statement stm = null;

        Scanner t = new Scanner(System.in);
        //System.out.println("Number of rows: " + count);
        //int quants = stm.executeUpdate("INSERT INTO ....");

        stm = con.createStatement();
        // Result set get the result of the SQL query
        rst = stm.executeQuery("select * from contribuent");

        ModelHistoric mh = new ModelHistoric();

        Scanner eleccio = new Scanner(System.in);
        System.out.println("Quina en quina taula vols inserir dades?\n 'contribuent', 'vehicle' o 'historic'?\n");
        String taula = eleccio.nextLine();

        switch (taula) {
            case "contribuent":
                PreparedStatement psc = con.prepareStatement("UPDATE TABLE " + taula + " SET  ");
                System.out.println("Introdueixi el nom: \n");
                psc.setString(1, t.nextLine());
                System.out.println("Introdueixi el NIF: \n");
                psc.setString(2, t.nextLine());
                System.out.println("Introdueixi el Domicili: \n");
                psc.setString(3, t.nextLine());
                System.out.println("Dades inserides correctament");
                psc.close();
                break;
            case "vehicle":
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate avui = LocalDate.now();

                PreparedStatement psv = con.prepareStatement("INSERT INTO " + taula + " VALUES(null,?,?,?,?,?,?)");
                System.out.println("Introdueixi la matrícula: \n");
                psv.setString(1, t.nextLine());
                System.out.println("Introdueixi el número de motor: \n");
                psv.setString(2, t.nextLine());
                System.out.println("Introdueixi el bastidor: \n");
                psv.setString(3, t.nextLine());
                System.out.println("Introdueixi la data d'alta: \n");
                psv.setString(4, t.nextLine());
                System.out.println("Introdueixi el tipus de baixa: \n");
                if (t.nextLine().equals("BD") || t.nextLine().equals("BT") || t.nextLine().equals("")) {
                    psv.setString(5, t.nextLine());
                } else {
                    System.err.println("Ni se t'acudeixi...");
                }
                System.out.println("Introdueixi la data de baixa: \n");
                psv.setString(6, t.nextLine());
                System.out.println("Dades inserides correctament");
                psv.close();
                break;
            case "historic":
                ModelVehicle mv2 = new ModelVehicle();
                String DataAlta = rst.getString(mv2.getDataAlta());
                String DataBaixa = rst.getString(mv2.getDataBaixa());
                PreparedStatement psh = con.prepareStatement("UPDATE TABLE " + taula + " ");
                System.out.println("Introdueixi la data d'alta: \n");
                psh.setString(1, DataAlta);
                System.out.println("Introdueixi la data de baixa: \n");
                psh.setString(2, DataBaixa);
                System.out.println("Dades inserides correctament");
                psh.close();
                break;
            default:
                System.err.println("Taula? On?");
        }
        rst.close();
        stm.close();
    }
}
