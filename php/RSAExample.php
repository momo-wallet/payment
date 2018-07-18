<?php
// the public key get from https://business.momo.vn/
$public_key = "-----BEGIN PUBLIC KEY-----
MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAiBIo9EMTElPppPejirL1cdgCuZUoBzGZ
F3SyrTp+xdMnIXSOiFYG+zHmI1lFzoEbEd1JwXAUV52gn/oAkUo+2qwuqZAPdkm714tiyjvxXE/0
WYLl8X1K8uCSK47u26CnOLgNB6iW1m9jog00i9XV/AmKI1U8OioLFSp1BwMf3O+jA9uuRfj1Lv5Q
0Q7RMtk4tgV924+D8mY/y3otBp5b+zX0NrWkRqwgPly6NeXN5LwqRj0LwAEVVwGbpl6V2cztYv94
ZHjGzNziFJli2D0Vpb/HRPP6ibXvllgbL4UXU4Izqhxml8gwd74jXaNaEgNJGhjjeUXR1sAm7Mpj
qqgyxpx6B2+GpjWtEwvbJuO8DsmQNsm+bJZhw46uf9AuY5VSYy2cAF1XMXSAPNLqYEE8oVUki4IW
YOEWSNXcQwikJC25rAErbyst/0i8RN4yqgiO/xVA1J1vdmRQTvGMXPGbDFpVca4MkHHLrkdC3Z3C
zgMkbIqnpaDYoIHZywraHWA7Zh5fDt/t7FzX69nbGg8i4QFLzIm/2RDPePJTY2R24w1iVO5RhEbK
EaTBMuibp4UJH+nEQ1p6CNdHvGvWz8S0izfiZmYIddaPatQTxYRq4rSsE/+2L+9RE9HMqAhQVveh
RGWWiGSY1U4lWVeTGq2suCNcMZdgDMbbIaSEJJRQTksCAwEAAQ==
-----END PUBLIC KEY-----";

$rowData->partnerCode = "MOMOV2OF20180515";
$rowData->partnerRefId = "caa5a630-8a3a-11e8-884c-653db95e86a6";
$rowData->amount = 500000;
$rowData->partnerTransId = "caa5a630-8a3a-11e8-884c-653db95e86a6";

$rowJSON = json_encode($rowData);

openssl_public_encrypt($rowJSON, $encrypted, $public_key, OPENSSL_PKCS1_PADDING);
//openssl_public_encrypt($rowJSON, $encrypted, $public_key);
echo base64_encode($encrypted);