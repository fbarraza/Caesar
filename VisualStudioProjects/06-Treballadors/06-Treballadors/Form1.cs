using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _06_Treballadors
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btGuarda_Click(object sender, EventArgs e)
        {
            String nom;
            char genere;
            double salari;

            nom = tbNom.Text;
            genere = Convert.ToChar(cbGen.SelectedItem);
            salari = Convert.ToDouble(tbSalari.Text);
            
            Treballador tre = new Treballador(nom,genere,salari);

            tre.escriuObjecteFitxer();

            tbNom.Text = "";
            cbGen.SelectedItem = "";
            tbSalari.Text = "";
        }

        private void btMostra_Click(object sender, EventArgs e)
        {
            Treballador[] tre = new Treballador[100];
            Treballador t = new Treballador();
            rtbPantalla.Text = "";

            tre = t.llegirObjecteFitxer();
            Mostra(tre);
        }

        public void escriuObjecteFitxer(String fitxer = "fitxer/treballadors.dat", bool afegir = true)
        {


        }

        public void llegirObjecteFitxer(String fitxer = "fitxer/treballadors.dat")
        {


        }

        private int contaTreb(Treballador[] t)
        {
            
            int i = 0;
            //for (int i=0;i<t.Length;i++)
            do
            {
                if (t[i] == null)
                    return i;
                i++;
            } while (i < t.Length);

            return i;

        }
        public void Mostra(Treballador treb)
        {
            int maxTreb = contaTreb(treb);
            for (int i = 0; i < maxTreb; i++)
            {
                rtbPantalla.Text = rtbPantalla.Text + "\nNom: " + treb[i].GetNom + "\nGènere: " + treb[i].GetGenere + "\nSalari: " + treb[i].Salari + "\n";
            }
        }

        private void btSouGran_Click(object sender, EventArgs e)
        {
            int i = 0;
            Treballador maxTreb = new Treballador();
            Treballador[] treObj = new Treballador[100];

            treObj = maxTreb.llegirObjectefitxer();
            int totalTreballadors = contraTreb(treObj);

            maxTreb = treObj[0];

            for (i = 0; i < totalTreballadors; i++)
            {
                if (treObj[i].GetSalari > maxTreb.Salari)
                    maxTreb = treObj[i];
            }
            rtbpantalla.Text = "\n El treballador amb més sou és: " + maxTreb;
            Mostra(maxTreb);
        }
        
    }
}
