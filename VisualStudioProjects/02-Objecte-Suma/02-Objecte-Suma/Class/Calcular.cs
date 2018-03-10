using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _02_Objecte_Suma.Class
{
    class Calcular
    {
        //private int suma;
        //private int resta;
        private int resultat; //variable de la classe (propietat)

        public Calcular() //Mètode sense paràmetres, PER DEFECTE, SEMPRE
        {
        }

        public Calcular(int resultat)
        {
            this.resultat = resultat;
        }

        public int Suma(int TB1, int TB2)
        {
            resultat = TB1 + TB2;
            return resultat;
        }

        public int Resta(int TB1, int TB2)
        {
            resultat = TB1 - TB2;
            return resultat;
        }
    }
}
