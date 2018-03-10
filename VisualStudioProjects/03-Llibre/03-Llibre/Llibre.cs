using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _03_Llibre
{
    class Llibre
    {
        private String titulo;
        private int paginas;
        private String autor;
        private String color;
        private String genero;
        private String dimensiones;
        private String contenido;

        public Llibre()
        {

        }

        public Llibre(String titulo,int paginas,String autor,String color, String genero, String dimensiones, String contenido)
        {
            this.titulo = titulo;
            this.paginas = paginas;
            this.autor = autor;
            this.color = color;
            this.genero = genero;
            this.dimensiones = dimensiones;
            this.contenido = contenido;
        }

        public string Titulo { get => titulo; set => titulo = value; }
        public int Paginas { get => paginas; set => paginas = value; }
        public string Autor { get => autor; set => autor = value; }
        public string Color { get => color; set => color = value; }
        public string Genero { get => genero; set => genero = value; }
        public string Dimensiones { get => dimensiones; set => dimensiones = value; }
        public string Contenido { get => contenido; set => contenido = value; }


    }
}
