using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04_Alumne_Array
{
    class AlumneArray
    {
        private String nom;
        private String cog1;
        private String cog2;
        private int edat;
        private int nota;

        public AlumneArray(string nom, string cog1, string cog2, int edat, int nota)
        {
            this.SetNom(nom);
            this.SetCog1(cog1);
            this.SetCog2(cog2);
            this.SetEdat(edat);
            this.SetNota(nota);
        }

        public string GetNom()
        {
            return nom;   
        }

        public void SetNom(string value)
        {
        }

        public string GetCog1()
        {
            return cog1;
        }

        public void SetCog1(string value)
        {
        }

        public string GetCog2()
        {
            return cog2;
        }

        public void SetCog2(string value)
        {
        }

        public int GetEdat()
        {
            return edat;
        }

        public void SetEdat(int value)
        {
        }

        public int GetNota()
        {
            return nota;
        }

        public void SetNota(int value)
        {
        }




        //public String getNom()
        //{
        //    return nom;
        //}

        //public String getNom()
        //{
        //    return nom;
        //}

        //public String getNom()
        //{
        //    return nom;
        //}

    }
}
