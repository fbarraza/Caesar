using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _03_InfoLlibre
{
    /*
    class Clase           :          Form
    class Nombredelaclase Herenciade Nombre de otra clase
    */
    class Llibre
    {
        //PROPIEDADES DE LA CLASE
        private String titol;
        private int pag;
        private String dim;
        private String autor;
        private String color;
        private int any;
        private String contingut;

        //CONSTRUCTOR POR DEFECTO -- SIEMPRE SE DEBE PONER
        public Llibre()
        {
            
        }

        //CONSTRUCTOR DE LA CLASE(Variables del constructor separadas por comas).
        public Llibre(String titol, int pag, String dim, String autor, String color, int any, String contingut)
        {
            //propiedadclase = variable constructor. Se usa 
            this.titol = titol;
            this.pag = pag;
            this.dim = dim;
            this.autor = autor;
            this.color = color;
            this.any = any;
            this.contingut = contingut;
        }


        ///////////////////////
        //    MÈTODES GET    //

        /**
         *"public" por que quiero que se pueda acceder dentro.
         */
        /*
       public String getTitol() //El nombre del método ES OBLIGATORIO escribirlo como "getLOQUESEA".
       {
           return this.titol; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       public int getPag()
       {
           return this.pag; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       public String getDim()
       {
           return this.dim; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       public String getAutor()
       {
           return this.autor; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       public String getColor()
       {
           return this.color; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       public int getAny()
       {
           return this.any; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       public String getContingut()
       {
           return this.contingut; //Devuelvo la propiedad, o variable de la clase ya almacenada antes en el constructor YA INICIALIZADO con DATOS ENCAPSULADOS!!
       }
       */
        /*
         * //EDIT, REFACTOR, ENCAPSULATE FIELD.
         * Seleccionamos TODAS LAS PROPIEDADES.
         * Esto de "get => titol" se llama 'Lambda', se utiliza más.
         * Encapsulación de campo, getters y setters.
         *                                          |
         *                                          v
        */
        public string Titol { get => titol; }
        public int Pag { get => pag; }
        public string Dim { get => dim; }
        public string Autor { get => autor; }
        public string Color { get => color; }
        public int Any { get => any; }
        public string Contingut { get => contingut; }
    }
}
