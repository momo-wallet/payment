package com.mservice.paygate;

import com.mservice.paygate.model.*;
import com.mservice.paygate.processor.Processor;
import com.mservice.paygate.utils.Console;

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

        Console.log("========================== START CAPTURE MOMO WALLET ==================");
        CaptureMoMoRequest captureMoMoRequest = Processor.createCaptureMoMoRequest(orderId, requestId, amount, orderInfo, returnURL, notifyURL, extraData, environment.getPartnerInfo());
        CaptureMoMoResponse captureMoMoResponse = Processor.getCaptureMoMoResponse(environment, captureMoMoRequest);
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
        QueryStatusTransactionRequest queryStatusRequest = Processor.createQueryTransactionRequest(orderId, requestId, environment.getPartnerInfo());
        QueryStatusTransactionResponse queryStatusResponse = Processor.getQueryStatusResponse(environment, queryStatusRequest);
        // Your handler
        Console.error("errorCode::", queryStatusResponse.getErrorCode() + "");
        Console.error("errorMessage::", queryStatusResponse.getMessage());
        Console.error("localMessage::", queryStatusResponse.getLocalMessage());
        Console.log("========================== END QUERY QUERY STATUS ==================");


        Console.log("========================== START CREATE RSA TEST DATA ==================");
        // current version is 2.0
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkpa+qMXS6O11x7jBGo9W3yxeHEsAdyDE40UoXhoQf9K6attSIclTZMEGfq6gmJm2BogVJtPkjvri5/j9mBntA8qKMzzanSQaBEbr8FyByHnf226dsLt1RbJSMLjCd3UC1n0Yq8KKvfHhvmvVbGcWfpgfo7iQTVmL0r1eQxzgnSq31EL1yYNMuaZjpHmQuT24Hmxl9W9enRtJyVTUhwKhtjOSOsR03sMnsckpFT9pn1/V9BE2Kf3rFGqc6JukXkqK6ZW9mtmGLSq3K+JRRq2w8PVmcbcvTr/adW4EL2yc1qk9Ec4HtiDhtSYd6/ov8xLVkKAQjLVt7Ex3/agRPfPrNwIDAQAB";
		String partnerCode = "CGV19072017";
        String billId = requestId;		// this is test, use your order id
        String phoneNumber = "0963181714";
        long amount1 = 30000;
        String username = "nhat.nguyen";
        String tranId = requestId;		// this is test, use transaction id
        final String hashRSA = Processor.generateRSA(phoneNumber, billId, tranId, username, partnerCode, amount1, publicKey);
        Console.log(" RSA hash data:: ", hashRSA, " ");
        Console.log("========================== END CREATE RSA TEST DATA ==================");
    }
}
