package com.europcar.create_redit_card.client.config;

import com.europcar.create_redit_card.exception.EmailException;
import com.europcar.create_redit_card.exception.ErrorResponse;
import com.europcar.create_redit_card.exception.ValidationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class NotificationErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    public NotificationErrorDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 400) {
            try (final Reader reader = response.body().asReader(StandardCharsets.UTF_8)) {
                ErrorResponse errorResponse = objectMapper.readValue(IOUtils.toString(reader), ErrorResponse.class);
                throw new EmailException(errorResponse.getId(),errorResponse.getMessage());
            } catch (IOException e) {
                log.error("Error while mapping error response", e);
            }

        } return new Exception("Exception while getting product details");
    }

}
