package net.happykoo.ps.infrastructure.out.pg.toss.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.happykoo.ps.infrastructure.out.pg.toss.response.payment.Cancel;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentCancelResponse extends CommonPaymentResponse {

  private List<Cancel> cancels;

}
