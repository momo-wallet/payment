// npm install node-rsa
const NodeRSA = require('node-rsa');

// using your public key get from https://business.momo.vn/
//const fs = require('fs');
//const pubKey = fs.readFileSync('rsa.pub');
const pubKey = '-----BEGIN PUBLIC KEY-----'+
'MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAiBIo9EMTElPppPejirL1'+
'cdgCuZUoBzGZF3SyrTp+xdMnIXSOiFYG+zHmI1lFzoEbEd1JwXAUV52gn/oAkUo+'+
'2qwuqZAPdkm714tiyjvxXE/0WYLl8X1K8uCSK47u26CnOLgNB6iW1m9jog00i9XV'+
'/AmKI1U8OioLFSp1BwMf3O+jA9uuRfj1Lv5Q0Q7RMtk4tgV924+D8mY/y3otBp5b'+
'+zX0NrWkRqwgPly6NeXN5LwqRj0LwAEVVwGbpl6V2cztYv94ZHjGzNziFJli2D0V'+
'pb/HRPP6ibXvllgbL4UXU4Izqhxml8gwd74jXaNaEgNJGhjjeUXR1sAm7Mpjqqgy'+
'xpx6B2+GpjWtEwvbJuO8DsmQNsm+bJZhw46uf9AuY5VSYy2cAF1XMXSAPNLqYEE8'+
'oVUki4IWYOEWSNXcQwikJC25rAErbyst/0i8RN4yqgiO/xVA1J1vdmRQTvGMXPGb'+
'DFpVca4MkHHLrkdC3Z3CzgMkbIqnpaDYoIHZywraHWA7Zh5fDt/t7FzX69nbGg8i'+
'4QFLzIm/2RDPePJTY2R24w1iVO5RhEbKEaTBMuibp4UJH+nEQ1p6CNdHvGvWz8S0'+
'izfiZmYIddaPatQTxYRq4rSsE/+2L+9RE9HMqAhQVvehRGWWiGSY1U4lWVeTGq2s'+
'uCNcMZdgDMbbIaSEJJRQTksCAwEAAQ=='+
'-----END PUBLIC KEY-----';

const key = new NodeRSA(pubKey, {encryptionScheme: 'pkcs1'});
const jsonData = {
  "partnerCode": "MOMOV2OF20180515",
  "partnerRefId": "caa5a630-8a3a-11e8-884c-653db95e86a6",
  "amount": 500000,
  "partnerTransId": "caa5a630-8a3a-11e8-884c-653db95e86a6"
};
const encrypted = key.encrypt(JSON.stringify(jsonData), 'base64');
console.log('encrypted: ', encrypted);
