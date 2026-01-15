package com.saroi.backend.service;

import com.saroi.backend.dto.MlResponse;
import com.saroi.backend.dto.TextEmbeddingResponse;

import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;


@Service
public class MlClientService {

    private final RestClient client;

    public MlClientService(RestClient client) {
        this.client = client;
    }

    public MlResponse analyze(Resource image) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", image); // MUST be named "file"
        return client.post()
                .uri("/analyze-image")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .body(MlResponse.class);
    }


    public float[] embedText(String text) {
        TextEmbeddingResponse response = client.post()
                .uri(uri -> uri.path("/embed-text")
                        .queryParam("text", text)
                        .build())
                .retrieve()
                .body(TextEmbeddingResponse.class);

        float[] out = new float[response.getEmbedding().size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = response.getEmbedding().get(i);
        }
        return out;
    }
}
