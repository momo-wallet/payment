
require 'openssl'
require 'base64'
require 'json'

rawData = {
  :partnerCode => 'MOMO5G4K20180302',
  :requestId => '410458',
  :amount => 110000,
  :signature => '410458'
}

# load key
key = OpenSSL::PKey::RSA.new File.read 'mykey.pem'
# encryptdata
encrypted_string = key.public_encrypt(JSON.pretty_generate(rawData))
encoded_str = Base64.encode64(encrypted_string)