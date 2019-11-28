namespace MoMo
{
    partial class MoMoForm
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MoMoForm));
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.textAmount = new System.Windows.Forms.TextBox();
            this.button1 = new System.Windows.Forms.Button();
            this.textPubkey = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.textEndpoint = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.textAccessKey = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.textPartnerCode = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.textOrderInfo = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.textNotify = new System.Windows.Forms.TextBox();
            this.label8 = new System.Windows.Forms.Label();
            this.textReturn = new System.Windows.Forms.TextBox();
            this.payOfflinebtn = new System.Windows.Forms.Button();
            this.label9 = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label11 = new System.Windows.Forms.Label();
            this.textPaymentCode = new System.Windows.Forms.TextBox();
            this.label12 = new System.Windows.Forms.Label();
            this.textAmountPos = new System.Windows.Forms.TextBox();
            this.label13 = new System.Windows.Forms.Label();
            this.textEndpointPos = new System.Windows.Forms.TextBox();
            this.button2 = new System.Windows.Forms.Button();
            this.button3 = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(18, 333);
            this.label3.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(59, 13);
            this.label3.TabIndex = 25;
            this.label3.Text = "Public key:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(38, 128);
            this.label2.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(46, 13);
            this.label2.TabIndex = 24;
            this.label2.Text = "Amount:";
            // 
            // textAmount
            // 
            this.textAmount.Location = new System.Drawing.Point(90, 125);
            this.textAmount.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textAmount.Name = "textAmount";
            this.textAmount.Size = new System.Drawing.Size(250, 20);
            this.textAmount.TabIndex = 22;
            this.textAmount.Text = "55000";
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(370, 493);
            this.button1.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(138, 23);
            this.button1.TabIndex = 20;
            this.button1.Text = "Web Payment request";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // textPubkey
            // 
            this.textPubkey.Location = new System.Drawing.Point(20, 356);
            this.textPubkey.Margin = new System.Windows.Forms.Padding(2);
            this.textPubkey.Multiline = true;
            this.textPubkey.Name = "textPubkey";
            this.textPubkey.Size = new System.Drawing.Size(490, 129);
            this.textPubkey.TabIndex = 29;
            this.textPubkey.Text = resources.GetString("textPubkey.Text");
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(30, 46);
            this.label1.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(52, 13);
            this.label1.TabIndex = 31;
            this.label1.Text = "Endpoint:";
            // 
            // textEndpoint
            // 
            this.textEndpoint.Location = new System.Drawing.Point(90, 39);
            this.textEndpoint.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textEndpoint.Name = "textEndpoint";
            this.textEndpoint.Size = new System.Drawing.Size(342, 20);
            this.textEndpoint.TabIndex = 30;
            this.textEndpoint.Text = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(18, 73);
            this.label4.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(63, 13);
            this.label4.TabIndex = 33;
            this.label4.Text = "AccessKey:";
            // 
            // textAccessKey
            // 
            this.textAccessKey.Location = new System.Drawing.Point(90, 70);
            this.textAccessKey.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textAccessKey.Name = "textAccessKey";
            this.textAccessKey.Size = new System.Drawing.Size(250, 20);
            this.textAccessKey.TabIndex = 32;
            this.textAccessKey.Text = "M8brj9K6E22vXoDB";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(12, 99);
            this.label5.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(69, 13);
            this.label5.TabIndex = 35;
            this.label5.Text = "PartnerCode:";
            // 
            // textPartnerCode
            // 
            this.textPartnerCode.Location = new System.Drawing.Point(90, 96);
            this.textPartnerCode.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textPartnerCode.Name = "textPartnerCode";
            this.textPartnerCode.Size = new System.Drawing.Size(250, 20);
            this.textPartnerCode.TabIndex = 34;
            this.textPartnerCode.Text = "MOMO5RGX20191128";
            this.textPartnerCode.TextChanged += new System.EventHandler(this.textPartnerCode_TextChanged);
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(30, 154);
            this.label6.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(54, 13);
            this.label6.TabIndex = 37;
            this.label6.Text = "OrderInfo:";
            // 
            // textOrderInfo
            // 
            this.textOrderInfo.Location = new System.Drawing.Point(90, 151);
            this.textOrderInfo.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textOrderInfo.Name = "textOrderInfo";
            this.textOrderInfo.Size = new System.Drawing.Size(250, 20);
            this.textOrderInfo.TabIndex = 36;
            this.textOrderInfo.Text = "test";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(34, 183);
            this.label7.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(50, 13);
            this.label7.TabIndex = 39;
            this.label7.Text = "NotifyUrl:";
            // 
            // textNotify
            // 
            this.textNotify.Location = new System.Drawing.Point(90, 180);
            this.textNotify.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textNotify.Name = "textNotify";
            this.textNotify.Size = new System.Drawing.Size(250, 20);
            this.textNotify.TabIndex = 38;
            this.textNotify.Text = "https://momo.vn/notify";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(30, 209);
            this.label8.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(55, 13);
            this.label8.TabIndex = 41;
            this.label8.Text = "ReturnUrl:";
            // 
            // textReturn
            // 
            this.textReturn.Location = new System.Drawing.Point(90, 206);
            this.textReturn.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textReturn.Name = "textReturn";
            this.textReturn.Size = new System.Drawing.Size(250, 20);
            this.textReturn.TabIndex = 40;
            this.textReturn.Text = "https://momo.vn/return";
            // 
            // payOfflinebtn
            // 
            this.payOfflinebtn.Location = new System.Drawing.Point(252, 493);
            this.payOfflinebtn.Margin = new System.Windows.Forms.Padding(2);
            this.payOfflinebtn.Name = "payOfflinebtn";
            this.payOfflinebtn.Size = new System.Drawing.Size(114, 23);
            this.payOfflinebtn.TabIndex = 42;
            this.payOfflinebtn.Text = "Pay offline Request";
            this.payOfflinebtn.UseVisualStyleBackColor = true;
            this.payOfflinebtn.Click += new System.EventHandler(this.payOfflinebtn_Click);
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(6, 231);
            this.label9.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(78, 13);
            this.label9.TabIndex = 43;
            this.label9.Text = "PAY OFFLINE:";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(24, 16);
            this.label10.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(59, 13);
            this.label10.TabIndex = 44;
            this.label10.Text = "PAY WEB:";
            this.label10.Click += new System.EventHandler(this.label10_Click);
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(8, 307);
            this.label11.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(79, 13);
            this.label11.TabIndex = 48;
            this.label11.Text = "Payment Code:";
            // 
            // textPaymentCode
            // 
            this.textPaymentCode.Location = new System.Drawing.Point(90, 306);
            this.textPaymentCode.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textPaymentCode.Name = "textPaymentCode";
            this.textPaymentCode.Size = new System.Drawing.Size(250, 20);
            this.textPaymentCode.TabIndex = 47;
            this.textPaymentCode.Text = "MM315244796579015388";
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(38, 282);
            this.label12.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(46, 13);
            this.label12.TabIndex = 46;
            this.label12.Text = "Amount:";
            // 
            // textAmountPos
            // 
            this.textAmountPos.Location = new System.Drawing.Point(90, 280);
            this.textAmountPos.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textAmountPos.Name = "textAmountPos";
            this.textAmountPos.Size = new System.Drawing.Size(250, 20);
            this.textAmountPos.TabIndex = 45;
            this.textAmountPos.Text = "55000";
            // 
            // label13
            // 
            this.label13.AutoSize = true;
            this.label13.Location = new System.Drawing.Point(30, 260);
            this.label13.Margin = new System.Windows.Forms.Padding(2, 0, 2, 0);
            this.label13.Name = "label13";
            this.label13.Size = new System.Drawing.Size(52, 13);
            this.label13.TabIndex = 50;
            this.label13.Text = "Endpoint:";
            // 
            // textEndpointPos
            // 
            this.textEndpointPos.Location = new System.Drawing.Point(90, 256);
            this.textEndpointPos.Margin = new System.Windows.Forms.Padding(2, 3, 2, 3);
            this.textEndpointPos.Name = "textEndpointPos";
            this.textEndpointPos.Size = new System.Drawing.Size(342, 20);
            this.textEndpointPos.TabIndex = 49;
            this.textEndpointPos.Text = "https://test-payment.momo.vn/pay/pos";
            // 
            // button2
            // 
            this.button2.Location = new System.Drawing.Point(98, 493);
            this.button2.Margin = new System.Windows.Forms.Padding(2);
            this.button2.Name = "button2";
            this.button2.Size = new System.Drawing.Size(140, 23);
            this.button2.TabIndex = 51;
            this.button2.Text = "Query Status";
            this.button2.UseVisualStyleBackColor = true;
            this.button2.Click += new System.EventHandler(this.button2_Click);
            // 
            // button3
            // 
            this.button3.Location = new System.Drawing.Point(368, 532);
            this.button3.Margin = new System.Windows.Forms.Padding(2);
            this.button3.Name = "button3";
            this.button3.Size = new System.Drawing.Size(140, 23);
            this.button3.TabIndex = 52;
            this.button3.Text = "Refund";
            this.button3.UseVisualStyleBackColor = true;
            this.button3.Click += new System.EventHandler(this.button3_Click);
            // 
            // MoMoForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(528, 611);
            this.Controls.Add(this.button3);
            this.Controls.Add(this.button2);
            this.Controls.Add(this.label13);
            this.Controls.Add(this.textEndpointPos);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.textPaymentCode);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.textAmountPos);
            this.Controls.Add(this.label10);
            this.Controls.Add(this.label9);
            this.Controls.Add(this.payOfflinebtn);
            this.Controls.Add(this.label8);
            this.Controls.Add(this.textReturn);
            this.Controls.Add(this.label7);
            this.Controls.Add(this.textNotify);
            this.Controls.Add(this.label6);
            this.Controls.Add(this.textOrderInfo);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.textPartnerCode);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.textAccessKey);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.textEndpoint);
            this.Controls.Add(this.textPubkey);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.textAmount);
            this.Controls.Add(this.button1);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(2);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "MoMoForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Test";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox textAmount;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.TextBox textPubkey;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textEndpoint;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox textAccessKey;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox textPartnerCode;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox textOrderInfo;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox textNotify;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox textReturn;
        private System.Windows.Forms.Button payOfflinebtn;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.TextBox textPaymentCode;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.TextBox textAmountPos;
        private System.Windows.Forms.Label label13;
        private System.Windows.Forms.TextBox textEndpointPos;
        private System.Windows.Forms.Button button2;
        private System.Windows.Forms.Button button3;
    }
}