using System;
using System.IO;
using System.Text;
using System.Windows.Forms;
using System.Net;
using Newtonsoft.Json.Linq;

namespace MoMo
{
    public partial class MoMoForm : Form
    {
        
        public MoMoForm()
        {
            InitializeComponent();
        }
       

        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
        private void button1_Click(object sender, EventArgs e)
        {
            //request params need to request to MoMo system
            string endpoint = textEndpoint.Text.Equals("") ? "https://test-payment.momo.vn/gw_payment/transactionProcessor" : textEndpoint.Text;
            string partnerCode = textPartnerCode.Text;
            string accessKey = textAccessKey.Text;
            string serectkey = "nqQiVSgDMy809JoPF6OzP5OdBUB550Y4";
            string orderInfo = textOrderInfo.Text;
            string returnUrl = textReturn.Text;
            string notifyurl = textNotify.Text;

            string amount = textAmount.Text;
            string orderid = Guid.NewGuid().ToString();
            string requestId = Guid.NewGuid().ToString();
            string extraData = ""; 

            //Before sign HMAC SHA256 signature
            string rawHash = "partnerCode="+ 
                partnerCode + "&accessKey="+
                accessKey+ "&requestId=" +
                requestId+ "&amount=" + 
                amount + "&orderId="+
                orderid + "&orderInfo="+ 
                orderInfo + "&returnUrl="+ 
                returnUrl + "&notifyUrl=" + 
                notifyurl + "&extraData="+
                extraData;

            log.Debug("rawHash = "+ rawHash);

            MoMoSecurity crypto = new MoMoSecurity();
            //sign signature SHA256
            string signature = crypto.signSHA256(rawHash, serectkey);
            log.Debug("Signature = " + signature);

            //build body json request
            JObject message = new JObject
            {
                { "partnerCode", partnerCode },
                { "accessKey", accessKey },
                { "requestId", requestId },
                { "amount", amount },
                { "orderId", orderid },
                { "orderInfo", orderInfo },
                { "returnUrl", returnUrl },
                { "notifyUrl", notifyurl },
                { "extraData", extraData },
                { "requestType", "captureMoMoWallet" },
                { "signature", signature }

            };
            log.Debug("Json request to MoMo: " + message.ToString());
            string responseFromMomo = PaymentRequest.sendPaymentRequest(endpoint, message.ToString());
         
            JObject jmessage = JObject.Parse(responseFromMomo);
            log.Debug("Return from MoMo: " + jmessage.ToString());
            DialogResult result = MessageBox.Show(responseFromMomo, "Open in browser", MessageBoxButtons.OKCancel);
            if (result == DialogResult.OK)
            {
                //yes...
                System.Diagnostics.Process.Start(jmessage.GetValue("payUrl").ToString());
            }
            else if (result == DialogResult.Cancel)
            {
                //no...
            }
        }

        private void payOfflinebtn_Click(object sender, EventArgs e)
        {
            string endpoint = textEndpointPos.Text;
            string partnerCode = "MOMO5RGX20191128";
            string merchantRefId = Guid.NewGuid().ToString();
            string amount = textAmountPos.Text;
            string paymentCode = textPaymentCode.Text;
            string storeId = "1";
            string storeName = "cua hang netika";
            string description = "";
            string version = "2.0";
            string publicKey = textPubkey.Text;

            Console.Out.WriteLine(publicKey);
            //get hash
            MoMoSecurity momoCrypto = new MoMoSecurity();
            string hash = momoCrypto.getHash(partnerCode, merchantRefId, amount,
                paymentCode, storeId,
                storeName, publicKey);
            
            //request to MoMo
            string jsonRequest = "{\"partnerCode\":\"" +
                partnerCode + "\",\"partnerRefId\":\"" +
                merchantRefId + "\",\"description\":\"" +
                description + "\",\"amount\":" +
                amount + ",\"version\":" +
                version + ",\"hash\":\"" +
                hash + "\"}";

            log.Debug(jsonRequest);

            //response from MoMo
            string responseFromMomo = PaymentRequest.sendPaymentRequest(endpoint, jsonRequest.ToString());

            log.Debug(responseFromMomo);
            log.Debug(hash);

            JObject jmessage = JObject.Parse(responseFromMomo);
            log.Debug("Return from MoMo: " + jmessage.ToString());
            DialogResult result = MessageBox.Show(responseFromMomo, "MoMo response", MessageBoxButtons.OKCancel);
            if (result == DialogResult.OK)
            {
                //yes...
            }
            else if (result == DialogResult.Cancel)
            {
                //no...
            }
        }

        private void label10_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            string endpoint = "https://test-payment.momo.vn/pay/query-status";
            string partnerCode = "MOMO5RGX20191128";
            string merchantRefId = "1519717410468";
            string version = "2.0";
            string publicKey = textPubkey.Text;
            string requestId = Guid.NewGuid().ToString();
            string description = "";

            //get hash
            MoMoSecurity momoCrypto = new MoMoSecurity();
            string hash = momoCrypto.buildQueryHash(partnerCode, merchantRefId, requestId,
                publicKey);

            //request to MoMo
            string jsonRequest = "{\"partnerCode\":\"" +
                partnerCode + "\",\"partnerRefId\":\"" +
                merchantRefId + "\",\"description\":\"" +
                description + "\",\"version\":" +
                version + ",\"hash\":\"" +
                hash + "\"}";
            log.Debug(jsonRequest);

            //response from MoMo
            string responseFromMomo = PaymentRequest.sendPaymentRequest(endpoint, jsonRequest.ToString());
            log.Debug(responseFromMomo);
            log.Debug(hash);
            JObject jmessage = JObject.Parse(responseFromMomo);
            log.Debug("Return from MoMo: " + jmessage.ToString());
            DialogResult result = MessageBox.Show(responseFromMomo, "MoMo response", MessageBoxButtons.OKCancel);
            if (result == DialogResult.OK)
            {
                //yes...
            }
            else if (result == DialogResult.Cancel)
            {
                //no...
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            string endpoint = "https://test-payment.momo.vn/pay/refund";
            string partnerCode = "MOMO5RGX20191128";
            string merchantRefId = "1519717410468";
            string momoTransId = "137489899";
            string version = "2.0";
            string publicKey = textPubkey.Text;
            string requestId = Guid.NewGuid().ToString();
            string description = "";
            long amount = 5000;

            //get hash
            MoMoSecurity momoCrypto = new MoMoSecurity();
            string hash = momoCrypto.buildRefundHash(partnerCode, merchantRefId, momoTransId, amount,
                description,
                publicKey);

            //request to MoMo
            string jsonRequest = "{\"partnerCode\":\"" +
                partnerCode + "\",\"requestId\":\"" +
                requestId +"\",\"version\":" +
                version + ",\"hash\":\"" +
                hash + "\"}";
            log.Debug(jsonRequest);

            //response from MoMo
            string responseFromMomo = PaymentRequest.sendPaymentRequest(endpoint, jsonRequest.ToString());
            log.Debug(responseFromMomo);
            log.Debug(hash);
            JObject jmessage = JObject.Parse(responseFromMomo);
            log.Debug("Return from MoMo: " + jmessage.ToString());
            DialogResult result = MessageBox.Show(responseFromMomo, "MoMo response", MessageBoxButtons.OKCancel);
            if (result == DialogResult.OK)
            {
                //yes...
            }
            else if (result == DialogResult.Cancel)
            {
                //no...
            }
        }

        private void textPartnerCode_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
