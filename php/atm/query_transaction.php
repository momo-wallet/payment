<?php
header('Content-type: text/html; charset=utf-8');


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

$endpoint = "https://test-payment.momo.vn/v2/gateway/api/query";
$partnerCode = 'MOMOBKUN20180529';
$accessKey = 'klm05TvNBzhg7h7j';
$secretKey = 'at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa';
$requestId = time()."";



if (!empty($_POST)) {
    $orderId = $_POST["orderId"];;// Mã đơn hàng cần kiểm tra trạng thái

    //before sign HMAC SHA256 signature
    $rawHash = "accessKey=".$accessKey."&orderId=".$orderId."&partnerCode=".$partnerCode."&requestId=".$requestId;
    // echo "<script>console.log('Debug Objects: " . $rawHash . "' );</script>";

    $signature = hash_hmac("sha256", $rawHash, $secretKey);

    $data = array('partnerCode' => $partnerCode,
        'requestId' => $requestId,
        'orderId' => $orderId,
        'requestType' => $requestType,
        'signature' => $signature,
        'lang' => 'vi');
    $result = execPostRequest($endpoint, json_encode($data));
    $jsonResult = json_decode($result, true);  // decode json
    $response = json_encode($jsonResult, JSON_PRETTY_PRINT);
    // check signature response
    if(!empty($result)){
        $partnerCode = $jsonResult["partnerCode"];
        $accessKey = $jsonResult["accessKey"];
        $requestId = $jsonResult["requestId"];
        $orderId = $jsonResult["orderId"];
        $errorCode = $jsonResult["errorCode"];
        $transId = $jsonResult["transId"];
        $amount = $jsonResult["amount"];
        $message = $jsonResult["message"];
        $localMessage = $jsonResult["localMessage"];
        $requestType = $jsonResult["requestType"];
        $payType = $jsonResult["payType"];
        $extraData = ($jsonResult["extraData"] ? $jsonResult["extraData"] : "");
        $m2signature = $jsonResult["signature"];

        //before sign HMAC SHA256 signature
        $rawHash = "partnerCode=".$partnerCode."&accessKey=".$accessKey."&requestId=".$requestId."&orderId=".$orderId."&errorCode=".$errorCode."&transId=".$transId."&amount=".$amount."&message=".$message."&localMessage=".$localMessage."&requestType=".$requestType."&payType=".$payType."&extraData=".$extraData;
        $partnerSignature = hash_hmac("sha256", $rawHash, $secretKey);


    }
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>MoMo Sandbox</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="./statics/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>
    <!-- CSS -->
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Kiểm tra trạng thái giao dịch thanh toán</h3>
                </div>
                <div class="panel-body">
                    <form class="" method="POST" target="" enctype="application/x-www-form-urlencoded"
                          action="query_transaction.php">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="form-group">
                                    <label for="fxRate" class="col-form-label">Partner Code</label>
                                    <div class='input-group date' id='fxRate'>
                                        <input type='text' name="partnerCode" value="<?php echo $partnerCode; ?>"
                                               class="form-control" readonly/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="fxRate" class="col-form-label">Mã đơn hàng cần kiểm tra trạng thái</label>
                                    <div class='input-group date' id='fxRate'>
                                        <input type='text' name="orderId" value="<?php echo $orderId; ?>"
                                               class="form-control"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <p>
                        <div style="margin-top: 1em;">
                            <button type="submit" class="btn btn-primary btn-block">Check Payment</button>
                        </div>
                        </p>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"> Debugger</h3>
                </div>
                <div class="panel-body">

                    <?php
                    echo '<b> Response: </b><pre>' .$response . '</pre></br>';
                    ?>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

<script type="text/javascript" src="./statics/jquery/dist/jquery.min.js"></script>
<script type="text/javascript" src="./statics/moment/min/moment.min.js"></script>
</html>
