package net.happykoo.ps.representation.in.web.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

//모든 타입을 CommonMessageConverter 가 제어하기 위함
@Component
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class CommonHttpMessageConverter extends AbstractHttpMessageConverter<ApiResponse<Object>> {

  private final ObjectMapper objectMapper;

  @Override
  protected ApiResponse<Object> readInternal(Class<? extends ApiResponse<Object>> clazz,
      HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    throw new UnsupportedOperationException("This converter can only support writing operation.");
  }

  @Override
  protected void writeInternal(ApiResponse<Object> objectApiResponse,
      HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    String responseMessage = this.objectMapper.writeValueAsString(objectApiResponse);
    StreamUtils.copy(responseMessage.getBytes(StandardCharsets.UTF_8), outputMessage.getBody());
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return clazz.equals(ApiResponse.class) || clazz.isPrimitive() || clazz.equals(String.class);
  }

  @Override
  public List<MediaType> getSupportedMediaTypes() {
    return Collections.singletonList(MediaType.APPLICATION_JSON);
  }
}
