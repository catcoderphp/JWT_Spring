package com.catcoderdev.auth.Service;

import com.catcoderdev.auth.Model.ResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;

@Service
public class RequestHandlerServiceImpl {
    private String url;
    private RestTemplate restClient;
    private HttpHeaders headers;
    private HttpStatus status;

    public RequestHandlerServiceImpl() {
        this.restClient = new RestTemplate();
        this.headers = new HttpHeaders();
        this.headers.add("Content-Type", "Application/json");
        this.headers.add("Accept", "*/*");
    }

    public ResponseHandler get(String url) throws JsonProcessingException {
        HttpEntity<String> request = new HttpEntity<String>("", this.headers);
        ResponseEntity<String> response = this.restClient.exchange(url, HttpMethod.GET, request, String.class);
        this.status = response.getStatusCode();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.getBody(), ResponseHandler.class);

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        if (this.URLValidator(url)) {
            this.url = url;
        }
    }

    private boolean URLValidator(String url) {
        try {
            URL urlValidator = new URL(url);
            return true;
        } catch (MalformedURLException exception) {
            return false;
        }
    }

}
