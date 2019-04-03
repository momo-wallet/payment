<?php

    function execPostRequest($url, $data)
   {
    $ch = curl_init($url);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($ch, CURLOPT_POSTFIELDS, $data);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(
        'Content-Type: application/json',
        'Content-Length: ' . strlen($data))
    );
    curl_setopt($ch, CURLOPT_TIMEOUT, 5);
    curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 5);

    //execute post
    $result = curl_exec($ch);

    //close connection
    curl_close($ch);

    return $result;
   }
   $endpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
   $partnerCode = "MOMO0HGO20180417";
   $accessKey = "E8HZuQRy2RsjVtZp";
   $serectkey = "fj00YKnJhmYqahaFWUgkg75saNTzMrbO";
   $orderInfo = "pay with MoMo";
   $returnUrl = "https://momo.vn/return";
   $notifyurl = "https://dummy-url.vn/notify";
   $amount = "50000";
   $orderid = time()."";
   $requestId = time()."";
   $requestType = "captureMoMoWallet";
   $extraData = "merchantName=;merchantId=";//pass empty value if your merchant does not have stores else merchantName=[storeName]; merchantId=[storeId] to identify a transaction map with a physical store
   //before sign HMAC SHA256 signature
   $rawHash = "partnerCode=".$partnerCode."&accessKey=".$accessKey."&requestId=".$requestId."&amount=".$amount."&orderId=".$orderid."&orderInfo=".$orderInfo."&returnUrl=".$returnUrl."&notifyUrl=".$notifyurl."&extraData=".$extraData;
   echo "Raw signature: ".$rawHash."\n";
   $signature = hash_hmac("sha256", $rawHash, $serectkey);

   $data =  array('partnerCode' => $partnerCode,
                  'accessKey' => $accessKey,
                  'requestId' => $requestId,
                  'amount' => $amount,
                  'orderId' => $orderid,
                  'orderInfo' => $orderInfo,
                  'returnUrl' => $returnUrl,
                  'notifyUrl' => $notifyurl,
                  'extraData' => $extraData,
                  'requestType' => $requestType,
                  'signature' => $signature);
   echo "Data send to MoMo: \n";
   print_r(json_encode($data));
   echo "\n";

   $result = execPostRequest($endpoint, json_encode($data));
   $jsonResult =json_decode($result,true);  // decode json
   echo "Result: \n";
   print_r($jsonResult);



