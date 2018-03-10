namespace _04_Alumne_Array
{
    partial class Form
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.tbNom = new System.Windows.Forms.TextBox();
            this.tbCog1 = new System.Windows.Forms.TextBox();
            this.tbCog2 = new System.Windows.Forms.TextBox();
            this.cbEdat = new System.Windows.Forms.ComboBox();
            this.cbNota = new System.Windows.Forms.ComboBox();
            this.btSave = new System.Windows.Forms.Button();
            this.btShow = new System.Windows.Forms.Button();
            this.rtbPantalla = new System.Windows.Forms.RichTextBox();
            this.lbContador = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(43, 21);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Nom";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(43, 49);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(55, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Cognom 1";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(43, 76);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(55, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Cognom 2";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(43, 104);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(29, 13);
            this.label4.TabIndex = 3;
            this.label4.Text = "Edat";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(43, 131);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(30, 13);
            this.label5.TabIndex = 4;
            this.label5.Text = "Nota";
            // 
            // tbNom
            // 
            this.tbNom.Location = new System.Drawing.Point(123, 18);
            this.tbNom.Name = "tbNom";
            this.tbNom.Size = new System.Drawing.Size(100, 20);
            this.tbNom.TabIndex = 5;
            // 
            // tbCog1
            // 
            this.tbCog1.Location = new System.Drawing.Point(123, 46);
            this.tbCog1.Name = "tbCog1";
            this.tbCog1.Size = new System.Drawing.Size(100, 20);
            this.tbCog1.TabIndex = 6;
            // 
            // tbCog2
            // 
            this.tbCog2.Location = new System.Drawing.Point(123, 76);
            this.tbCog2.Name = "tbCog2";
            this.tbCog2.Size = new System.Drawing.Size(100, 20);
            this.tbCog2.TabIndex = 7;
            // 
            // cbEdat
            // 
            this.cbEdat.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbEdat.FormattingEnabled = true;
            this.cbEdat.Location = new System.Drawing.Point(123, 104);
            this.cbEdat.Name = "cbEdat";
            this.cbEdat.Size = new System.Drawing.Size(42, 21);
            this.cbEdat.TabIndex = 8;
            // 
            // cbNota
            // 
            this.cbNota.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cbNota.FormattingEnabled = true;
            this.cbNota.Location = new System.Drawing.Point(123, 131);
            this.cbNota.Name = "cbNota";
            this.cbNota.Size = new System.Drawing.Size(42, 21);
            this.cbNota.TabIndex = 9;
            // 
            // btSave
            // 
            this.btSave.Location = new System.Drawing.Point(46, 178);
            this.btSave.Name = "btSave";
            this.btSave.Size = new System.Drawing.Size(75, 23);
            this.btSave.TabIndex = 10;
            this.btSave.Text = "Guardar";
            this.btSave.UseVisualStyleBackColor = true;
            this.btSave.Click += new System.EventHandler(this.BtSave_Click);
            // 
            // btShow
            // 
            this.btShow.Location = new System.Drawing.Point(148, 178);
            this.btShow.Name = "btShow";
            this.btShow.Size = new System.Drawing.Size(75, 23);
            this.btShow.TabIndex = 11;
            this.btShow.Text = "Mostrar";
            this.btShow.UseVisualStyleBackColor = true;
            this.btShow.Click += new System.EventHandler(this.BtShow_Click);
            // 
            // rtbPantalla
            // 
            this.rtbPantalla.BackColor = System.Drawing.SystemColors.Control;
            this.rtbPantalla.Location = new System.Drawing.Point(314, 21);
            this.rtbPantalla.Name = "rtbPantalla";
            this.rtbPantalla.Size = new System.Drawing.Size(230, 309);
            this.rtbPantalla.TabIndex = 12;
            this.rtbPantalla.Text = "";
            // 
            // lbContador
            // 
            this.lbContador.AutoSize = true;
            this.lbContador.Location = new System.Drawing.Point(224, 139);
            this.lbContador.Name = "lbContador";
            this.lbContador.Size = new System.Drawing.Size(0, 13);
            this.lbContador.TabIndex = 13;
            // 
            // Form
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(573, 348);
            this.Controls.Add(this.lbContador);
            this.Controls.Add(this.rtbPantalla);
            this.Controls.Add(this.btShow);
            this.Controls.Add(this.btSave);
            this.Controls.Add(this.cbNota);
            this.Controls.Add(this.cbEdat);
            this.Controls.Add(this.tbCog2);
            this.Controls.Add(this.tbCog1);
            this.Controls.Add(this.tbNom);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form";
            this.Text = "Llista d\'alumnes";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox tbNom;
        private System.Windows.Forms.TextBox tbCog1;
        private System.Windows.Forms.TextBox tbCog2;
        private System.Windows.Forms.ComboBox cbEdat;
        private System.Windows.Forms.ComboBox cbNota;
        private System.Windows.Forms.Button btSave;
        private System.Windows.Forms.Button btShow;
        private System.Windows.Forms.RichTextBox rtbPantalla;
        private System.Windows.Forms.Label lbContador;
    }
}

