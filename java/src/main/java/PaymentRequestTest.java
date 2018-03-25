import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Formatter;


/**
 * Created by khoivu on 5/24/16.
 */
public class PaymentRequestTest {

    private static final String HMAC_SHA256 = "HmacSHA256";

    private final static String Endpoint = "https://payment.momo.vn:18081/gw_payment/transactionProcessor";
    private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }

        return sb.toString();
    }

    public static String signHmacSHA256(String data, String secretKey) throws Exception {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
        return toHexString(rawHmac);
    }

    public static void main(String[] args) throws Exception {
        String orderId = System.currentTimeMillis()+"";
        String requestId = System.currentTimeMillis()+"";
        String orderInfo = "";
        String extraData = "";
        String amount = "250000";
        String partnerCode = "123456";
        String accessKey = "F8BBA842ECF85";
        String serectKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz";
        String notifyUrl = "https://www.123phim.vn/";
        String returnUrl = "https://www.123phim.vn/";

        String dataSignature = "partnerCode="+partnerCode
                                +"&accessKey="+accessKey
                                +"&requestId="+ requestId
                                +"&amount="+amount
                                +"&orderId="+orderId
                                +"&orderInfo="+orderInfo
                                +"&returnUrl="+returnUrl
                                +"&notifyUrl="+notifyUrl
                                +"&extraData="+extraData;
        String signature = signHmacSHA256(dataSignature, serectKey);

        String jsonString =  "{\n" +
                "  \"accessKey\": \""+accessKey+"\",\n" +
                "  \"partnerCode\": \""+partnerCode+"\",\n" +
                "  \"requestType\": \"captureMoMoWallet\",\n" +
                "  \"notifyUrl\": \""+notifyUrl+"\",\n" +
                "  \"returnUrl\": \""+returnUrl+"\",\n" +
                "  \"orderId\": \""+orderId+"\",\n" +
                "  \"amount\": \""+amount+"\",\n" +
                "  \"orderInfo\": \"\",\n" +
                "  \"requestId\": \""+requestId+"\",\n" +
                "  \"extraData\": \""+extraData+"\",\n" +
                "  \"signature\": \""+signature+"\"\n" +
                "}";

        System.out.println(jsonString);
        try {
            String       postUrl       = Endpoint;// put in your url
            HttpClient   httpClient    = HttpClientBuilder.create().build();
            HttpPost     post          = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(jsonString);
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            HttpResponse  response = httpClient.execute(post);
            InputStream returnResponse = response.getEntity().getContent();
            String result = IOUtils.toString(returnResponse, "UTF-8");

            System.out.println(result);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
