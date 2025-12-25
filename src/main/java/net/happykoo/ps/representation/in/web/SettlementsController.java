package net.happykoo.ps.representation.in.web;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import net.happykoo.ps.application.port.in.FetchPaymentSettlementUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/settlements")
@RequiredArgsConstructor
public class SettlementsController {

  private final FetchPaymentSettlementUseCase fetchPaymentSettlementUseCase;

  @PostMapping
  public void fetchSettlements() throws IOException {
    fetchPaymentSettlementUseCase.fetch();
  }

}
