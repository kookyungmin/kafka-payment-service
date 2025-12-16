package net.happykoo.ps.representation.in.web.common;

import java.util.List;
import org.springframework.http.HttpStatus;

public record ErrorResponse(
    List<StackTraceElement> stackTraces,
    String message,
    HttpStatus status
) {

}
