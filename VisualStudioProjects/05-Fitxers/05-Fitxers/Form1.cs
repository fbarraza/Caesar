using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _05_Fitxers
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        public void buidaCamps()
        {
            tbTitol.Text = "";
            tbPag.Text = "";
            tbDim.Text = "";
            tbAutor.Text = "";
            tbColor.Text = "";
            tbAny.Text = "";
            tbContingut.Text = "";
        }

        private void btSave_Click(object sender, EventArgs e)
        {
            buidaCamps();

            String titol, dim, autor, color, contingut;
            int pag, any;

            titol = tbTitol.Text;
            pag = Convert.ToInt32(tbPag.Text);
            dim = tbDim.Text;
            autor = tbAutor.Text;
            color = tbColor.Text;
            any = Convert.ToInt32(tbAny.Text);
            contingut = tbContingut.Text;

            FitxerLlibre lli = new FitxerLlibre(titol, pag, dim, autor, color, any, contingut);
            
            lli.escriuObjecteEnFitxerText(lli);
        }

        private void BtShow_Click(object sender, EventArgs e)
        {
            String titol, dim, autor, color, contingut;
            int pag, any;

            titol = tbTitol.Text;
            pag = Convert.ToInt32(tbPag.Text);
            dim = tbDim.Text;
            autor = tbAutor.Text;
            color = tbColor.Text;
            any = Convert.ToInt32(tbAny.Text);
            contingut = tbContingut.Text;
            
            FitxerLlibre lli = new FitxerLlibre(titol, pag, dim, autor, color, any, contingut);
        }

        private void mostraLlibre(FitxerLlibre[] lli)
        {
            int i;
            int maxLlibre = contaVector(lli);
            for (i = 0; i < lli.Length /* [100] */; i++)
            {
                rtbPantalla.Text = rtbPantalla.Text + "\nTitol: " + lli[i].GetTitol() + "\nPàgines: " + lli[i].GetPag() + "\nDimensions: " + lli[i].GetDim() + "\nAutor: " + lli[i].GetAutor() + "\nColor: " + lli[i].GetColor() + "\nAny: " + lli[i].GetAny() + "\nContingut: " + lli[i].GetContingut();
            }
        }

        public int contaVector(Object[] obj) //POLIMORFISMO, Un array de tipo Objeto se convierte a Array de Objeto estándar.
        { //POLIMORFISMO: FitxerLlibre[] lli --> Object[] obj //--// Digamos que un Objeto se adapta o convierte a otro "Objeto".
            int i = 0;
            do
            {
                if (obj[i] != null) //Todos los libros dentro del vector que NO SEAN NULOS
                    i++;
                else
                    return i;
            } while (i < obj.Length);
            return i; //Return obligatorio, aceptará el de arriba.
        }
    }
}