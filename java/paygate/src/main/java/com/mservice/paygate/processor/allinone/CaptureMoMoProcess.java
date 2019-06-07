package com.mservice.paygate.processor.allinone;

import com.mservice.paygate.constants.Parameter;
import com.mservice.paygate.constants.RequestType;
import com.mservice.paygate.exception.MoMoException;
import com.mservice.paygate.model.CaptureMoMoRequest;
import com.mservice.paygate.model.CaptureMoMoResponse;
import com.mservice.paygate.model.Environment;
import com.mservice.paygate.model.PaymentResponse;
import com.mservice.paygate.utils.Console;
import com.mservice.paygate.utils.Encoder;
import com.mservice.paygate.utils.HttpResponse;
import com.sun.istack.internal.NotNull;

/**
 * @author hainguyen
 * Documention: https://developers.momo.vn
 */
public class CaptureMoMoProcess extends AbstractProcess<CaptureMoMoRequest, CaptureMoMoResponse> {

    public CaptureMoMoProcess(Environment environment) {
        super(environment);
    }

    @Override
    public CaptureMoMoResponse execute(CaptureMoMoRequest request) throws MoMoException {
        try {

            String payload = getGson().toJson(request, CaptureMoMoRequest.class);

            HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint(), payload);

            CaptureMoMoResponse captureMoMoResponse = getGson().fromJson(response.getData(), CaptureMoMoResponse.class);

            errorMoMoProcess(captureMoMoResponse.getErrorCode());

            if (captureMoMoResponse.getErrorCode() == 0) {
                String responserawData =
                        Parameter.REQUEST_ID + "=" + captureMoMoResponse.getRequestId() +
                                "&" + Parameter.ORDER_ID + "=" + captureMoMoResponse.getOrderId() +
                                "&" + Parameter.MESSAGE + "=" + captureMoMoResponse.getMessage() +
                                "&" + Parameter.LOCAL_MESSAGE + "=" + captureMoMoResponse.getLocalMessage() +
                                "&" + Parameter.PAY_URL + "=" + captureMoMoResponse.getPayUrl() +
                                "&" + Parameter.ERROR_CODE + "=" + captureMoMoResponse.getErrorCode() +
                                "&" + Parameter.REQUEST_TYPE + "=" + RequestType.CAPTURE_MOMO_WALLET;

                Console.debug("getCaptureMoMoResponse::partnerRawDataBeforeHash::" + responserawData);
                String signResponse = Encoder.signHmacSHA256(responserawData, partnerInfo.getSecretKey());
                Console.debug("getCaptureMoMoResponse::partnerSignature::" + signResponse);
                Console.debug("getCaptureMoMoResponse::momoSignature::" + captureMoMoResponse.getSignature());

                if (signResponse.equals(captureMoMoResponse.getSignature())) {
                    return captureMoMoResponse;
                } else {
                    throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
                }
            }
            return captureMoMoResponse;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }

    /**
     * @param orderId
     * @param requestId
     * @param amount
     * @param orderInfo
     * @param returnUrl
     * @param notifyUrl
     * @param extraData
     * @return
     */
    public CaptureMoMoRequest createPaymentCreationRequest(@NotNull String orderId, @NotNull String requestId, @NotNull String amount, @NotNull String orderInfo,
                                                           @NotNull String returnUrl, @NotNull String notifyUrl, @NotNull String extraData) {

        try {
            String requestRawData = new StringBuilder()
                    .append(Parameter.PARTNER_CODE).append("=").append(partnerInfo.getPartnerCode()).append("&")
                    .append(Parameter.ACCESS_KEY).append("=").append(partnerInfo.getAccessKey()).append("&")
                    .append(Parameter.REQUEST_ID).append("=").append(requestId).append("&")
                    .append(Parameter.AMOUNT).append("=").append(amount).append("&")
                    .append(Parameter.ORDER_ID).append("=").append(orderId).append("&")
                    .append(Parameter.ORDER_INFO).append("=").append(orderInfo).append("&")
                    .append(Parameter.RETURN_URL).append("=").append(returnUrl).append("&")
                    .append(Parameter.NOTIFY_URL).append("=").append(notifyUrl).append("&")
                    .append(Parameter.EXTRA_DATA).append("=").append(extraData)
                    .toString();

            Console.debug("createCaptureMoMoRequest::rawDataBeforeHash::" + requestRawData);
            String signRequest = Encoder.signHmacSHA256(requestRawData, partnerInfo.getSecretKey());
            Console.debug("createCaptureMoMoRequest::signature::" + signRequest);

            return CaptureMoMoRequest
                    .builder()
                    .accessKey(partnerInfo.getAccessKey())
                    .requestId(requestId)
                    .partnerCode(partnerInfo.getPartnerCode())
                    .requestType(RequestType.CAPTURE_MOMO_WALLET)
                    .notifyUrl(notifyUrl)
                    .returnUrl(returnUrl)
                    .orderId(orderId)
                    .amount(amount)
                    .signature(signRequest)
                    .extraData(extraData)
                    .orderInfo(orderInfo)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * After end-user do pay order, MoMo will return result to partner by two ways:
     * - 1. On browser, MoMo website will be redirected to Partner website by returnUrl field which passed to api captureMoMoWallet
     * - 2. MoMo uses notifyUrl field to send http request with POST method to partner endpoint
     * <p>
     * For returnUrl: we will attach and fill data to params: refer PAYLOAD
     * For notifyUrl: we will build request with information:
     * METHOD: POST
     * HEADER: CONTENT-TYPE: X-WWW-FORM-URLENCODED
     * CHARSET : UTF-8
     * <p>
     * PAYLOAD: partnerCode=$partnerCode&accessKey=$accessKey&requestId=$requestId&amount=$amount&orderId=$oderId&orderInfo=$orderInfo&orderType=$orderType&transId=$transId&message=$message&localMessage=$localMessage&responseTime=$responseTime&errorCode=$errorCode& payType=$payType&extraData=$extraData
     *
     *
     * <p>
     * You can use this function to get and validate result
     * Using for two commands: captureMoMoWallet and payWithATM
     * ErrorCode is key to detect transaction is success or fail
     * <p>
     * if ErrorCode = 0 mean transaction is payment success else fail
     *
     * @param paymentResponse
     * @return
     * @throws Exception
     */
    public PaymentResponse resultCaptureMoMoWallet(PaymentResponse paymentResponse) throws Exception {
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
}
