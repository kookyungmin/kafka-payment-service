package net.happykoo.ps.representation.in.web;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.UUID;
import net.happykoo.ps.representation.in.web.request.order.Orderer;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrder;
import net.happykoo.ps.representation.in.web.request.order.PurchaseOrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
public class OrderControllerDocTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void newOrderTest() throws Exception {
    Orderer orderer = new Orderer("해피쿠", "010-1234-1234");
    List<PurchaseOrderItem> orderItems = List.of(
        new PurchaseOrderItem(1, UUID.randomUUID(), "사리곰탕", 1500, 1, 1500));
    PurchaseOrder newOrder = new PurchaseOrder(orderer, orderItems);

    String requestJson = objectMapper.writeValueAsString(newOrder);
    this.mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/orders/new")
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestJson))
        .andExpect(status().isOk())
        .andDo(document("CorrectRequestMessage",
                PayloadDocumentation.requestFields(
                    PayloadDocumentation.fieldWithPath("orderer.name").description("주문자명")
                        .attributes((key("constraint").value("주문자명을 입력 해주세요."))),
                    PayloadDocumentation.fieldWithPath("orderer.phoneNumber").description("주문자 휴대전화 번호")
                        .attributes((key("constraint").value("주문자명을 입력 해주세요."))),
                    PayloadDocumentation.fieldWithPath("newlyOrderedItem[].itemIdx")
                        .description("상세 주문 번호")
                        .attributes((key("constraint").value("상세 주문 번호"))),
                    PayloadDocumentation.fieldWithPath("newlyOrderedItem[].productId")
                        .description("상품 ID")
                        .attributes((key("constraint").value("상품 ID"))),
                    PayloadDocumentation.fieldWithPath("newlyOrderedItem[].productName")
                        .description("상품명")
                        .attributes((key("constraint").value("상품명"))),
                    PayloadDocumentation.fieldWithPath("newlyOrderedItem[].price")
                        .description("상품 단일 가격")
                        .attributes((key("constraint").value("상품 단일 가격"))),
                    PayloadDocumentation.fieldWithPath("newlyOrderedItem[].quantity")
                        .description("주문 수량")
                        .attributes((key("constraint").value("주문 수량"))),
                    PayloadDocumentation.fieldWithPath("newlyOrderedItem[].amounts")
                        .description("주문 상품 총 가격(상품 단일 가격 * 주문 수량)")
                        .attributes((key("constraint").value("주문 상품 총 가격(상품 단일 가격 * 주문 수량)")))
                )
            )
        );
  }


}
