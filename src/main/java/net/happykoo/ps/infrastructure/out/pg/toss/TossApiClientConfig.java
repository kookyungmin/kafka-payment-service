package net.happykoo.ps.infrastructure.out.pg.toss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class TossApiClientConfig {

  @Value("${pg.toss.base-url}")
  private String baseUrl;
  @Value("${pg.toss.secret-key}")
  private String secretKey;

  @Bean
  public OkHttpClient okHttpClient() {
    Base64.Encoder encoder = Base64.getEncoder();
    byte[] encodedBytes = encoder.encode((secretKey + ":").getBytes(StandardCharsets.UTF_8));
    String authorizations = "Basic " + new String(encodedBytes);

    return new OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(chain -> {
          Request request = chain.request().newBuilder().addHeader("Authorization", authorizations)
              .build();
          return chain.proceed(request);
        })
        .build();
  }

  @Bean
  public Retrofit retrofit(OkHttpClient okHttpClient) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .client(okHttpClient)
        .build();
  }

  @Bean
  public TossPaymentApis createApiClient(Retrofit retrofit) {
    return retrofit.create(TossPaymentApis.class);
  }
}
