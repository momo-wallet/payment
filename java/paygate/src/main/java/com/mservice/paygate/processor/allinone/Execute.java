package com.mservice.paygate.processor.allinone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mservice.paygate.exception.MoMoException;
import com.mservice.paygate.utils.*;
import com.sun.istack.internal.NotNull;

@SuppressWarnings("restriction")
public class Execute {

    public static HttpResponse sendToMoMo(@NotNull String endpoint, @NotNull String payload) throws MoMoException {
        Headers headers = new Headers();
        headers.put("Content-Type", "application/json")
                .put("Charset", "utf-8");

        HttpRequest httpRequest = new HttpRequest("POST", payload, endpoint, headers);

        Console.debug("sendToMoMoServer::Endpoint::" + httpRequest.getUrl());
        Console.debug("sendToMoMoServer::RequestBody::" + httpRequest.getData());
        Console.debug("sendToMoMoServer::Header::" + httpRequest.getHeaders());

        HttpResponse httpResponse = HttpClient.post(httpRequest);

        Console.debug("sendToMoMoServer::ResponseStatus::" + httpResponse.getStatus());
        Console.debug("sendToMoMoServer::ResponseData::" + httpResponse.getData());

        return httpResponse;
    }
}
