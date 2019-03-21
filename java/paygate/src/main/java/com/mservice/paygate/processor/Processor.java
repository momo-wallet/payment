package com.mservice.paygate.processor;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.mservice.paygate.constants.Parameter;
import com.mservice.paygate.constants.RequestType;
import com.mservice.paygate.model.CaptureMoMoRequest;
import com.mservice.paygate.model.CaptureMoMoResponse;
import com.mservice.paygate.model.Environment;
import com.mservice.paygate.model.PartnerInfo;
import com.mservice.paygate.model.PayATMRequest;
import com.mservice.paygate.model.PayATMResponse;
import com.mservice.paygate.model.PaymentResponse;
import com.mservice.paygate.model.QueryStatusTransactionRequest;
import com.mservice.paygate.model.QueryStatusTransactionResponse;
import com.mservice.paygate.model.RefundATMMoMoRequest;
import com.mservice.paygate.model.RefundMoMoRequest;
import com.mservice.paygate.utils.Console;
import com.mservice.paygate.utils.Encoder;

@SuppressWarnings("static-access")
public class Processor {

    public static PayATMRequest createPayWithATMRequest(String requestId, String orderId, String bankCode, String amount, String orderInfo, String returnUrl,
                                                        String notifyUrl, String extra, PartnerInfo partnerInfo) {
        String dataCryption
                = Parameter.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() + "&"
                + Parameter.ACCESS_KEY + "=" + partnerInfo.getAccessKey() + "&"
                + Parameter.REQUEST_ID + "=" + requestId + "&"
                + Parameter.BANK_CODE + "=" + bankCode + "&"
                + Parameter.AMOUNT + "=" + amount + "&"
                + Parameter.ORDER_ID + "=" + orderId + "&"
                + Parameter.ORDER_INFO + "=" + orderInfo + "&"
                + Parameter.RETURN_URL + "=" + returnUrl + "&"
                + Parameter.NOTIFY_URL + "=" + notifyUrl + "&"
                + Parameter.EXTRA_DATA + "=" + extra + "&"
                + Parameter.REQUEST_TYPE + "=" + RequestType.PAY_WITH_ATM;
        try {
            String signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());

            PayATMRequest payATMRequest = new PayATMRequest();
            payATMRequest.setPartnerCode(partnerInfo.getPartnerCode());
            payATMRequest.setAccessKey(partnerInfo.getAccessKey());
            payATMRequest.setAmount(amount);
            payATMRequest.setRequestId(requestId);
            payATMRequest.setOrderId(orderId);
            payATMRequest.setReturnUrl(returnUrl);
            payATMRequest.setNotifyUrl(notifyUrl);
            payATMRequest.setOrderInfo(orderInfo);
            payATMRequest.setBankCode(bankCode);
            payATMRequest.setRequestType(RequestType.PAY_WITH_ATM);
            payATMRequest.setSignature(signature);
            return payATMRequest;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public static RefundATMMoMoRequest createRefundMoMoATMRequest(String requestId, String orderId, String bankCode, String amount, String momoTransId, PartnerInfo partnerInfo) {
        RefundATMMoMoRequest refundATMMoMoRequest = new RefundATMMoMoRequest();
        String dataCryption
                = Parameter.PARTNER_CODE + "=" + refundATMMoMoRequest.getPartnerCode() + "&"
                + Parameter.ACCESS_KEY + "=" + refundATMMoMoRequest.getAccessKey() + "&"
                + Parameter.REQUEST_ID + "=" + refundATMMoMoRequest.getRequestId() + "&"
                + Parameter.BANK_CODE + "=" + refundATMMoMoRequest.getBankCode() + "&"
                + Parameter.AMOUNT + "=" + refundATMMoMoRequest.getAmount() + "&"
                + Parameter.ORDER_ID + "=" + refundATMMoMoRequest.getOrderId() + "&"
                + Parameter.TRANS_ID + "=" + refundATMMoMoRequest.getTransId() + "&"
                + Parameter.REQUEST_TYPE + "=" + refundATMMoMoRequest.getRequestType();
        String signature = "";
        try {
            signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());
            refundATMMoMoRequest.setPartnerCode(partnerInfo.getPartnerCode());
            refundATMMoMoRequest.setAccessKey(partnerInfo.getAccessKey());
            refundATMMoMoRequest.setRequestId(requestId);
            refundATMMoMoRequest.setAmount(amount);
            refundATMMoMoRequest.setBankCode(bankCode);
            refundATMMoMoRequest.setOrderId(orderId);
            refundATMMoMoRequest.setTransId(momoTransId);
            refundATMMoMoRequest.setSignature(signature);
            refundATMMoMoRequest.setRequestType(RequestType.REFUND_ATM);

            return refundATMMoMoRequest;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public static RefundMoMoRequest createRefundMoMoRequest(String requestId, String orderId, String amount, String momoTransId, PartnerInfo partnerInfo) {
        RefundMoMoRequest refundMoMoRequest = new RefundMoMoRequest();
        String dataCryption =
                Parameter.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                        "&" + Parameter.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                        "&" + Parameter.REQUEST_ID + "=" + requestId +
                        "&" + Parameter.AMOUNT + "=" + amount +
                        "&" + Parameter.ORDER_ID + "=" + orderId +
                        "&" + Parameter.TRANS_ID + "=" + momoTransId +
                        "&" + Parameter.REQUEST_TYPE + "=" + RequestType.REFUND_MOMO_WALLET;
        String signature = "";
        try {
            signature = Encoder.signHmacSHA256(dataCryption, partnerInfo.getSecretKey());
            refundMoMoRequest.setPartnerCode(partnerInfo.getPartnerCode());
            refundMoMoRequest.setAccessKey(partnerInfo.getAccessKey());
            refundMoMoRequest.setRequestId(requestId);
            refundMoMoRequest.setAmount(amount);
            refundMoMoRequest.setOrderId(orderId);
            refundMoMoRequest.setTransId(momoTransId);
            refundMoMoRequest.setSignature(signature);
            refundMoMoRequest.setRequestType(RequestType.REFUND_MOMO_WALLET);

            return refundMoMoRequest;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    public static QueryStatusTransactionRequest createQueryTransactionRequest(String requestId, String orderId, PartnerInfo partnerInfo) {
        QueryStatusTransactionRequest request = new QueryStatusTransactionRequest();
        String rawData =
                Parameter.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                        "&" + Parameter.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                        "&" + Parameter.REQUEST_ID + "=" + requestId +
                        "&" + Parameter.ORDER_ID + "=" + orderId +
                        "&" + Parameter.REQUEST_TYPE + "=" + RequestType.TRANSACTION_STATUS;
        String signature = "";
        try {
            Console.debug("createQueryStatusRequest::rawDataBeforeHash::" + rawData);
            signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("createQueryStatusRequest::signature::" + signature);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAccessKey(partnerInfo.getAccessKey());
        request.setPartnerCode(partnerInfo.getPartnerCode());
        request.setOrderId(orderId);
        request.setRequestId(requestId);
        request.setRequestType(RequestType.TRANSACTION_STATUS);
        request.setSignature(signature);
        return request;
    }

    public static CaptureMoMoRequest createCaptureMoMoRequest(String orderId, String requestId, String amount, String orderInfo, String returnUrl, String notifyUrl, String extraData, PartnerInfo partnerInfo) {
        try {
            String rawData =
                    Parameter.PARTNER_CODE + "=" + partnerInfo.getPartnerCode() +
                            "&" + Parameter.ACCESS_KEY + "=" + partnerInfo.getAccessKey() +
                            "&" + Parameter.REQUEST_ID + "=" + requestId +
                            "&" + Parameter.AMOUNT + "=" + amount +
                            "&" + Parameter.ORDER_ID + "=" + orderId +
                            "&" + Parameter.ORDER_INFO + "=" + orderInfo +
                            "&" + Parameter.RETURN_URL + "=" + returnUrl +
                            "&" + Parameter.NOTIFY_URL + "=" + notifyUrl +
                            "&" + Parameter.EXTRA_DATA + "=" + extraData;
            Console.debug("createCaptureMoMoRequest::rawDataBeforeHash::" + rawData);
            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("createCaptureMoMoRequest::signature::" + signature);

            CaptureMoMoRequest captureMoMoRequest = new CaptureMoMoRequest();
            captureMoMoRequest.setAccessKey(partnerInfo.getAccessKey());
            captureMoMoRequest.setRequestId(requestId);
            captureMoMoRequest.setPartnerCode(partnerInfo.getPartnerCode());
            captureMoMoRequest.setRequestType(RequestType.CAPTURE_MOMO_WALLET);
            captureMoMoRequest.setNotifyUrl(notifyUrl);
            captureMoMoRequest.setReturnUrl(returnUrl);
            captureMoMoRequest.setOrderId(orderId);
            captureMoMoRequest.setAmount(amount);
            captureMoMoRequest.setSignature(signature);
            captureMoMoRequest.setExtraData(extraData);
            captureMoMoRequest.setOrderInfo(orderInfo);
            return captureMoMoRequest;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }


    /*** Send to MoMo Service**/
	public static CaptureMoMoResponse getCaptureMoMoResponse(Environment env, CaptureMoMoRequest captureMoMoRequest) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        Execute execute = new Execute();
        String payload = execute.getGson().toJson(captureMoMoRequest, CaptureMoMoRequest.class);
        String response = execute.sendToMoMo(env, payload);
        CaptureMoMoResponse captureMoMoResponse = execute.getGson().fromJson(response, CaptureMoMoResponse.class);
        errorMoMoProcess(captureMoMoResponse.getErrorCode());
        if (captureMoMoResponse.getErrorCode() == 0) {
            String rawData =
                    Parameter.REQUEST_ID + "=" + captureMoMoResponse.getRequestId() +
                            "&" + Parameter.ORDER_ID + "=" + captureMoMoResponse.getOrderId() +
                            "&" + Parameter.MESSAGE + "=" + captureMoMoResponse.getMessage() +
                            "&" + Parameter.LOCAL_MESSAGE + "=" + captureMoMoResponse.getLocalMessage() +
                            "&" + Parameter.PAY_URL + "=" + captureMoMoResponse.getPayUrl() +
                            "&" + Parameter.ERROR_CODE + "=" + captureMoMoResponse.getErrorCode() +
                            "&" + Parameter.REQUEST_TYPE + "=" + RequestType.CAPTURE_MOMO_WALLET;

            Console.debug("getCaptureMoMoResponse::partnerRawDataBeforeHash::" + rawData);
            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("getCaptureMoMoResponse::partnerSignature::" + signature);
            Console.debug("getCaptureMoMoResponse::momoSignature::" + captureMoMoResponse.getSignature());

            if (signature.equals(captureMoMoResponse.getSignature())) {
                return captureMoMoResponse;
            } else {
                throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
            }
        }
        return captureMoMoResponse;
    }

    public static QueryStatusTransactionResponse getQueryStatusResponse(Environment env, QueryStatusTransactionRequest queryStatusRequest) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        String payload = Execute.getGson().toJson(queryStatusRequest, CaptureMoMoRequest.class);
        String response = Execute.sendToMoMo(env, payload);
        QueryStatusTransactionResponse queryStatusResponse = Execute.getGson().fromJson(response, QueryStatusTransactionResponse.class);

        errorMoMoProcess(queryStatusResponse.getErrorCode());
        String rawData = Parameter.PARTNER_CODE + "=" + queryStatusResponse.getPartnerCode() +
                "&" + Parameter.ACCESS_KEY + "=" + queryStatusResponse.getAccessKey() +
                "&" + Parameter.REQUEST_ID + "=" + queryStatusResponse.getRequestId() +
                "&" + Parameter.ORDER_ID + "=" + queryStatusResponse.getOrderId() +
                "&" + Parameter.ERROR_CODE + "=" + queryStatusResponse.getErrorCode() +
                "&" + Parameter.TRANS_ID + "=" + queryStatusResponse.getTransId() +
                "&" + Parameter.AMOUNT + "=" + queryStatusResponse.getAmount() +
                "&" + Parameter.MESSAGE + "=" + queryStatusResponse.getMessage() +
                "&" + Parameter.LOCAL_MESSAGE + "=" + queryStatusResponse.getLocalMessage() +
                "&" + Parameter.REQUEST_TYPE + "=" + RequestType.TRANSACTION_STATUS +
                "&" + Parameter.PAY_TYPE + "=" + queryStatusResponse.getPayType() +
                "&" + Parameter.EXTRA_DATA + "=" + queryStatusResponse.getExtraData();
        Console.debug("getQueryStatusResponse::rawDataBeforeHash::" + rawData);
        String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
        Console.debug("getQueryStatusResponse::signature::" + signature);

        if (signature.equals(queryStatusResponse.getSignature())) {
            return queryStatusResponse;
        } else {
            throw new Exception("Wrong signature from MoMo side - please contact with us");
        }
    }

    public static PayATMResponse getPayMoMoATMResponse(Environment env, PayATMRequest payATMRequest) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        Execute execute = new Execute();
        String payload = execute.getGson().toJson(payATMRequest, PayATMRequest.class);
        String response = execute.sendToMoMo(env, payload);
        PayATMResponse payATMResponse = execute.getGson().fromJson(response, PayATMResponse.class);
        errorMoMoProcess(payATMResponse.getErrorCode());

        if (payATMResponse.getErrorCode() == 0) {
            String rawData =
                    Parameter.PARTNER_CODE + "=" + payATMRequest.getPartnerCode() +
                            "&" + Parameter.ACCESS_KEY + "=" + payATMRequest.getAccessKey() +
                            "&" + Parameter.REQUEST_ID + "=" + payATMRequest.getRequestId() +
                            "&" + Parameter.PAY_URL + "=" + payATMResponse.getPayUrl() +
                            "&" + Parameter.ERROR_CODE + "=" + payATMResponse.getErrorCode() +
                            "&" + Parameter.ORDER_ID + "=" + payATMResponse.getOrderId() +
                            "&" + Parameter.MESSAGE + "=" + payATMResponse.getMessage() +
                            "&" + Parameter.LOCAL_MESSAGE + "=" + payATMResponse.getLocalMessage() +
                            "&" + Parameter.REQUEST_TYPE + "=" + RequestType.PAY_WITH_ATM;

            Console.debug("getPayATMMoMoResponse::partnerRawDataBeforeHash::" + rawData);
            String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
            Console.debug("getPayATMMoMoResponse::partnerSignature::" + signature);
            Console.debug("getPayATMMoMoResponse::momoSignature::" + payATMResponse.getSignature());

            if (signature.equals(payATMResponse.getSignature())) {
                return payATMResponse;
            } else {
                throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
            }
        }
        return payATMResponse;
    }

    /**
     * After end-user do pay order, MoMo will return result to partner by two ways:
     * - 1. On browser, MoMo website will be redirected to Partner website by returnUrl field which passed to api captureMoMoWallet
     * - 2. MoMo uses notifyUrl field to send http request with POST method to partner endpoint
     *
     * For returnUrl: we will attach and fill data to params: refer PAYLOAD
     * For notifyUrl: we will build request with information:
     * METHOD: POST
     * HEADER: CONTENT-TYPE: X-WWW-FORM-URLENCODED
     *          CHARSET : UTF-8
     *
     * PAYLOAD: partnerCode=$partnerCode&accessKey=$accessKey&requestId=$requestId&amount=$amount&orderId=$oderId&orderInfo=$orderInfo&orderType=$orderType&transId=$transId&message=$message&localMessage=$localMessage&responseTime=$responseTime&errorCode=$errorCode& payType=$payType&extraData=$extraData
     *
     *
     * <p>
     * You can use this function to get and validate result
     * Using for two commands: captureMoMoWallet and payWithATM
     * ErrorCode is key to detect transaction is success or fail
     *
     * if ErrorCode = 0 mean transaction is payment success else fail
     *
     * @param env
     * @param paymentResponse
     * @return
     * @throws Exception
     */
    public static PaymentResponse resultCaptureMoMoWallet(Environment env, PaymentResponse paymentResponse) throws Exception {
        PartnerInfo partnerInfo = env.getPartnerInfo();
        String rawData
                = Parameter.PARTNER_CODE + "=" + paymentResponse.getPartnerCode() + "&"
                + Parameter.ACCESS_KEY + "=" + paymentResponse.getAccessKey() + "&"
                + Parameter.REQUEST_ID + "=" + paymentResponse.getRequestId() + "&"
                + Parameter.AMOUNT + "=" + paymentResponse.getAmount() + "&"
                + Parameter.ORDER_ID + "=" + paymentResponse.getOrderId() + "&"
                + Parameter.ORDER_INFO + "=" + paymentResponse.getOrderInfo() + "&"
                + Parameter.ORDER_TYPE + "=" + paymentResponse.getOrderType() + "&"
                + Parameter.TRANS_ID + "=" + paymentResponse.getTransId() + "&"
                + Parameter.MESSAGE + "=" + paymentResponse.getMessage() + "&"
                + Parameter.LOCAL_MESSAGE + "=" + paymentResponse.getLocalMessage() + "&"
                + Parameter.DATE + "=" + paymentResponse.getResponseDate() + "&"
                + Parameter.ERROR_CODE + "=" + paymentResponse.getErrorCode() + "&"
                + Parameter.PAY_TYPE + "=" + paymentResponse.getPayType() + "&"
                + Parameter.EXTRA_DATA + "=" + paymentResponse.getExtraData();

        Console.debug("resultCaptureMoMoWallet::partnerRawDataBeforeHash::" + rawData);
        String signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());
        Console.debug("resultCaptureMoMoWallet::partnerSignature::" + signature);
        Console.debug("resultCaptureMoMoWallet::momoSignature::" + paymentResponse.getSignature());

        if (signature.equals(paymentResponse.getSignature())) {
            return paymentResponse;
        } else {
            throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
        }
    }

    /**
     * Some errors will be showed in process
     * Read detail error in documents
     * [Find out] (https://business.momo.vn/solution/document) - Section 7
     *
     * @param errorCode
     * @throws Exception
     */
    public static void errorMoMoProcess(int errorCode) throws Exception {

        switch (errorCode) {
            case 0:
                // O is meaning success
                break;
            case 1:
                throw new Exception("Empty access key or partner code");

        }
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
	    byte[] testByte = jsonStr.getBytes("UTF-8");
	    final String hash = Encoder.encryptRSA(testByte, publicKey);
	    return hash;
    }
}
