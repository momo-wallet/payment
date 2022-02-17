require 'net/https'
require 'uri'
require 'json'
require 'openssl'
require 'Base64'
require 'securerandom'

#parameters send to MoMo get get payUrl
endpoint = "https://test-payment.momo.vn/v2/gateway/api/create";
partnerCode = "MOMO"
accessKey = "F8BBA842ECF85"
secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz"
orderInfo = "pay with MoMo"
redirectUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b"
ipnUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b"
amount = "50000"
orderId = SecureRandom.uuid
requestId = SecureRandom.uuid
requestType = "captureWallet"
extraData = "" #pass empty value or Encode base64 JsonString

#before sign HMAC SHA256 with format: accessKey=$accessKey&amount=$amount&extraData=$extraData&ipnUrl=$ipnUrl&orderId=$orderId&orderInfo=$orderInfo&partnerCode=$partnerCode&redirectUrl=$redirectUrl&requestId=$requestId&requestType=$requestType
rawSignature = "accessKey="+accessKey+"&amount="+amount+"&extraData="+extraData+"&ipnUrl="+ipnUrl+"&orderId="+orderId+"&orderInfo="+orderInfo+"&partnerCode="+partnerCode+"&redirectUrl="+redirectUrl+"&requestId="+requestId+"&requestType="+requestType
#puts raw signature
puts "--------------------RAW SIGNATURE----------------"
puts rawSignature
#signature
signature = OpenSSL::HMAC.hexdigest(OpenSSL::Digest.new('sha256'), secretKey, rawSignature)
puts "--------------------SIGNATURE----------------"
puts signature

#json object send to MoMo endpoint
jsonRequestToMomo = {
				  :partnerCode => partnerCode,
                  :partnerName => "Test",
                  :storeId => "MomoTestStore",
                  :requestId => requestId,
                  :amount => amount,
                  :orderId => orderId,
                  :orderInfo => orderInfo,
                  :redirectUrl => redirectUrl,
                  :ipnUrl => ipnUrl,
                  :lang => "vi",
                  :extraData => extraData,
                  :requestType => requestType,
                  :signature => signature,
              }
puts "--------------------JSON REQUEST----------------"
puts JSON.pretty_generate(jsonRequestToMomo)
# Create the HTTP objects
uri = URI.parse(endpoint)
http = Net::HTTP.new(uri.host, uri.port)
http.use_ssl = true
http.verify_mode = OpenSSL::SSL::VERIFY_NONE
request = Net::HTTP::Post.new(uri.path)
request.add_field('Content-Type', 'application/json')
request.body = jsonRequestToMomo.to_json


# Send the request and get the response
puts "SENDING...."
response = http.request(request)
result = JSON.parse(response.body)
puts "--------------------RESPONSE----------------"
puts JSON.pretty_generate(result)
puts "pay URL is: " + result["payUrl"]
