using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _04_Alumne_Array
{
    public partial class Form : System.Windows.Forms.Form
    {
        static int maxVector = 10;
        int numAlumne = 0;
        AlumneArray[] al = new AlumneArray[maxVector];

        public Form()
        {
            InitializeComponent();
        }

        public void reiniciarCamps()
        {
            tbNom.Text = "";
            tbCog1.Text = "";
            tbCog2.Text = "";
            cbEdat.Text = "";
            cbNota.Text = "";
        }

        private void BtSave_Click(object sender, EventArgs e)
        {
            String nom, cog1, cog2;
            int nota, edat;

            nom = tbNom.Text;
            cog1 = tbCog1.Text;
            cog2 = tbCog2.Text;

            edat = Convert.ToInt32(cbEdat.SelectedItem);
            nota = Convert.ToInt32(cbNota.SelectedItem);

            al[numAlumne] = new AlumneArray(nom,cog1,cog2,edat,nota);
            numAlumne++; //Suma uno al vector

            //BUIDAR COMBOBOX
            reiniciarCamps();

            lbContador.Text = numAlumne + " / " + maxVector;
            btShow.Enabled = true;

            if (numAlumne >= maxVector)
                btSave.Enabled = false;
        }

        private void BtShow_Click(object sender, EventArgs e)
        {
            int i;
            rtbPantalla.Text = "";
            for (i = 0; i < numAlumne; i++)
            {
                rtbPantalla.Text = rtbPantalla.Text + "\nNom:" + al[i].GetNom() + "\nCognoms:" + al[i].GetCog1() + " " + al[i].GetCog2() + "\nEdat:" + al[i].GetEdat() + "\nNota:" + al[i].GetNota() + "\n";
            }


        }
    }
}
