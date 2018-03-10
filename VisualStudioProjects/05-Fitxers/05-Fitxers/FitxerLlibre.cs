using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _05_Fitxers
{
    [Serializable]
    public class FitxerLlibre
    {
        private String Titol;
        private int Pag;
        private String Dim;
        private String Autor;
        private String Color;
        private int Any;
        private String Contingut;

        public FitxerLlibre(string titol, int pag, string dim, string autor, string color, int any, string contingut)
        {
            Titol = titol;
            Pag = pag;
            Dim = dim;
            Autor = autor;
            Color = color;
            Any = any;
            Contingut = contingut;
        }

        public string GetTitol()
        {
            return Titol;
        }

        public void SetTitol(string value)
        {
        }

        public int GetPag()
        {
            return Pag;
        }

        public void SetPag(int value)
        {
            
        }

        public string GetDim()
        {
            return Dim;
        }

        public void SetDim(string value)
        {
        }

        public string GetAutor()
        {
            return Autor;
        }

        public void SetAutor(string value)
        {
        }

        public string GetColor()
        {
            return Color;
        }

        public void SetColor(string value)
        {
        }

        public int GetAny()
        {
            return Any;
        }

        public void SetAny(int value)
        {
        }

        public string GetContingut()
        {
            return Contingut;
        }

        public void SetContingut(string value)
        {
        }

        public void escriuTextFitxer(String text, String fitxer = "fitxer/fitxer.txt", bool afegir = true)
        {
            try
            {
                StreamWriter arxiu = new StreamWriter(@fitxer, afegir);
                arxiu.Write(text);
                arxiu.Close();
            }
            catch (FileNotFoundException fnfe)
            {
                MessageBox.Show("No s'ha trobat el fitxer", "error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        public void escriuObjecteEnFitxerText(FitxerLlibre lli)
        {
            String linia;
            linia = lli.Titol + ";" + lli.Pag + ";" + lli.Dim + ";" + lli.Autor + ";" + lli.Color + ";" + lli.Any + ";" + lli.Contingut;

            escriuTextFitxer(linia);
        }

        public FitxerLlibre[] llegeixTextFitxer(String fitxer = "fitxer/fitxer.txt")
        {
            StreamReader arxiu = new StreamReader(fitxer);
            String linia;
            String[] dades = new String[7];

            FitxerLlibre[] lli = new FitxerLlibre[100/* lli.Length */];
            int i = 0;

            while ((linia = arxiu.ReadLine()) != null)
            {
                dades = linia.Split(';');
                lli[i] = new FitxerLlibre(dades[0], Convert.ToInt32(dades[1]), dades[2], dades[3], dades[4], Convert.ToInt32(dades[5]), dades[6]);
                i++;
            }
            arxiu.Close();
            return lli;
        }

        public void escriuObjecteFitxerObjectStream(FitxerLlibre lli, String fitxer = "fitxer/fitxer.dat", bool afegir = true)
        {
            Stream str = File.Open(fitxer, FileMode.Append); //Añade
            var formatter = new System.Runtime.Serialization.Formatters.Binary.BinaryFormatter(); //Tratamiento en formato binario
            formatter.Serialize(str,lli); //Serialize(str,lli): Guarda en fichero.
            str.Close();
        }

        public FitxerLlibre[] llegeixObjecteFitxer(String arxiu = "fitxer/fitxer.dat")
        {
            Stream str = File.Open(arxiu, FileMode.Open);
            var formatter = new System.Runtime.Serialization.Formatters.Binary.BinaryFormatter(); //Tratamiento en formato binario
            int q = 0;
            FitxerLlibre[] lli = new FitxerLlibre[100];
            do
            {
                try
                {
                    lli[q] = (FitxerLlibre)formatter.Deserialize(str);
                }
                catch
                {

                }
                q++;
            } while (lli[q - 1] != null);
            str.Close();
            return lli;
        }


    }
}
