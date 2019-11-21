<?php
header("content-type: application/json; charset=UTF-8");
http_response_code(200); //200 - Everything will be 200 Oke
if (!empty($_POST)) {
    $response = array();
    try {
        $partnerCode = $_POST["partnerCode"];
        $accessKey = $_POST["accessKey"];
        $serectkey = '';
        $orderId = $_POST["orderId"];
        $localMessage = $_POST["localMessage"];
        $message = $_POST["message"];
        $transId = $_POST["transId"];
        $orderInfo = $_POST["orderInfo"];
        $amount = $_POST["amount"];
        $errorCode = $_POST["errorCode"];
        $responseTime = $_POST["responseTime"];
        $requestId = $_POST["requestId"];
        $extraData = $_POST["extraData"];
        $payType = $_POST["payType"];
        $orderType = $_POST["orderType"];
        $extraData = $_POST["extraData"];
        $m2signature = $_POST["signature"]; //MoMo signature


        //Checksum
        $rawHash = "partnerCode=" . $partnerCode . "&accessKey=" . $accessKey . "&requestId=" . $requestId . "&amount=" . $amount . "&orderId=" . $orderId . "&orderInfo=" . $orderInfo .
            "&orderType=" . $orderType . "&transId=" . $transId . "&message=" . $message . "&localMessage=" . $localMessage . "&responseTime=" . $responseTime . "&errorCode=" . $errorCode .
            "&payType=" . $payType . "&extraData=" . $extraData;

        $partnerSignature = hash_hmac("sha256", $rawHash, 'at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa');

        if ($m2signature == $partnerSignature) {
            if ($errorCode == '0') {
                $result = '<div class="alert alert-success">Capture Payment Success</div>';
            } else {
                $result = '<div class="alert alert-danger">' . $message . '</div>';
            }
        } else {
            $result = '<div class="alert alert-danger">This transaction could be hacked, please check your signature and returned signature</div>';
        }

    } catch (Exception $e) {
        echo $response['message'] = $e;
    }

    $debugger = array();
    $debugger['rawData'] = $rawHash;
    $debugger['momoSignature'] = $m2signature;
    $debugger['partnerSignature'] = $partnerSignature;

    if ($m2signature == $partnerSignature) {
        $response['message'] = "Received payment result success";
    } else {
        $response['message'] = "ERROR! Fail checksum";
    }
    $response['debugger'] = $debugger;
    echo json_encode($response);
}

?>

