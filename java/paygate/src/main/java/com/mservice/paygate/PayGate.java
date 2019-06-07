package com.mservice.paygate;

import com.google.gson.Gson;
import com.mservice.paygate.constants.Parameter;
import com.mservice.paygate.model.*;
import com.mservice.paygate.processor.allinone.CaptureMoMoProcess;
import com.mservice.paygate.processor.allinone.QueryStatusProcess;
import com.mservice.paygate.utils.Console;
import com.mservice.paygate.utils.Encoder;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo
 */
public class PayGate {

    /***
     * Select environment
     * You can load config from file
     * MoMo only provide once endpoint for each envs: dev and prod
     * @param target
     * @return
     */
    public static Environment selectEnv(String target) {
        switch (target) {
            case "dev":
                PartnerInfo devInfo = new PartnerInfo("MOMO", "F8BBA842ECF85", "K951B6PE1waDMi640xX08PD3vg6EkVlz");
                Environment dev = new Environment("https://testing.momo.vn/gw_payment/transactionProcessor", devInfo, "development");
                return dev;
            case "prod":
                PartnerInfo productionInfo = new PartnerInfo("MOMO", "F8BBA842ECF85", "K951B6PE1waDMi640xX08PD3vg6EkVlz");
                Environment production = new Environment("https://payment.momo.vn/gw_payment/transactionProcessor", productionInfo, "production");
                return production;
            default:
                throw new IllegalArgumentException("MoMo doesnt provide other environment: dev and prod");
        }
    }

    public static void main(String... args) throws Exception {
        String requestId = String.valueOf(System.currentTimeMillis());
        String orderId = String.valueOf(System.currentTimeMillis());
        String amount = "1500000";
        String orderInfo = "Pay With MoMo";
        String returnURL = "https://google.com.vn";
        String notifyURL = "https://google.com.vn";
        String extraData = "";

        Environment environment = selectEnv("dev");

        CaptureMoMoProcess m2Processor = new CaptureMoMoProcess(environment);

        Console.log("========================== START CAPTURE MOMO WALLET ==================");
        CaptureMoMoRequest captureMoMoRequest = m2Processor.createPaymentCreationRequest(orderId, requestId, amount, orderInfo, returnURL, notifyURL, extraData);

        CaptureMoMoResponse captureMoMoResponse = m2Processor.execute(captureMoMoRequest);

        // Your handler
        if (captureMoMoResponse.getErrorCode() != 0) {
            Console.debug("errorCode::", captureMoMoResponse.getErrorCode() + "");
            Console.debug("errorMessage::", captureMoMoResponse.getMessage());
            Console.debug("localMessage::", captureMoMoResponse.getLocalMessage());
        } else {
            // To do something here ...
            // You can get payUrl to redirect new tab browser or open link on iframe to serve payment
            // Using deeplink to open MoMo App
            // Using qrCodeUrl to generate QrCode with data is this
            Console.debug("payURL::", captureMoMoResponse.getPayUrl() + "");
            Console.debug("deepLink::", captureMoMoResponse.getDeeplink());
            Console.debug("qrCodeURL::", captureMoMoResponse.getQrCodeUrl());
        }

        Console.log("========================== END CAPTURE MOMO WALLET ==================");


        Console.log("========================== START QUERY QUERY STATUS ==================");
        QueryStatusProcess queryStatusProcess = new QueryStatusProcess(environment);

        QueryStatusTransactionRequest queryStatusRequest = queryStatusProcess.createQueryRequest(orderId, requestId);
        QueryStatusTransactionResponse queryStatusResponse = queryStatusProcess.execute(queryStatusRequest);
        // Your handler
        Console.error("errorCode::", queryStatusResponse.getErrorCode() + "");
        Console.error("errorMessage::", queryStatusResponse.getMessage());
        Console.error("localMessage::", queryStatusResponse.getLocalMessage());
        Console.log("========================== END QUERY QUERY STATUS ==================");


        Console.log("========================== START CREATE RSA TEST DATA ==================");
        // current version is 2.0
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkpa+qMXS6O11x7jBGo9W3yxeHEsAdyDE" + 
        		"40UoXhoQf9K6attSIclTZMEGfq6gmJm2BogVJtPkjvri5/j9mBntA8qKMzzanSQaBEbr8FyByHnf226dsL" + 
        		"t1RbJSMLjCd3UC1n0Yq8KKvfHhvmvVbGcWfpgfo7iQTVmL0r1eQxzgnSq31EL1yYNMuaZjpHmQuT2" + 
        		"4Hmxl9W9enRtJyVTUhwKhtjOSOsR03sMnsckpFT9pn1/V9BE2Kf3rFGqc6JukXkqK6ZW9mtmGLSq3" + 
        		"K+JRRq2w8PVmcbcvTr/adW4EL2yc1qk9Ec4HtiDhtSYd6/ov8xLVkKAQjLVt7Ex3/agRPfPrNwIDAQAB";
		String partnerCode = "MOMOIQA420180417";
        String phoneNumber = "0963181714";
        long amount1 = 30000;
        String username = "nhat.nguyen";
        // this is test, use your order_id
        final String hashRSA = generateRSA(phoneNumber, requestId, requestId, username, partnerCode, amount1, publicKey);
        Console.log(" RSA hash data:: ", hashRSA, " ");
        Console.log("========================== END CREATE RSA TEST DATA ==================");
    }


    /**
     * @author nhat.nguyen
     */
    public static String generateRSA(String phoneNumber, String billId, String tranId, String username, String partnerCode, long amount, String publicKey) throws Exception {
        // current version of Parameter key name is 2.0
        Map<String, Object> rawData = new HashMap<>();
        rawData.put(Parameter.CUSTOMER_NUMBER, phoneNumber);
        rawData.put(Parameter.PARTNER_REF_ID, billId);
        rawData.put(Parameter.PARTNER_TRANS_ID, tranId);
        rawData.put(Parameter.USERNAME, username);
        rawData.put(Parameter.PARTNER_CODE, partnerCode);
        rawData.put(Parameter.AMOUNT, amount);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(rawData);
        byte[] testByte = jsonStr.getBytes(StandardCharsets.UTF_8);
        return Encoder.encryptRSA(testByte, publicKey);
    }
}
