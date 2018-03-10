using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _03_Llibre
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void btGuardar_Click(object sender, EventArgs e)
        {
            String titulo, autor, color, genero, dimensiones, contenido;
            int paginas;

            titulo = tbTitol.Text;
            paginas = Convert.ToInt32(tbPag.Text);
            autor = tbAut.Text;
            color = tbCol.Text;
            genero = tbGen.Text;
            dimensiones = tbDim.Text;
            contenido = tbCon.Text;
            
            Llibre lli = new Llibre(titulo,paginas,autor,color,genero,dimensiones,contenido);


        }

        private void btMostrar_Click(object sender, EventArgs e)
        {

        }
    }
}
