from Crypto.PublicKey import RSA
from Crypto.Cipher import PKCS1_v1_5 as Cipher_PKCS1_v1_5
from base64 import b64encode
import json

# load key
f = open('mykey.pem', 'r')
key = RSA.importKey(f.read())

# encrypt data
rowData = {
  'partnerCode': 'MOMO5G4K20180302',
  'partnerRefId': 'c1f10470-8a68-11e8-9a57',
  'amount': 101000,
  'partnerTransId': 'c1f10470-8a68-11e8-9a57'
}
cipher = Cipher_PKCS1_v1_5.new(key)
cipher_text = cipher.encrypt(json.dumps(rowData).encode())
emsg = b64encode(cipher_text)
print "Encrypted: " + emsg