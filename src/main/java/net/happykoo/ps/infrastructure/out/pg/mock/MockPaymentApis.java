package net.happykoo.ps.infrastructure.out.pg.mock;

import java.util.List;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentSettlementsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MockPaymentApis {

  @GET("settlements")
  Call<List<PaymentSettlementsResponse>> paymentSettlements();
}
