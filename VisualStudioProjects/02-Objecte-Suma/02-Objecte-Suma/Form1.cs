using _02_Objecte_Suma.Class;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _02_Objecte_Suma
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void bSuma_Click(object sender, EventArgs e)
        {
            int n1, n2, resultat; //Crear variables
            Calcular s = new Calcular(); //Inicializar el constructor
            n1 = Convert.ToInt32(TB1.Text); //Convertir String a número
            n2 = Convert.ToInt32(TB2.Text); //Convertir String a número
            resultat = s.Suma(n1, n2); //resultado = objeto.Método(pasarvariablecreada1,pasarvariablecreada2);
            LBpantalla.Text = Convert.ToString(resultat); //Mostrar resultado.
        }

        private void bResta_Click(object sender, EventArgs e)
        {
            int n1, n2, resultat; //Crear variables
            Calcular s = new Calcular(); //Inicializar el constructor
            n1 = Convert.ToInt32(TB1.Text); //Convertir String a número
            n2 = Convert.ToInt32(TB2.Text); //Convertir String a número
            resultat = s.Suma(n1, n2); //resultado = objeto.Método(pasarvariablecreada1,pasarvariablecreada2);
            LBpantalla.Text = Convert.ToString(resultat); //Mostrar resultado.
        }

        private void LBpantalla_Click(object sender, EventArgs e)
        {
            



        }
    }
}
