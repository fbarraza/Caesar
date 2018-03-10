namespace _03_Llibre
{
    partial class Form1
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            this.lbTitol = new System.Windows.Forms.Label();
            this.lbPag = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.lbDim = new System.Windows.Forms.Label();
            this.lbCon = new System.Windows.Forms.Label();
            this.btGuardar = new System.Windows.Forms.Button();
            this.btMostrar = new System.Windows.Forms.Button();
            this.tbTitol = new System.Windows.Forms.TextBox();
            this.tbPag = new System.Windows.Forms.TextBox();
            this.tbAut = new System.Windows.Forms.TextBox();
            this.tbCol = new System.Windows.Forms.TextBox();
            this.tbGen = new System.Windows.Forms.TextBox();
            this.tbDim = new System.Windows.Forms.TextBox();
            this.tbCon = new System.Windows.Forms.TextBox();
            this.rtbPantalla = new System.Windows.Forms.RichTextBox();
            this.SuspendLayout();
            // 
            // lbTitol
            // 
            this.lbTitol.AutoSize = true;
            this.lbTitol.Location = new System.Drawing.Point(12, 9);
            this.lbTitol.Name = "lbTitol";
            this.lbTitol.Size = new System.Drawing.Size(35, 13);
            this.lbTitol.TabIndex = 0;
            this.lbTitol.Text = "Título";
            // 
            // lbPag
            // 
            this.lbPag.AutoSize = true;
            this.lbPag.Location = new System.Drawing.Point(12, 40);
            this.lbPag.Name = "lbPag";
            this.lbPag.Size = new System.Drawing.Size(45, 13);
            this.lbPag.TabIndex = 1;
            this.lbPag.Text = "Páginas";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(12, 71);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(32, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Autor";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(12, 102);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(71, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "Color Portada";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 135);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(42, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "Género";
            // 
            // lbDim
            // 
            this.lbDim.AutoSize = true;
            this.lbDim.Location = new System.Drawing.Point(12, 169);
            this.lbDim.Name = "lbDim";
            this.lbDim.Size = new System.Drawing.Size(67, 13);
            this.lbDim.TabIndex = 5;
            this.lbDim.Text = "Dimensiones";
            // 
            // lbCon
            // 
            this.lbCon.AutoSize = true;
            this.lbCon.Location = new System.Drawing.Point(12, 202);
            this.lbCon.Name = "lbCon";
            this.lbCon.Size = new System.Drawing.Size(55, 13);
            this.lbCon.TabIndex = 6;
            this.lbCon.Text = "Contenido";
            // 
            // btGuardar
            // 
            this.btGuardar.Location = new System.Drawing.Point(15, 278);
            this.btGuardar.Name = "btGuardar";
            this.btGuardar.Size = new System.Drawing.Size(75, 23);
            this.btGuardar.TabIndex = 7;
            this.btGuardar.Text = "Guardar";
            this.btGuardar.UseVisualStyleBackColor = true;
            this.btGuardar.Click += new System.EventHandler(this.btGuardar_Click);
            // 
            // btMostrar
            // 
            this.btMostrar.Location = new System.Drawing.Point(123, 278);
            this.btMostrar.Name = "btMostrar";
            this.btMostrar.Size = new System.Drawing.Size(75, 23);
            this.btMostrar.TabIndex = 8;
            this.btMostrar.Text = "Mostrar";
            this.btMostrar.UseVisualStyleBackColor = true;
            this.btMostrar.Click += new System.EventHandler(this.btMostrar_Click);
            // 
            // tbTitol
            // 
            this.tbTitol.Location = new System.Drawing.Point(82, 6);
            this.tbTitol.Name = "tbTitol";
            this.tbTitol.Size = new System.Drawing.Size(100, 20);
            this.tbTitol.TabIndex = 9;
            // 
            // tbPag
            // 
            this.tbPag.Location = new System.Drawing.Point(82, 37);
            this.tbPag.Name = "tbPag";
            this.tbPag.Size = new System.Drawing.Size(100, 20);
            this.tbPag.TabIndex = 10;
            // 
            // tbAut
            // 
            this.tbAut.Location = new System.Drawing.Point(82, 68);
            this.tbAut.Name = "tbAut";
            this.tbAut.Size = new System.Drawing.Size(100, 20);
            this.tbAut.TabIndex = 11;
            // 
            // tbCol
            // 
            this.tbCol.Location = new System.Drawing.Point(82, 99);
            this.tbCol.Name = "tbCol";
            this.tbCol.Size = new System.Drawing.Size(100, 20);
            this.tbCol.TabIndex = 12;
            // 
            // tbGen
            // 
            this.tbGen.Location = new System.Drawing.Point(82, 132);
            this.tbGen.Name = "tbGen";
            this.tbGen.Size = new System.Drawing.Size(100, 20);
            this.tbGen.TabIndex = 13;
            // 
            // tbDim
            // 
            this.tbDim.Location = new System.Drawing.Point(82, 166);
            this.tbDim.Name = "tbDim";
            this.tbDim.Size = new System.Drawing.Size(100, 20);
            this.tbDim.TabIndex = 14;
            // 
            // tbCon
            // 
            this.tbCon.Location = new System.Drawing.Point(82, 202);
            this.tbCon.Name = "tbCon";
            this.tbCon.Size = new System.Drawing.Size(100, 20);
            this.tbCon.TabIndex = 15;
            // 
            // rtbPantalla
            // 
            this.rtbPantalla.BackColor = System.Drawing.SystemColors.Control;
            this.rtbPantalla.Location = new System.Drawing.Point(262, 6);
            this.rtbPantalla.Name = "rtbPantalla";
            this.rtbPantalla.Size = new System.Drawing.Size(297, 295);
            this.rtbPantalla.TabIndex = 16;
            this.rtbPantalla.Text = "";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(582, 318);
            this.Controls.Add(this.rtbPantalla);
            this.Controls.Add(this.tbCon);
            this.Controls.Add(this.tbDim);
            this.Controls.Add(this.tbGen);
            this.Controls.Add(this.tbCol);
            this.Controls.Add(this.tbAut);
            this.Controls.Add(this.tbPag);
            this.Controls.Add(this.tbTitol);
            this.Controls.Add(this.btMostrar);
            this.Controls.Add(this.btGuardar);
            this.Controls.Add(this.lbCon);
            this.Controls.Add(this.lbDim);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.lbPag);
            this.Controls.Add(this.lbTitol);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbTitol;
        private System.Windows.Forms.Label lbPag;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label lbDim;
        private System.Windows.Forms.Label lbCon;
        private System.Windows.Forms.Button btGuardar;
        private System.Windows.Forms.Button btMostrar;
        private System.Windows.Forms.TextBox tbTitol;
        private System.Windows.Forms.TextBox tbPag;
        private System.Windows.Forms.TextBox tbAut;
        private System.Windows.Forms.TextBox tbCol;
        private System.Windows.Forms.TextBox tbGen;
        private System.Windows.Forms.TextBox tbDim;
        private System.Windows.Forms.TextBox tbCon;
        private System.Windows.Forms.RichTextBox rtbPantalla;
    }
}

