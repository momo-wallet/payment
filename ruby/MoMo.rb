require 'net/https'
require 'uri'
require 'json'
require 'openssl'
require 'Base64'
require 'securerandom'

#parameters send to MoMo get get payUrl
endpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
partnerCode = "MOMO"
accessKey = "F8BBA842ECF85"
serectkey = "K951B6PE1waDMi640xX08PD3vg6EkVlz"
orderInfo = "pay with MoMo"
returnUrl = "https://momo.vn/return"
notifyurl = "https://dummy-url.vn/notify"
amount = "50000"
orderId = SecureRandom.uuid
requestId = SecureRandom.uuid
requestType = "captureMoMoWallet"
extraData = "merchantName=Grab taxi;merchantId=3948"

#before sign HMAC SHA256 with format
#partnerCode=$partnerCode&accessKey=$accessKey&requestId=$requestId&amount=$amount&orderId=$oderId&orderInfo=$orderInfo&returnUrl=$returnUrl&notifyUrl=$notifyUrl&extraData=$extraData
rawSignature = "partnerCode="+partnerCode+"&accessKey="+accessKey+"&requestId="+requestId+"&amount="+amount+"&orderId="+orderId+"&orderInfo="+orderInfo+"&returnUrl="+returnUrl+"&notifyUrl="+notifyurl+"&extraData="+extraData
#puts raw signature
puts "--------------------RAW SIGNATURE----------------"
puts rawSignature
#signature
signature = OpenSSL::HMAC.hexdigest(OpenSSL::Digest.new('sha256'), serectkey, rawSignature)
puts "--------------------SIGNATURE----------------"
puts signature

#json object send to MoMo endpoint
jsonRequestToMomo = {
				  :partnerCode => partnerCode,
                  :accessKey => accessKey,
                  :requestId => requestId,
                  :amount => amount,
                  :orderId => orderId,
                  :orderInfo => orderInfo,
                  :returnUrl => returnUrl,
                  :notifyUrl => notifyurl,
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
response = http.request(request)

# Send the request and get the response
puts "SENDING...."
response = JSON.parse(http.request(request).body)
puts "--------------------RESPONSE----------------"
puts JSON.pretty_generate(response)
puts "pay URL is: " + response["payUrl"]

