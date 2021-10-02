from __future__ import print_function


from datetime import timedelta, datetime

import requests
from flask import Blueprint, request, jsonify, json
import base64
from .models import HaraCustomers, \
    HaraShop
now = datetime.utcnow() + timedelta(hours=7)
payments = Blueprint('payments', __name__)
import hashlib
import hmac
import json
import uuid
def momo_payment(amount):
    # parameters send to MoMo get get payUrl
    endpoint = "https://test-payment.momo.vn/v2/gateway/api/create"
    partnerCode = "your_partnderCode"
    accessKey = "your_accessKey"
    secretKey = "your_secretKey"
    orderInfo = "pay with MoMo"
    redirectUrl = "https://yourwebsite.com/api/v2/momo/payment/return"
    ipnUrl = "https://yourwebsite.com/api/v2/momo/payment/notify"
    #amount = "50000"
    orderId = str(uuid.uuid4())
    requestId = str(uuid.uuid4())
    requestType = "captureWallet"
    extraData = ""  # pass empty value or Encode base64 JsonString
    # before sign HMAC SHA256 with format: accessKey=$accessKey&amount=$amount&extraData=$extraData&ipnUrl=$ipnUrl&orderId=$orderId&orderInfo=$orderInfo&partnerCode=$partnerCode&redirectUrl=$redirectUrl&requestId=$requestId&requestType=$requestType
    rawSignature = "accessKey=" + accessKey + "&amount=" + amount + "&extraData=" + extraData + "&ipnUrl=" + ipnUrl + "&orderId=" + orderId + "&orderInfo=" + orderInfo + "&partnerCode=" + partnerCode + "&redirectUrl=" + redirectUrl + "&requestId=" + requestId + "&requestType=" + requestType
    print(rawSignature, type (rawSignature))
    # puts raw signature
    #digest = hmac.new(secretKey.encode('utf-8'), rawSignature, hashlib.sha256).digest()
    key_bytes = bytes(secretKey, 'utf-8')
    # encoding as per other answers
    byte_key = bytes(secretKey, 'UTF-8')  # key.encode() would also work in this case
    message = rawSignature.encode()

    # now use the hmac.new function and the hexdigest method
    h = hmac.new(byte_key, message, hashlib.sha256)
    signature = h.hexdigest()
    data = {
        'partnerCode': partnerCode,
        'partnerName': "Test",
        'storeId': "MomoTestStore",
        'requestId': requestId,
        'amount': amount,
        'orderId': orderId,
        'orderInfo': orderInfo,
        'redirectUrl': redirectUrl,
        'ipnUrl': ipnUrl,
        'lang': "vi",
        'extraData': extraData,
        'requestType': requestType,
        'signature': signature
    }
    #data = json.dumps(data)
    clen = len(data)
    headers ={'Content-Type': 'application/json; charset=UTF-8', 'Content-Length': str(clen)}
    response = requests.post(endpoint, headers=headers, json=data)
    print(response.json())
    return response
@payments.route('/api/v2/momo/payment/return', methods=['GET', 'POST'])
def payment_momo_return():
    print(request.data)
    return 200
@payments.route('/api/v2/momo/payment/notify', methods=['GET', 'POST'])
def payment_momo_notify():
    print(request.data)
    return 200
@payments.route('/api/v2/momo/payment', methods=['POST'])
def payment_momo_post():
    print(request.data)
    data = request.data
    headers = request.headers
    try:
        request_json = json.loads(request.json)
        print(request_json)
    except Exception as e:
        print(e)
        request_json = data.decode('utf8').replace("'", '"')
        print(request_json)
        request_json = json.loads(request_json)
    Authorization = headers['Authorization']
    token = Authorization.replace("Bearer", "")
    phone = request_json['phone']
    if phone is not None and phone != "":
        print('token', token, "phone", phone)
        now = datetime.utcnow() + timedelta(hours=7)
        shop = HaraShop.query.filter(HaraShop.private_token == token, HaraShop.ended_date > now,
                                     HaraShop.shop_active == True).first()
        customer = HaraCustomers.query.filter_by(shop_id=shop.id, phone=phone).first()
        if customer is not None:
            amount = request_json['amount']
            response = momo_payment(str(amount))
            print(response.url)
            print(response.json())
            response_json = {
                "status": True,
                "have_customer": True,
                "response":response.json(),
                "url":response.url,
            }
            return jsonify(response_json)
        else:
            response_json = {
                "status": False,
                "have_customer": False
            }
            return jsonify(response_json)
    else:
        response_json = {
            "have_customer": False,
            "status": False,
        }
        return jsonify(response_json)
