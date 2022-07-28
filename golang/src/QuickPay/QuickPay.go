package main

import (
	"bytes"
	"crypto/hmac"
	"crypto/sha256"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"strconv"

	//run for download library `go get github.com/sony/sonyflake`
	"github.com/sony/sonyflake"
)

//define a payload, reference in https://developers.momo.vn/#cong-thanh-toan-momo-phuong-thuc-thanh-toan
type Payload struct {
	PartnerCode  string `json:"partnerCode"`
	AccessKey    string `json:"accessKey"`
	RequestID    string `json:"requestId"`
	Amount       string `json:"amount"`
	OrderID      string `json:"orderId"`
	OrderInfo    string `json:"orderInfo"`
	PartnerName  string `json:"partnerName"`
	StoreId      string `json:"storeId"`
	OrderGroupId string `json:"orderGroupId"`
	Lang         string `json:"lang"`
	AutoCapture  bool   `json:"autoCapture"`
	RedirectUrl  string `json:"redirectUrl"`
	IpnUrl       string `json:"ipnUrl"`
	ExtraData    string `json:"extraData"`
	PaymentCode  string `json:"paymentCode"`
	RequestType  string `json:"requestType"`
	Signature    string `json:"signature"`
}

//define a POS payload, reference in https://developers.momo.vn/#thanh-toan-pos-xu-ly-thanh-toan
type PosHash struct {
	PartnerCode  string `json:"partnerCode"`
	PartnerRefID string `json:"partnerRefId"`
	Amount       int    `json:"amount"`
	PaymentCode  string `json:"paymentCode"`
}

type PosPayload struct {
	PartnerCode  string `json:"partnerCode"`
	PartnerRefID string `json:"partnerRefId"`
	Hash         string `json:"hash"`
	Version      int    `json:"version"`
}

func main() {
	fmt.Printf("Hello Momo!\n")

	flake := sonyflake.NewSonyflake(sonyflake.Settings{})
	//randome orderID and requestID
	a, _ := flake.NextID()
	b, _ := flake.NextID()

	var endpoint = "https://test-payment.momo.vn/v2/gateway/api/pos"
	var accessKey = "F8BBA842ECF85"
	var secretKey = "K951B6PE1waDMi640xX08PD3vg6EkVlz"
	var orderInfo = "pay with MoMo"
	var partnerCode = "MOMO"
	var redirectUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b"
	var ipnUrl = "https://webhook.site/b3088a6a-2d17-4f8d-a383-71389a6c600b"
	var amount = "50000"
	var orderId = strconv.FormatUint(a, 16)
	var requestId = strconv.FormatUint(b, 16)
	var extraData = ""
	var partnerName = "MoMo Payment"
	var storeId = "Test Store"
	var paymentCode = "xKY7ElH5GucU8PvjR6EESlrlEnl9ykdC5x4XgJi7+v2Pm6HEHcAQIwknjP/3NAH72oUBCmYQ9Iyi5kYGmquSpXpEAbNoBCo470dxb/rTPg4LuLF3BRKmTjyV5mUJl7XsyAQMk8J9uaYVv6Ya6EtLAVzrWNOMOiFSWmSY+UJKOwuva4vBCkY3VsQIdqix6QbrcHlinqEPBotsDENp+EeGlL9yRBGJROXwAEex3FUVPyDwYNK8ThXPmJ1tC0DCV/lKg8fEliP+oid4RDxPgYJhPIibIvFZ/JUvFyYUgZPKf8uQPiFo1wQ/8LpVCwb4OjgtRwinK1qYWQ5Kq9jGjIrT4w=="
	var orderGroupId = ""
	var autoCapture = true
	var lang = "vi"

	//build raw signature
	var rawSignature bytes.Buffer
	rawSignature.WriteString("accessKey=")
	rawSignature.WriteString(accessKey)
	rawSignature.WriteString("&amount=")
	rawSignature.WriteString(amount)
	rawSignature.WriteString("&extraData=")
	rawSignature.WriteString(extraData)
	rawSignature.WriteString("&orderId=")
	rawSignature.WriteString(orderId)
	rawSignature.WriteString("&orderInfo=")
	rawSignature.WriteString(orderInfo)
	rawSignature.WriteString("&partnerCode=")
	rawSignature.WriteString(partnerCode)
	rawSignature.WriteString("&paymentCode=")
	rawSignature.WriteString(paymentCode)
	rawSignature.WriteString("&requestId=")
	rawSignature.WriteString(requestId)

	// Create a new HMAC by defining the hash type and the key (as byte array)
	hmac := hmac.New(sha256.New, []byte(secretKey))

	// Write Data to it
	hmac.Write(rawSignature.Bytes())
	fmt.Println("Raw signature: " + rawSignature.String())

	// Get result and encode as hexadecimal string
	signature := hex.EncodeToString(hmac.Sum(nil))

	var payload = Payload{
		PartnerCode:  partnerCode,
		AccessKey:    accessKey,
		RequestID:    requestId,
		Amount:       amount,
		OrderID:      orderId,
		StoreId:      storeId,
		PartnerName:  partnerName,
		OrderGroupId: orderGroupId,
		AutoCapture:  autoCapture,
		Lang:         lang,
		OrderInfo:    orderInfo,
		RedirectUrl:  redirectUrl,
		IpnUrl:       ipnUrl,
		ExtraData:    extraData,
		PaymentCode:  paymentCode,
		Signature:    signature,
	}

	var jsonPayload []byte
	var err error
	jsonPayload, err = json.Marshal(payload)
	if err != nil {
		log.Println(err)
	}
	fmt.Println("Payload: " + string(jsonPayload))
	fmt.Println("Signature: " + signature)

	//send HTTP to momo endpoint
	resp, err := http.Post(endpoint, "application/json", bytes.NewBuffer(jsonPayload))
	if err != nil {
		log.Fatalln(err)
	}

	//result
	var result map[string]interface{}
	json.NewDecoder(resp.Body).Decode(&result)
	fmt.Println("response from Momo: ", result)

	fmt.Println()
	fmt.Println()
	fmt.Println()
}
