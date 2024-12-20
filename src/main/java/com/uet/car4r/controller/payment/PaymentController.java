package com.uet.car4r.controller.payment;

import com.uet.car4r.dto.response.PaymentResponse;
import com.uet.car4r.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;


    @GetMapping("/vn-pay")
    public PaymentResponse<PaymentDTO.VNPayResponse> pay(HttpServletRequest request) {
        return new PaymentResponse<>(HttpStatus.OK, "Success", paymentService.createVnPayPayment(request));
    }
    @GetMapping("/vn-pay-callback")
    public PaymentResponse<PaymentDTO.VNPayResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        if (status.equals("00")) {
            return new PaymentResponse<>(HttpStatus.OK, "Success", new PaymentDTO.VNPayResponse("00", "Success", ""));
        } else {
            return new PaymentResponse<>(HttpStatus.BAD_REQUEST, "Failed",null);
        }
    }
}