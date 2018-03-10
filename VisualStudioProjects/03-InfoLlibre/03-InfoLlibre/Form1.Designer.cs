namespace _03_InfoLlibre
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.bBack = new System.Windows.Forms.Button();
            this.bForward = new System.Windows.Forms.Button();
            this.lTitol = new System.Windows.Forms.Label();
            this.lPag = new System.Windows.Forms.Label();
            this.lDim = new System.Windows.Forms.Label();
            this.lAutor = new System.Windows.Forms.Label();
            this.lColorPortada = new System.Windows.Forms.Label();
            this.lAny = new System.Windows.Forms.Label();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.bGuarda = new System.Windows.Forms.Button();
            this.TBautor = new System.Windows.Forms.TextBox();
            this.TBcontingut = new System.Windows.Forms.TextBox();
            this.TBdimensions = new System.Windows.Forms.TextBox();
            this.TBpag = new System.Windows.Forms.TextBox();
            this.TBany = new System.Windows.Forms.TextBox();
            this.TBcolor = new System.Windows.Forms.TextBox();
            this.TBtitol = new System.Windows.Forms.TextBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.label7 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label13 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.label14 = new System.Windows.Forms.Label();
            this.lContingut = new System.Windows.Forms.Label();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
            // 
            // bBack
            // 
            this.bBack.Location = new System.Drawing.Point(21, 471);
            this.bBack.Name = "bBack";
            this.bBack.Size = new System.Drawing.Size(75, 23);
            this.bBack.TabIndex = 0;
            this.bBack.Text = "<-";
            this.bBack.UseVisualStyleBackColor = true;
            this.bBack.Click += new System.EventHandler(this.bBack_Click);
            // 
            // bForward
            // 
            this.bForward.Location = new System.Drawing.Point(131, 471);
            this.bForward.Name = "bForward";
            this.bForward.Size = new System.Drawing.Size(75, 23);
            this.bForward.TabIndex = 1;
            this.bForward.Text = "->";
            this.bForward.UseVisualStyleBackColor = true;
            this.bForward.Click += new System.EventHandler(this.bForward_Click);
            // 
            // lTitol
            // 
            this.lTitol.AutoSize = true;
            this.lTitol.Location = new System.Drawing.Point(46, 76);
            this.lTitol.Name = "lTitol";
            this.lTitol.Size = new System.Drawing.Size(0, 13);
            this.lTitol.TabIndex = 2;
            // 
            // lPag
            // 
            this.lPag.AutoSize = true;
            this.lPag.Location = new System.Drawing.Point(46, 173);
            this.lPag.Name = "lPag";
            this.lPag.Size = new System.Drawing.Size(0, 13);
            this.lPag.TabIndex = 3;
            // 
            // lDim
            // 
            this.lDim.AutoSize = true;
            this.lDim.Location = new System.Drawing.Point(46, 124);
            this.lDim.Name = "lDim";
            this.lDim.Size = new System.Drawing.Size(0, 13);
            this.lDim.TabIndex = 4;
            // 
            // lAutor
            // 
            this.lAutor.AutoSize = true;
            this.lAutor.Location = new System.Drawing.Point(195, 76);
            this.lAutor.Name = "lAutor";
            this.lAutor.Size = new System.Drawing.Size(0, 13);
            this.lAutor.TabIndex = 5;
            // 
            // lColorPortada
            // 
            this.lColorPortada.AutoSize = true;
            this.lColorPortada.Location = new System.Drawing.Point(195, 124);
            this.lColorPortada.Name = "lColorPortada";
            this.lColorPortada.Size = new System.Drawing.Size(0, 13);
            this.lColorPortada.TabIndex = 6;
            // 
            // lAny
            // 
            this.lAny.AutoSize = true;
            this.lAny.Location = new System.Drawing.Point(366, 124);
            this.lAny.Name = "lAny";
            this.lAny.Size = new System.Drawing.Size(0, 13);
            this.lAny.TabIndex = 7;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.label9);
            this.groupBox1.Controls.Add(this.label8);
            this.groupBox1.Controls.Add(this.label7);
            this.groupBox1.Controls.Add(this.label6);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.bGuarda);
            this.groupBox1.Controls.Add(this.TBautor);
            this.groupBox1.Controls.Add(this.TBcontingut);
            this.groupBox1.Controls.Add(this.TBdimensions);
            this.groupBox1.Controls.Add(this.TBpag);
            this.groupBox1.Controls.Add(this.TBany);
            this.groupBox1.Controls.Add(this.TBcolor);
            this.groupBox1.Controls.Add(this.TBtitol);
            this.groupBox1.Location = new System.Drawing.Point(42, 28);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(472, 200);
            this.groupBox1.TabIndex = 9;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Formulari";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(183, 21);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(32, 13);
            this.label2.TabIndex = 11;
            this.label2.Text = "Autor";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(30, 20);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(29, 13);
            this.label1.TabIndex = 10;
            this.label1.Text = "Títol";
            // 
            // bGuarda
            // 
            this.bGuarda.Location = new System.Drawing.Point(344, 142);
            this.bGuarda.Name = "bGuarda";
            this.bGuarda.Size = new System.Drawing.Size(100, 20);
            this.bGuarda.TabIndex = 9;
            this.bGuarda.Text = "Guardar";
            this.bGuarda.UseVisualStyleBackColor = true;
            this.bGuarda.Click += new System.EventHandler(this.bGuarda_Click);
            // 
            // TBautor
            // 
            this.TBautor.Location = new System.Drawing.Point(186, 37);
            this.TBautor.Name = "TBautor";
            this.TBautor.Size = new System.Drawing.Size(100, 20);
            this.TBautor.TabIndex = 8;
            // 
            // TBcontingut
            // 
            this.TBcontingut.Location = new System.Drawing.Point(344, 37);
            this.TBcontingut.Name = "TBcontingut";
            this.TBcontingut.Size = new System.Drawing.Size(100, 20);
            this.TBcontingut.TabIndex = 7;
            // 
            // TBdimensions
            // 
            this.TBdimensions.Location = new System.Drawing.Point(30, 90);
            this.TBdimensions.Name = "TBdimensions";
            this.TBdimensions.Size = new System.Drawing.Size(100, 20);
            this.TBdimensions.TabIndex = 6;
            // 
            // TBpag
            // 
            this.TBpag.Location = new System.Drawing.Point(30, 142);
            this.TBpag.Name = "TBpag";
            this.TBpag.Size = new System.Drawing.Size(100, 20);
            this.TBpag.TabIndex = 5;
            // 
            // TBany
            // 
            this.TBany.Location = new System.Drawing.Point(344, 90);
            this.TBany.Name = "TBany";
            this.TBany.Size = new System.Drawing.Size(100, 20);
            this.TBany.TabIndex = 2;
            // 
            // TBcolor
            // 
            this.TBcolor.Location = new System.Drawing.Point(186, 90);
            this.TBcolor.Name = "TBcolor";
            this.TBcolor.Size = new System.Drawing.Size(100, 20);
            this.TBcolor.TabIndex = 1;
            // 
            // TBtitol
            // 
            this.TBtitol.Location = new System.Drawing.Point(30, 37);
            this.TBtitol.Name = "TBtitol";
            this.TBtitol.Size = new System.Drawing.Size(100, 20);
            this.TBtitol.TabIndex = 0;
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.lContingut);
            this.groupBox2.Controls.Add(this.label14);
            this.groupBox2.Controls.Add(this.label13);
            this.groupBox2.Controls.Add(this.label12);
            this.groupBox2.Controls.Add(this.label11);
            this.groupBox2.Controls.Add(this.label10);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.label3);
            this.groupBox2.Controls.Add(this.lAny);
            this.groupBox2.Controls.Add(this.lPag);
            this.groupBox2.Controls.Add(this.lDim);
            this.groupBox2.Controls.Add(this.lColorPortada);
            this.groupBox2.Controls.Add(this.lAutor);
            this.groupBox2.Controls.Add(this.lTitol);
            this.groupBox2.Location = new System.Drawing.Point(42, 234);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(472, 218);
            this.groupBox2.TabIndex = 10;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Guardar";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(30, 126);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(99, 13);
            this.label5.TabIndex = 14;
            this.label5.Text = "Número de pàgines";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(341, 74);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(25, 13);
            this.label6.TabIndex = 15;
            this.label6.Text = "Any";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(183, 74);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(70, 13);
            this.label7.TabIndex = 16;
            this.label7.Text = "Color portada";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(30, 74);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(61, 13);
            this.label8.TabIndex = 17;
            this.label8.Text = "Dimensions";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(344, 21);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(52, 13);
            this.label9.TabIndex = 18;
            this.label9.Text = "Contingut";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label10.Location = new System.Drawing.Point(363, 63);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(61, 13);
            this.label10.TabIndex = 21;
            this.label10.Text = "Contingut";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label13.Location = new System.Drawing.Point(363, 111);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(28, 13);
            this.label13.TabIndex = 24;
            this.label13.Text = "Any";
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label12.Location = new System.Drawing.Point(195, 111);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(83, 13);
            this.label12.TabIndex = 23;
            this.label12.Text = "Color portada";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(195, 63);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(37, 13);
            this.label4.TabIndex = 20;
            this.label4.Text = "Autor";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(41, 63);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(34, 13);
            this.label3.TabIndex = 19;
            this.label3.Text = "Títol";
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label11.Location = new System.Drawing.Point(41, 111);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(71, 13);
            this.label11.TabIndex = 22;
            this.label11.Text = "Dimensions";
            // 
            // label14
            // 
            this.label14.AutoSize = true;
            this.label14.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label14.Location = new System.Drawing.Point(41, 160);
            this.label14.Name = "label14";
            this.label14.Size = new System.Drawing.Size(116, 13);
            this.label14.TabIndex = 25;
            this.label14.Text = "Número de pàgines";
            // 
            // lContingut
            // 
            this.lContingut.AutoSize = true;
            this.lContingut.Location = new System.Drawing.Point(366, 76);
            this.lContingut.Name = "lContingut";
            this.lContingut.Size = new System.Drawing.Size(0, 13);
            this.lContingut.TabIndex = 26;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(557, 506);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.bForward);
            this.Controls.Add(this.bBack);
            this.Name = "Form1";
            this.Text = "Form1";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button bBack;
        private System.Windows.Forms.Button bForward;
        private System.Windows.Forms.Label lTitol;
        private System.Windows.Forms.Label lPag;
        private System.Windows.Forms.Label lDim;
        private System.Windows.Forms.Label lAutor;
        private System.Windows.Forms.Label lColorPortada;
        private System.Windows.Forms.Label lAny;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button bGuarda;
        private System.Windows.Forms.TextBox TBautor;
        private System.Windows.Forms.TextBox TBcontingut;
        private System.Windows.Forms.TextBox TBdimensions;
        private System.Windows.Forms.TextBox TBpag;
        private System.Windows.Forms.TextBox TBany;
        private System.Windows.Forms.TextBox TBcolor;
        private System.Windows.Forms.TextBox TBtitol;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label14;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label lContingut;
    }
}

