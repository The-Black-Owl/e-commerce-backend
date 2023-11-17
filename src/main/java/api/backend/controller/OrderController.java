package api.backend.controller;

import api.backend.dto.checkout.CheckOutItemDTO;
import api.backend.dto.checkout.StripeResponse;
import api.backend.services.OrderService;

import com.stripe.exception.StripeException;

import com.stripe.model.checkout.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //stripe session checkout
    @PostMapping("/create-checkout-session")
    public ResponseEntity<StripeResponse> checkOutList(@RequestBody List<CheckOutItemDTO> checkOutItemDTOList)
    throws StripeException {
        Session session=orderService.createSession(checkOutItemDTOList);
        return ResponseEntity.ok(new StripeResponse(session.getId()));
    }

}
