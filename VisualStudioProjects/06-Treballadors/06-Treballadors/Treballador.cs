using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _06_Treballadors
{
    [Serializable]
    public class Treballador
    {
        private String nom;
        private char genere;
        private double salari;

        public Treballador()
        {
            
        }

        public Treballador(String nom, char genere, double salari)
        {
            this.nom = nom;
            this.genere = genere;
            this.salari = salari;
        }

        public String GetNom(String nom)
        {
            return nom;
        }
        public char GetGenere(char genere)
        {
            return genere;
        }
        public double GetSalari(double salari)
        {
            return salari;
        }
        
        public Treballador[] llegirObjecteFitder(String fitxer = "fitxer/treballadors.dat")
        {
            Stream str = File.Open(fitxer, FileMode.Open);


        }

        public Treballador[] llegeixObjecteFitxer(String arxiu = "fitxer/treballadors.dat")
        {
            Stream str = File.Open(arxiu, FileMode.Open);
            var formatter = new System.Runtime.Serialization.Formatters.Binary.BinaryFormatter(); //Tratamiento en formato binario
            Treballador[] tre = new Treballador[100];
            int q = 0;
           tre[q] = new Treballador[100];
            do
            {
                try
                {
                    tre[q] = (Treballador)formatter.Deserialize(str);
                }
                catch
                {
                    MessageBox.Show("Error al llegir el fitxer d'Objectes");
                }
                q++;
            } while (tre[q - 1] != null);
            str.Close();
            return tre;
        }







    }
}
