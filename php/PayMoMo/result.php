<?php
header('Content-type: text/html; charset=utf-8');


$secretKey = 'at67qH6mk8w5Y1nAyMoYKMWACiEi2bsa'; //Put your secret key in there

if (!empty($_GET)) {
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

    $partnerSignature = hash_hmac("sha256", $rawHash, $secretKey);

    echo "<script>console.log('Debug huhu Objects: " . $rawHash . "' );</script>";
    echo "<script>console.log('Debug huhu Objects: " . $partnerSignature . "' );</script>";


    if ($m2signature == $partnerSignature) {
        if ($errorCode == '0') {
            $result = '<div class="alert alert-success"><strong>Payment status: </strong>Success</div>';
        } else {
            $result = '<div class="alert alert-danger"><strong>Payment status: </strong>' . $message .'/'.$localMessage. '</div>';
        }
    } else {
        $result = '<div class="alert alert-danger">This transaction could be hacked, please check your signature and returned signature</div>';
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
    <script type="text/javascript" src="./statics/jquery/dist/jquery.min.js"></script>
    <script type="text/javascript" src="./statics/moment/min/moment.min.js"></script>
    <script type="text/javascript" src="./statics/bootstrap/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="./statics/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.4.1/css/bootstrap.min.css"/>
    <link rel="stylesheet"
          href="./statics/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css"/>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">Payment status/Kết quả thanh toán</h1>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-md-12">
                            <?php echo $result; ?>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">PartnerCode</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="partnerCode" value="<?php echo $partnerCode; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">AccessKey</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="accessKey" value="<?php echo $accessKey; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">OrderId</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="orderId" value="<?php echo $orderId; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">transId</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="transId" value="<?php echo $transId; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">OrderInfo</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="orderInfo" value="<?php echo $orderInfo; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">orderType</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="orderType" value="<?php echo $orderType; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">Amount</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="amount" value="<?php echo $amount; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">Message</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="message" value="<?php echo $message; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">localMessage</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="localMessage" value="<?php echo $localMessage; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">payType</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="payType" value="<?php echo $payType; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">ExtraData</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' type="text" name="extraData" value="<?php echo $extraData; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-12">
                            <div class="form-group">
                                <label for="fxRate" class="col-form-label">signature</label>
                                <div class='input-group date' id='fxRate'>
                                    <input type='text' name="signature" value="<?php echo $m2signature; ?>"
                                           class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="form-group">
                                <a href="/" class="btn btn-primary">Back to continue payment...</a>
                            </div>
                        </div>
                    </div>
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

                    echo '<b> SecretKey:</b> (This value is hard-coded in the php file. If you need to change it, do it manually)<pre>' . $secretKey . '</pre></br>';

                    echo '<b> RawData: </b><pre>' . $rawHash . '</pre></br>';

                    echo '<b>MoMo signature: </b><pre>' . $m2signature . '</pre></br>';

                    echo '<b>Partner signature: </b><pre>' . $partnerSignature . '</pre></br>';

                    if($m2signature == $partnerSignature){
                        echo '<div class="alert alert-success"><strong>INFO: </strong>Pass Checksum</div>';
                    }else{
                        echo '<div class="alert alert-danger" role="alert"> <strong>ERROR!:</strong> Fail checksum</div>';
                    }
                    ?>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
