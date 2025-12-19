package net.happykoo.ps.infrastructure.out.pg.toss;

import java.util.List;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentApprovedResponse;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentCancelResponse;
import net.happykoo.ps.infrastructure.out.pg.toss.response.PaymentSettlementsResponse;
import net.happykoo.ps.representation.in.web.request.payment.PaymentApproved;
import net.happykoo.ps.representation.in.web.request.payment.PaymentCancel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TossPaymentApis {

  @POST("payments/confirm")
  Call<PaymentApprovedResponse> paymentFulfill(@Body PaymentApproved requestMessage);

  @POST("payments/{paymentKey}/cancel")
  Call<PaymentCancelResponse> paymentCancel(@Path("paymentKey") String paymentKey,
      @Body PaymentCancel requestMessage);

  //정산
  @GET("settlements")
  Call<List<PaymentSettlementsResponse>> paymentSettlements(@Path("startDate") String startDate,
      @Path("endDate") String endDate,
      @Path("page") int page,
      @Path("size") int size);


}
