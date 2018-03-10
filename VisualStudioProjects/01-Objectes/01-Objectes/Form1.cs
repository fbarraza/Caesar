using _01_Objectes.Classes;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _01_Objectes
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void BOK_Click(object sender, EventArgs e)
        {
            Llibre l = new Llibre(); //Objecte
            Llibre l2 = new Llibre("adlka","adsand");
            Llibre l3 = new Llibre("3","");
            Llibre l4 = new Llibre();
            
            /**
             * l.propietat (i apareixen totes les propietats públiques, les private NO). 
             */
        }
    }
}
