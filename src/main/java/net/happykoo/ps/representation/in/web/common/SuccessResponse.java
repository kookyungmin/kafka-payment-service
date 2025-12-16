package net.happykoo.ps.representation.in.web.common;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SuccessResponse<T> {

  private final String status;
  private final T body;
}
