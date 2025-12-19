package net.happykoo.ps.representation.in.web.common;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ApiResponse<T> {

  private String status;
  private T body;
  private LocalDateTime timestamp;

  public ApiResponse(String status, T body) {
    this.status = status;
    this.body = body;
    this.timestamp = LocalDateTime.now();
  }
}
