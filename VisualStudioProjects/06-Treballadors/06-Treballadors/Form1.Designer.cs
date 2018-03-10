namespace _06_Treballadors
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
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.btGuarda = new System.Windows.Forms.Button();
            this.btMostra = new System.Windows.Forms.Button();
            this.tbNom = new System.Windows.Forms.TextBox();
            this.tbSalari = new System.Windows.Forms.TextBox();
            this.rtbPantalla = new System.Windows.Forms.RichTextBox();
            this.cbGen = new System.Windows.Forms.ComboBox();
            this.btSouGran = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(13, 13);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Nom";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(13, 46);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(42, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Gènere";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 85);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(33, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Salari";
            // 
            // btGuarda
            // 
            this.btGuarda.Location = new System.Drawing.Point(16, 141);
            this.btGuarda.Name = "btGuarda";
            this.btGuarda.Size = new System.Drawing.Size(75, 23);
            this.btGuarda.TabIndex = 3;
            this.btGuarda.Text = "btGuarda";
            this.btGuarda.UseVisualStyleBackColor = true;
            this.btGuarda.Click += new System.EventHandler(this.btGuarda_Click);
            // 
            // btMostra
            // 
            this.btMostra.Location = new System.Drawing.Point(126, 141);
            this.btMostra.Name = "btMostra";
            this.btMostra.Size = new System.Drawing.Size(75, 23);
            this.btMostra.TabIndex = 4;
            this.btMostra.Text = "btMostra";
            this.btMostra.UseVisualStyleBackColor = true;
            this.btMostra.Click += new System.EventHandler(this.btMostra_Click);
            // 
            // tbNom
            // 
            this.tbNom.Location = new System.Drawing.Point(69, 10);
            this.tbNom.Name = "tbNom";
            this.tbNom.Size = new System.Drawing.Size(100, 20);
            this.tbNom.TabIndex = 5;
            // 
            // tbSalari
            // 
            this.tbSalari.Location = new System.Drawing.Point(69, 82);
            this.tbSalari.Name = "tbSalari";
            this.tbSalari.Size = new System.Drawing.Size(100, 20);
            this.tbSalari.TabIndex = 7;
            // 
            // rtbPantalla
            // 
            this.rtbPantalla.BackColor = System.Drawing.SystemColors.Control;
            this.rtbPantalla.Location = new System.Drawing.Point(245, 13);
            this.rtbPantalla.Name = "rtbPantalla";
            this.rtbPantalla.Size = new System.Drawing.Size(301, 371);
            this.rtbPantalla.TabIndex = 8;
            this.rtbPantalla.Text = "";
            // 
            // cbGen
            // 
            this.cbGen.AutoCompleteCustomSource.AddRange(new string[] {
            "H",
            "D"});
            this.cbGen.FormattingEnabled = true;
            this.cbGen.Location = new System.Drawing.Point(69, 46);
            this.cbGen.Name = "cbGen";
            this.cbGen.Size = new System.Drawing.Size(100, 21);
            this.cbGen.TabIndex = 9;
            // 
            // btSouGran
            // 
            this.btSouGran.Location = new System.Drawing.Point(69, 180);
            this.btSouGran.Name = "btSouGran";
            this.btSouGran.Size = new System.Drawing.Size(75, 23);
            this.btSouGran.TabIndex = 10;
            this.btSouGran.Text = "Sou més gran";
            this.btSouGran.UseVisualStyleBackColor = true;
            this.btSouGran.Click += new System.EventHandler(this.btSouGran_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(734, 440);
            this.Controls.Add(this.btSouGran);
            this.Controls.Add(this.cbGen);
            this.Controls.Add(this.rtbPantalla);
            this.Controls.Add(this.tbSalari);
            this.Controls.Add(this.tbNom);
            this.Controls.Add(this.btMostra);
            this.Controls.Add(this.btGuarda);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Button btGuarda;
        private System.Windows.Forms.Button btMostra;
        private System.Windows.Forms.TextBox tbNom;
        private System.Windows.Forms.TextBox tbSalari;
        private System.Windows.Forms.RichTextBox rtbPantalla;
        private System.Windows.Forms.ComboBox cbGen;
        private System.Windows.Forms.Button btSouGran;
    }
}

