package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.request.DepositRequestDto;
import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.entity.Transaction;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.enums.ETransaction;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService{

    private final IUserRepository userRepository;
    private final ITransactionRepository transactionRepository;
    private final IJwtService jwtService;

    public TransactionServiceImpl(IUserRepository userRepository, ITransactionRepository transactionRepository, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
        this.jwtService = jwtService;
    }

    @Override
    public Boolean createDeposit(DepositRequestDto depositRequest, String token){
        if(depositRequest.getAmount() < 0.0){
            return null;
        }
        String userEmail = jwtService.extractUserName(token.substring(7));
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Account> userAccounts = user.getAccounts();
            Optional<Account> accountOptional = userAccounts.stream()
                    .filter(account -> account.getCurrency().name().equals(depositRequest.getCurrency()))
                    .findFirst();
            if(accountOptional.isPresent()){
                Account account = accountOptional.get();
                Transaction newTransaction = new Transaction();
                newTransaction.setAmount(depositRequest.getAmount());
                newTransaction.setType(ETransaction.DEPOSIT);
                newTransaction.setDescription(depositRequest.getDescription());
                newTransaction.setAccount(account);
                transactionRepository.save(newTransaction);
                return true;

            }
        }
        return false;
    }
}
