namespace _05_Fitxers
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
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.tbTitol = new System.Windows.Forms.TextBox();
            this.tbPag = new System.Windows.Forms.TextBox();
            this.tbDim = new System.Windows.Forms.TextBox();
            this.tbAutor = new System.Windows.Forms.TextBox();
            this.tbColor = new System.Windows.Forms.TextBox();
            this.tbAny = new System.Windows.Forms.TextBox();
            this.tbContingut = new System.Windows.Forms.TextBox();
            this.btSave = new System.Windows.Forms.Button();
            this.btShow = new System.Windows.Forms.Button();
            this.rtbPantalla = new System.Windows.Forms.RichTextBox();
            this.SuspendLayout();
            // 
            // lbTitol
            // 
            this.lbTitol.AutoSize = true;
            this.lbTitol.Location = new System.Drawing.Point(13, 13);
            this.lbTitol.Name = "lbTitol";
            this.lbTitol.Size = new System.Drawing.Size(29, 13);
            this.lbTitol.TabIndex = 0;
            this.lbTitol.Text = "Títol";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(13, 40);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(99, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Número de pàgines";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 70);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(61, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Dimensions";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(13, 99);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(32, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "Autor";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(13, 128);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(71, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "Color Portada";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(13, 155);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(91, 13);
            this.label6.TabIndex = 5;
            this.label6.Text = "Any de publicació";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(13, 185);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(52, 13);
            this.label7.TabIndex = 6;
            this.label7.Text = "Contingut";
            // 
            // tbTitol
            // 
            this.tbTitol.Location = new System.Drawing.Point(145, 10);
            this.tbTitol.Name = "tbTitol";
            this.tbTitol.Size = new System.Drawing.Size(100, 20);
            this.tbTitol.TabIndex = 8;
            // 
            // tbPag
            // 
            this.tbPag.Location = new System.Drawing.Point(145, 36);
            this.tbPag.Name = "tbPag";
            this.tbPag.Size = new System.Drawing.Size(100, 20);
            this.tbPag.TabIndex = 9;
            // 
            // tbDim
            // 
            this.tbDim.Location = new System.Drawing.Point(145, 66);
            this.tbDim.Name = "tbDim";
            this.tbDim.Size = new System.Drawing.Size(100, 20);
            this.tbDim.TabIndex = 10;
            // 
            // tbAutor
            // 
            this.tbAutor.Location = new System.Drawing.Point(145, 96);
            this.tbAutor.Name = "tbAutor";
            this.tbAutor.Size = new System.Drawing.Size(100, 20);
            this.tbAutor.TabIndex = 11;
            // 
            // tbColor
            // 
            this.tbColor.Location = new System.Drawing.Point(145, 125);
            this.tbColor.Name = "tbColor";
            this.tbColor.Size = new System.Drawing.Size(100, 20);
            this.tbColor.TabIndex = 12;
            // 
            // tbAny
            // 
            this.tbAny.Location = new System.Drawing.Point(145, 155);
            this.tbAny.Name = "tbAny";
            this.tbAny.Size = new System.Drawing.Size(100, 20);
            this.tbAny.TabIndex = 13;
            // 
            // tbContingut
            // 
            this.tbContingut.Location = new System.Drawing.Point(145, 182);
            this.tbContingut.Name = "tbContingut";
            this.tbContingut.Size = new System.Drawing.Size(100, 20);
            this.tbContingut.TabIndex = 14;
            // 
            // btSave
            // 
            this.btSave.Location = new System.Drawing.Point(29, 224);
            this.btSave.Name = "btSave";
            this.btSave.Size = new System.Drawing.Size(75, 23);
            this.btSave.TabIndex = 15;
            this.btSave.Text = "Guardar";
            this.btSave.UseVisualStyleBackColor = true;
            this.btSave.Click += new System.EventHandler(this.btSave_Click);
            // 
            // btShow
            // 
            this.btShow.Location = new System.Drawing.Point(145, 224);
            this.btShow.Name = "btShow";
            this.btShow.Size = new System.Drawing.Size(75, 23);
            this.btShow.TabIndex = 16;
            this.btShow.Text = "Mostrar";
            this.btShow.UseVisualStyleBackColor = true;
            this.btShow.Click += new System.EventHandler(this.BtShow_Click);
            // 
            // rtbPantalla
            // 
            this.rtbPantalla.BackColor = System.Drawing.SystemColors.Control;
            this.rtbPantalla.Location = new System.Drawing.Point(298, 13);
            this.rtbPantalla.Name = "rtbPantalla";
            this.rtbPantalla.Size = new System.Drawing.Size(310, 309);
            this.rtbPantalla.TabIndex = 17;
            this.rtbPantalla.Text = "";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(627, 339);
            this.Controls.Add(this.rtbPantalla);
            this.Controls.Add(this.btShow);
            this.Controls.Add(this.btSave);
            this.Controls.Add(this.tbContingut);
            this.Controls.Add(this.tbAny);
            this.Controls.Add(this.tbColor);
            this.Controls.Add(this.tbAutor);
            this.Controls.Add(this.tbDim);
            this.Controls.Add(this.tbPag);
            this.Controls.Add(this.tbTitol);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.lbTitol);
            this.Name = "Form1";
            this.Text = "FitxerLlibre";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbTitol;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox tbTitol;
        private System.Windows.Forms.TextBox tbPag;
        private System.Windows.Forms.TextBox tbDim;
        private System.Windows.Forms.TextBox tbAutor;
        private System.Windows.Forms.TextBox tbColor;
        private System.Windows.Forms.TextBox tbAny;
        private System.Windows.Forms.TextBox tbContingut;
        private System.Windows.Forms.Button btSave;
        private System.Windows.Forms.Button btShow;
        private System.Windows.Forms.RichTextBox rtbPantalla;
    }
}

