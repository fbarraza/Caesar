using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _03_InfoLlibre
{
    public partial class Form1 : Form
    {
        Llibre[] llibreglobal = new Llibre[2];
        public Form1()
        {
            InitializeComponent();
        }

        private void bBack_Click(object sender, EventArgs e)
        {
            if (llibreglobal.Length <= 0)
            {
                bBack.Enabled = false;
            }

            /**
             * Llibre l = new Llibre("La Ceremonia del Té",80,"14x7","Kakuzo Okakura","Negro",2006,"Japón"); //ENCAPUSULACIÓN DE DATOS, TODOS LOS DATOS SE GUARDAN EN LA VARIABLE "l".
            lTitol.Text = l.getTitol(); //Label.Tipodedato = Variable-encapsulada-de-Constructor-inicializado-con-parámetros.métodoGet;
            lPag.Text = Convert.ToString(l.getPag());
            lDim.Text = l.getDim();
            lAutor.Text = l.getTitol();
            lColorPortada.Text = l.getColor();
            lAny.Text = Convert.ToString(l.getAny());
            lContingut.Text = l.getContingut();
            
            <!-- Obtención de valor del objeto mediante constructor. -->
             */
        }

        private void extreuinformacio(int num)
        {
            lTitol.Text = llibreglobal[num].Titol;
            lPag.Text = Convert.ToString(llibreglobal[num].Pag);
            lDim.Text = llibreglobal[num].Dim;
            lAutor.Text = llibreglobal[num].Autor;
            lColorPortada.Text = llibreglobal[num].Color;
            lAny.Text = Convert.ToString(llibreglobal[num].Any);
            lContingut.Text = llibreglobal[num].Contingut;
        }
        
        private void bForward_Click(object sender, EventArgs e)
        {
            Llibre l1 = new Llibre("Windows 10 for Dummies", 240, "250x190", "Dan Forden", "Amarillo", 2015, "Sistemas Operativos"); //ENCAPUSULACIÓN DE DATOS, TODOS LOS DATOS SE GUARDAN EN LA VARIABLE "l".
            llibreglobal[1] = l1;
            
            /*
            lTitol.Text = r.getTitol(); //Label.Tipodedato = Variable-encapsulada-de-Constructor-inicializado-con-parámetros.métodoGet;
            lPag.Text = Convert.ToString(r.getPag());
            lDim.Text = r.getDim();
            lAutor.Text = r.getTitol();
            lColorPortada.Text = r.getColor();
            lAny.Text = Convert.ToString(r.getAny());
            lContingut.Text = r.getContingut();
            */
        }

        private void bGuarda_Click(object sender, EventArgs e)
        {
            String titol, dim, autor, color, contingut;
            int pag, any;
            
            titol = TBtitol.Text;
            dim = TBdimensions.Text;
            autor = TBautor.Text;
            color = TBcolor.Text;
            contingut = TBcontingut.Text;
            pag = Convert.ToInt32(TBpag.Text);
            any = Convert.ToInt32(TBany.Text);

            Llibre l = new Llibre(titol, pag, dim, autor, color, any, contingut); //Se crean primero las variables y se asocian, luego se pasan hacia el constructor.
            //Constructor = SEGUIR EL ORDEN ESCUETO TAL Y COMO SE CREÓ ANTERIORMENTE!!

            llibreglobal[0] = l;
        }
    }
        //SE PUEDE HACER TODO DE FORMA AUTOMÁTICA, PERO CUIDADO CON LOS FLUJOS.
}
