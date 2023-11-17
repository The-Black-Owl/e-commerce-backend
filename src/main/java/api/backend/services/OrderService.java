package api.backend.services;

import api.backend.dto.checkout.CheckOutItemDTO;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;

import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class OrderService {
    @Value("${STRIPE_SECRET_kEY}")
    private String stripeApiKey;
    @Value("${BASE_URL}")
    private String baseURL;


    public Session createSession(List<CheckOutItemDTO> checkOutItemDTOList) throws StripeException {
        //strings of the failure and success URLs
        String successURL=baseURL+"/payment/success";
        String failURL=baseURL+"/payment/failed";
        //stripe api key
        Stripe.apiKey=stripeApiKey;
        //line item
        List<SessionCreateParams.LineItem> sessionItemList=new ArrayList<>();
        for(CheckOutItemDTO checkOutItemDTO:checkOutItemDTOList){
            sessionItemList.add(createSessionLineItem(checkOutItemDTO));
        }

        SessionCreateParams params=SessionCreateParams.builder()
                .addAllPaymentMethodType(Collections.singletonList(SessionCreateParams.PaymentMethodType.CARD))
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl(failURL)
                .setSuccessUrl(successURL)
                .addAllLineItem(sessionItemList)
                .build();
        return Session.create(params);
    }

    private SessionCreateParams.LineItem createSessionLineItem(CheckOutItemDTO checkOutItemDTO) {
        return SessionCreateParams.LineItem.builder()
                .setPriceData(createPriceData(checkOutItemDTO))
                .setQuantity(Long.parseLong(String.valueOf(checkOutItemDTO.getQuantity())))
        .build();
    }

    private SessionCreateParams.LineItem.PriceData createPriceData(CheckOutItemDTO checkOutItemDTO) {
        return SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("ZAR")
                .setUnitAmount(Long.parseLong(String.valueOf(checkOutItemDTO.getPrice()*100)))
                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName(checkOutItemDTO.getProductName())
                        .build()
                ).build();
    }
}
