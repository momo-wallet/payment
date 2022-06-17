<?php
header("content-type: application/json; charset=UTF-8");
http_response_code(200); //200 - Everything will be 200 Oke
if (!empty($_POST)) {
    $response = array();
    try {
        $partnerCode = $_POST["partnerCode"];
		$orderId = $_POST["orderId"];
		$requestId = $_POST["requestId"];
		$amount = $_POST["amount"];	
		$orderInfo = $_POST["orderInfo"];
		$orderType = $_POST["orderType"];
		$transId = $_POST["transId"];
		$resultCode = $_POST["resultCode"];
		$message = $_POST["message"];
		$payType = $_POST["payType"];
		$responseTime = $_POST["responseTime"];
		$extraData = $_POST["extraData"];
		$m2signature = $_POST["signature"]; //MoMo signature
		

		//Checksum
		$rawHash = "accessKey=" . $accessKey . "&amount=" . $amount . "&extraData=" . $extraData . "&message=" . $message . "&orderId=" . $orderId . "&orderInfo=" . $orderInfo .
			"&orderType=" . $orderType . "&partnerCode=" . $partnerCode . "&payType=" . $payType . "&requestId=" . $requestId . "&responseTime=" . $responseTime .
			"&resultCode=" . $resultCode . "&transId=" . $transId;

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

