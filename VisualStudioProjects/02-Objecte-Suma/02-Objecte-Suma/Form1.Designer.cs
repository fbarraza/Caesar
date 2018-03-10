namespace _02_Objecte_Suma
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
            this.TB1 = new System.Windows.Forms.TextBox();
            this.TB2 = new System.Windows.Forms.TextBox();
            this.LBpantalla = new System.Windows.Forms.Label();
            this.bSuma = new System.Windows.Forms.Button();
            this.bResta = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // TB1
            // 
            this.TB1.Location = new System.Drawing.Point(109, 155);
            this.TB1.Name = "TB1";
            this.TB1.Size = new System.Drawing.Size(100, 20);
            this.TB1.TabIndex = 0;
            // 
            // TB2
            // 
            this.TB2.Location = new System.Drawing.Point(265, 155);
            this.TB2.Name = "TB2";
            this.TB2.Size = new System.Drawing.Size(100, 20);
            this.TB2.TabIndex = 1;
            // 
            // LBpantalla
            // 
            this.LBpantalla.AutoSize = true;
            this.LBpantalla.Location = new System.Drawing.Point(218, 234);
            this.LBpantalla.Name = "LBpantalla";
            this.LBpantalla.Size = new System.Drawing.Size(0, 13);
            this.LBpantalla.TabIndex = 2;
            this.LBpantalla.Click += new System.EventHandler(this.LBpantalla_Click);
            // 
            // bSuma
            // 
            this.bSuma.Location = new System.Drawing.Point(134, 295);
            this.bSuma.Name = "bSuma";
            this.bSuma.Size = new System.Drawing.Size(75, 23);
            this.bSuma.TabIndex = 3;
            this.bSuma.Text = "+";
            this.bSuma.UseVisualStyleBackColor = true;
            this.bSuma.Click += new System.EventHandler(this.bSuma_Click);
            // 
            // bResta
            // 
            this.bResta.Location = new System.Drawing.Point(265, 295);
            this.bResta.Name = "bResta";
            this.bResta.Size = new System.Drawing.Size(75, 23);
            this.bResta.TabIndex = 4;
            this.bResta.Text = "-";
            this.bResta.UseVisualStyleBackColor = true;
            this.bResta.Click += new System.EventHandler(this.bResta_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(640, 359);
            this.Controls.Add(this.bResta);
            this.Controls.Add(this.bSuma);
            this.Controls.Add(this.LBpantalla);
            this.Controls.Add(this.TB2);
            this.Controls.Add(this.TB1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox TB1;
        private System.Windows.Forms.TextBox TB2;
        private System.Windows.Forms.Label LBpantalla;
        private System.Windows.Forms.Button bSuma;
        private System.Windows.Forms.Button bResta;
    }
}

