package com.mursey.account.dto.converter;

import com.mursey.account.dto.TransactionDto;
import com.mursey.account.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {
    public TransactionDto convert(Transaction from){
        return new TransactionDto(from.getId(),
                from.getTransactionType(),
                from.getAmount(),
                from.getTransactionDate());
    }
}
