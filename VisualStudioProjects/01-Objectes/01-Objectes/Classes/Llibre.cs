using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _01_Objectes.Classes
{
    class Llibre
    {
        /////////////////////
        //   PROPIETATS    // 

        private int numPagines;
        private String ISBN;
        private String autor;
        private String colorPortada;


        /////////////////////
        //     MÈTODES     // 




        /////////////////////
        //   CONSTRUCTOR   // Donar valor a les dades que són privades.
        public Llibre()
        {
         /* public NomDeLaClasse, Constructor per defecte.
             SEMPRE S'HA D'INTRODUÏR, EXCEPTE EN CASOS ESPECIALS
             SI NO, NO ES PODRAN FER SERVIR FUNCIONS PÚBLIQUES
             */
       
        }

        public Llibre(int numPagines, String ISBN, String autor, String colorPortada)/* Les dades entraran per els paràmetres. */
            {
                this.numPagines = numPagines; //Amb "this" vol dir la variable de la propietat. Propietat = variable.
                this.ISBN = ISBN;
                this.autor = autor;
                this.colorPortada = colorPortada;
            }
        /* POTS CREAR CONSTRUCTORS AMB TOTS ELS PARÀMETRES O CONSTRUCTORS AMB MENYS, AIXÒ ES DIU SOBRECÀRREGA DE MÈTODES */
        public Llibre(String ISBN, String autor)/* Les dades entraran per els paràmetres. */
        {
            this.ISBN = ISBN;
            this.autor = autor;   
        }
    }
}
