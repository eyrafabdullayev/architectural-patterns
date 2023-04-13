package com.example.paymentservice.service;

import com.example.paymentservice.dto.event.OrderEvent;
import com.example.paymentservice.dto.PaymentDto;
import com.example.paymentservice.dto.event.PaymentEvent;
import com.example.paymentservice.entity.Transaction;
import com.example.paymentservice.enums.PaymentStatus;
import com.example.paymentservice.repository.TransactionRepository;
import com.example.paymentservice.repository.UserBalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserBalanceRepository userBalanceRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public PaymentEvent orderPayment(OrderEvent orderEvent) {
        PaymentDto payment = PaymentDto.of(orderEvent.getPurchaseOrder());
        double price = orderEvent.getPurchaseOrder().getPrice().doubleValue();
        int quantity = orderEvent.getPurchaseOrder().getQuantity();
        BigDecimal totalAmount = BigDecimal.valueOf(price * quantity);
        return userBalanceRepository.findByUserIdAndBalanceGreaterThanEqual(orderEvent.getPurchaseOrder().getUserId(), totalAmount)
                .map(ub -> {
                    ub.setBalance(ub.getBalance().subtract(totalAmount));
                    transactionRepository.save(Transaction.of(totalAmount, orderEvent.getPurchaseOrder()));
                    return new PaymentEvent(payment, PaymentStatus.RESERVED); })
                .orElse(new PaymentEvent(payment, PaymentStatus.REJECTED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        transactionRepository.findByOrderId(orderEvent.getPurchaseOrder().getId())
                .ifPresent(ut -> {
                    userBalanceRepository.findByUserId(orderEvent.getPurchaseOrder().getUserId())
                            .ifPresent(ub -> ub.setBalance(ub.getBalance().add(ut.getAmount())));
                    transactionRepository.delete(ut);
                });
    }
}
