package com.mservice.paygate.constants;

/**
 * Copyright (c) 2017 M_Service
 * Created by KhangDC
 * Created date 9/27/17
 * Donate & have any question
 * MoMo: 0938314061.
 */
public class RequestType {



    /**
     * ======================= USING FOR MERCHANT/PARTNER PUBLIC =================
     */
    public final static String UN_SUPPORT = "UN_SUPPORT";
    public final static String CAPTURE_MOMO_WALLET = "captureMoMoWallet";
    public final static String TRANSACTION_STATUS = "transactionStatus";
    public final static String REFUND_MOMO_WALLET = "refundMoMoWallet";
    public final static String QUERY_REFUND = "refundStatus";
    public final static String REFUND_ATM = "refundMoMoATM";
    public final static String WALLET_BALANCE = "walletBalance";
    public final static String PAY_WITH_ATM = "payWithMoMoATM";
    public final static String TOPUP_MOBILE = "topUpMoMo";
    public final static String BUY_CARD_PHONE = "buyCardMoMo";
    public final static String SUBSCRIBE = "subscribeMoMo";
    public final static String PAY_WITH_SUBSCRIBE = "payWithSubscribeMoMo";
    public final static String AUTHORIZE_MOMO_WALLET = "subscriptionToken";

    public final static String FINISH_WITH_MOMO_ATM = "finishProcessMoMoATM";

    public final static String PAY_WITH_QR = "finishProcessMoMoATM";


    /**
     * ========================= USING INTERNAL ==============================
     */
    public final static String QUERY_STATUS_PAY_WITH_APP = "queryStatusPayWithApp";
    public final static String QUERY_STATUS_AUTHORIZE_WITH_APP = "queryStatusAuthorizeWithApp";
    public final static String PAY_WITH_APP = "payWithApp";

}
