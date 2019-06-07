package com.mservice.paygate.processor.allinone;

import com.mservice.paygate.constants.Parameter;
import com.mservice.paygate.constants.RequestType;
import com.mservice.paygate.exception.MoMoException;
import com.mservice.paygate.model.Environment;
import com.mservice.paygate.model.PartnerInfo;
import com.mservice.paygate.model.PayATMRequest;
import com.mservice.paygate.model.PayATMResponse;
import com.mservice.paygate.utils.Console;
import com.mservice.paygate.utils.Encoder;
import com.mservice.paygate.utils.HttpResponse;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author hainguyen
 * Documention: https://developers.momo.vn
 */
public class PayATMProcess extends AbstractProcess<PayATMRequest, PayATMResponse> {

    public PayATMProcess(Environment environment) {
        super(environment);
    }

    @Override
    public PayATMResponse execute(PayATMRequest request) throws MoMoException {
        try {
            String payload = getGson().toJson(request, PayATMRequest.class);

            HttpResponse response = execute.sendToMoMo(environment.getMomoEndpoint(), payload);

            if (response.getStatus() != 200) {
                throw new MoMoException("Error API");
            }

            PayATMResponse payATMResponse = getGson().fromJson(response.getData(), PayATMResponse.class);

            errorMoMoProcess(payATMResponse.getErrorCode());

            if (payATMResponse.getErrorCode() == 0) {
                String rawData =
                        Parameter.PARTNER_CODE + "=" + request.getPartnerCode() +
                                "&" + Parameter.ACCESS_KEY + "=" + request.getAccessKey() +
                                "&" + Parameter.REQUEST_ID + "=" + request.getRequestId() +
                                "&" + Parameter.PAY_URL + "=" + payATMResponse.getPayUrl() +
                                "&" + Parameter.ERROR_CODE + "=" + payATMResponse.getErrorCode() +
                                "&" + Parameter.ORDER_ID + "=" + payATMResponse.getOrderId() +
                                "&" + Parameter.MESSAGE + "=" + payATMResponse.getMessage() +
                                "&" + Parameter.LOCAL_MESSAGE + "=" + payATMResponse.getLocalMessage() +
                                "&" + Parameter.REQUEST_TYPE + "=" + RequestType.PAY_WITH_ATM;

                Console.debug("getPayATMMoMoResponse::partnerRawDataBeforeHash::" + rawData);
                String signature = null;

                signature = Encoder.signHmacSHA256(rawData, partnerInfo.getSecretKey());

                Console.debug("getPayATMMoMoResponse::partnerSignature::" + signature);
                Console.debug("getPayATMMoMoResponse::momoSignature::" + payATMResponse.getSignature());

                if (signature.equals(payATMResponse.getSignature())) {
                    return payATMResponse;
                } else {
                    throw new IllegalArgumentException("Wrong signature from MoMo side - please contact with us");
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public PayATMRequest createPayWithATMRequest(String requestId, String orderId, String bankCode, String amount, String orderInfo, String returnUrl,
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

            PayATMRequest payATMRequest = PayATMRequest
                    .builder()
                    .partnerCode(partnerInfo.getPartnerCode())
                    .accessKey(partnerInfo.getAccessKey())
                    .amount(amount)
                    .requestId(requestId)
                    .orderId(orderId)
                    .returnUrl(returnUrl)
                    .notifyUrl(notifyUrl)
                    .orderInfo(orderInfo)
                    .bankCode(bankCode)
                    .requestType(RequestType.PAY_WITH_ATM)
                    .signature(signature)
                    .build();

            return payATMRequest;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid params capture MoMo Request");
        }
    }
}
