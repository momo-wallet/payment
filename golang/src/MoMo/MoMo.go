package main

import (
	"bytes"
	"crypto/dsa"
	"crypto/ecdsa"
	"crypto/hmac"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
	"crypto/x509"
	b64 "encoding/base64"
	"encoding/hex"
	"encoding/json"
	"encoding/pem"
	"fmt"
	"log"
	"net/http"
	"os"
	"strconv"

	//run for download library `go get github.com/sony/sonyflake`
	"github.com/sony/sonyflake"
)

//define a payload, reference in https://developers.momo.vn/#cong-thanh-toan-momo-phuong-thuc-thanh-toan
type Payload struct {
	PartnerCode string `json:"partnerCode"`
	AccessKey   string `json:"accessKey"`
	RequestID   string `json:"requestId"`
	Amount      string `json:"amount"`
	OrderID     string `json:"orderId"`
	OrderInfo   string `json:"orderInfo"`
	ReturnURL   string `json:"returnUrl"`
	NotifyURL   string `json:"notifyUrl"`
	ExtraData   string `json:"extraData"`
	RequestType string `json:"requestType"`
	Signature   string `json:"signature"`
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

var pubKeyData = []byte(`
-----BEGIN PUBLIC KEY-----
MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkpa+qMXS6O11x7jBGo9W
3yxeHEsAdyDE40UoXhoQf9K6attSIclTZMEGfq6gmJm2BogVJtPkjvri5/j9mBnt
A8qKMzzanSQaBEbr8FyByHnf226dsLt1RbJSMLjCd3UC1n0Yq8KKvfHhvmvVbGcW
fpgfo7iQTVmL0r1eQxzgnSq31EL1yYNMuaZjpHmQuT24Hmxl9W9enRtJyVTUhwKh
tjOSOsR03sMnsckpFT9pn1/V9BE2Kf3rFGqc6JukXkqK6ZW9mtmGLSq3K+JRRq2w
8PVmcbcvTr/adW4EL2yc1qk9Ec4HtiDhtSYd6/ov8xLVkKAQjLVt7Ex3/agRPfPr
NwIDAQAB
-----END PUBLIC KEY-----
`)

func main() {
	fmt.Printf("Hello Momo!\n")

	flake := sonyflake.NewSonyflake(sonyflake.Settings{})
	//randome orderID and requestID
	a, err := flake.NextID()
	b, err := flake.NextID()

	var orderId = strconv.FormatUint(a, 16)
	var requestId = strconv.FormatUint(b, 16)
	var endpoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor"
	var partnerCode = "MOMOIQA420180417"
	var accessKey = "SvDmj2cOTYZmQQ3H"
	var serectkey = "PPuDXq1KowPT1ftR8DvlQTHhC03aul17"
	var orderInfo = "momo all-in-one"
	var returnUrl = "https://developers.momo.vn/"
	var notifyurl = "https://webhook.site/3c5b6488-a159-4f8d-b038-29eed82fab1e"
	var amount = "1000"
	var requestType = "captureMoMoWallet"
	var extraData = "merchantName=;merchantId=" //pass empty value if your merchant does not have stores else merchantName=[storeName]; merchantId=[storeId] to identify a transaction map with a physical store

	//build raw signature
	var rawSignature bytes.Buffer
	rawSignature.WriteString("partnerCode=")
	rawSignature.WriteString(partnerCode)
	rawSignature.WriteString("&accessKey=")
	rawSignature.WriteString(accessKey)
	rawSignature.WriteString("&requestId=")
	rawSignature.WriteString(requestId)
	rawSignature.WriteString("&amount=")
	rawSignature.WriteString(amount)
	rawSignature.WriteString("&orderId=")
	rawSignature.WriteString(orderId)
	rawSignature.WriteString("&orderInfo=")
	rawSignature.WriteString(orderInfo)
	rawSignature.WriteString("&returnUrl=")
	rawSignature.WriteString(returnUrl)
	rawSignature.WriteString("&notifyUrl=")
	rawSignature.WriteString(notifyurl)
	rawSignature.WriteString("&extraData=")
	rawSignature.WriteString(extraData)

	// Create a new HMAC by defining the hash type and the key (as byte array)
	hmac := hmac.New(sha256.New, []byte(serectkey))

	// Write Data to it
	hmac.Write(rawSignature.Bytes())
	fmt.Println("Raw signature: " + rawSignature.String())

	// Get result and encode as hexadecimal string
	signature := hex.EncodeToString(hmac.Sum(nil))

	var payload = Payload{
		PartnerCode: partnerCode,
		AccessKey:   accessKey,
		RequestID:   requestId,
		Amount:      amount,
		OrderID:     orderId,
		OrderInfo:   orderInfo,
		ReturnURL:   returnUrl,
		NotifyURL:   notifyurl,
		ExtraData:   extraData,
		RequestType: requestType,
		Signature:   signature,
	}

	var jsonPayload []byte
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
	fmt.Println("Response from Momo: ", result)

	fmt.Println()
	fmt.Println()
	fmt.Println()

	//PayUrl
	fmt.Println("PayUrl is: %s\n", result["payUrl"])
	fmt.Println()
	fmt.Println()
	fmt.Println()

	//READ ONLY
	//--------------FOR RSA Only for pay/pos, pay/app, pay/tokenization

	//1. TODO - Parse RSA Public key string to memory Public key
	block, _ := pem.Decode([]byte(pubKeyData))
	if block == nil {
		panic("failed to parse PEM block containing the public key")
	}
	pkixPub, err := x509.ParsePKIXPublicKey(block.Bytes)
	if err != nil {
		panic("failed to parse DER encoded public key: " + err.Error())
	}
	switch pkixPub := pkixPub.(type) {
	case *rsa.PublicKey:
		fmt.Println("RSA Public Key: {}")
		fmt.Println(pkixPub)
		fmt.Println()
	case *dsa.PublicKey:
		fmt.Println("DSA Public Key: {}")
		fmt.Println(pkixPub)
	case *ecdsa.PublicKey:
		fmt.Println("ECDSA Public Key: {}")
		fmt.Println(pkixPub)
	default:
		panic("Unknow Public Key")
	}
	var publicKey *rsa.PublicKey
	//TODO - Result public key in memory
	publicKey = pkixPub.(*rsa.PublicKey)
	/////////////////////////////////////

	//TODO - Define some json that to be encrypt to hash
	var refId = "112233445566"
	var newAmount = 10000
	var paymentCode = "339939393939393"
	var posHash = PosHash{
		PartnerCode:  partnerCode,
		PartnerRefID: refId,
		Amount:       newAmount,
		PaymentCode:  paymentCode,
	}
	var rawJashJson []byte
	rawJashJson, err = json.Marshal(posHash)
	if err != nil {
		log.Println(err)
	}
	//END define a Json before hash name `hashJson`

	//2. TODO - Encrypt PKCS1V15 RSA encryption using `publicKey` in memory
	randomReader := rand.Reader
	ciphertext, err := rsa.EncryptPKCS1v15(randomReader, publicKey, rawJashJson)
	if err != nil {
		fmt.Fprintf(os.Stderr, "Error from encryption: %s\n", err)
		return
	}
	var hash = b64.StdEncoding.EncodeToString(ciphertext)
	///////END encryption with `hash` value////////

	fmt.Println("Hash after encryption : {}")
	fmt.Println(hash)
	fmt.Println()

	var version = 2
	var posPayload = PosPayload{
		PartnerCode:  partnerCode,
		PartnerRefID: refId,
		Hash:         hash,
		Version:      version,
	}

	var posPayloadJson []byte
	posPayloadJson, err = json.Marshal(posPayload)
	if err != nil {
		log.Println(err)
	}
	fmt.Println("Payload with hash : {}")
	fmt.Println(string(posPayloadJson))
	fmt.Println()
}
