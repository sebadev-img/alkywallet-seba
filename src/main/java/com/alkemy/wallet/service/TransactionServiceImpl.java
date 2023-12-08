package com.alkemy.wallet.service;


import com.alkemy.wallet.dto.request.TransactionRequestDto;
import com.alkemy.wallet.dto.response.TransactionResponseDto;
import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.entity.Transaction;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.enums.ERole;
import com.alkemy.wallet.enums.ETransactionType;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.ITransactionRepository;
import com.alkemy.wallet.repository.IUserRepository;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements ITransactionService {

    private final IUserRepository userRepository;
    private final IAccountRepository accountRepository;
    private final ITransactionRepository transactionRepository;
    private final IJwtService jwtService;

    public TransactionServiceImpl(IUserRepository userRepository, IAccountRepository accountRepository, ITransactionRepository transactionRepository, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
        this.jwtService = jwtService;
    }

    @Override
    public TransactionResponseDto getTransaction(Long id, String token) {
        Optional<Transaction> transactionOptional = transactionRepository.findById(id);
        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            User transactionOwner = transaction.getAccount().getUser();
            String userEmail = jwtService.extractUserName(token.substring(7));
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getRole().getName() == ERole.ADMIN || Objects.equals(transactionOwner.getId(), user.getId())) {
                    return new TransactionResponseDto(
                            transactionOwner.getEmail(),
                            transaction.getAccount().getId(),
                            transaction.getId(),
                            transaction.getAccount().getCurrency().name(),
                            transaction.getType().name(),
                            transaction.getAmount(),
                            transaction.getDescription(),
                            transaction.getTransactionDate()
                    );
                }
            }
        }
        return null;
    }




    @Override
    public Boolean createDeposit(TransactionRequestDto depositRequest, String token) {
        if (depositRequest.getAmount() < 0.00) {
            return null;
        }
        String userEmail = jwtService.extractUserName(token.substring(7));
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Account> userAccounts = user.getAccounts();
            Optional<Account> accountOptional = userAccounts.stream()
                    .filter(account -> account.getCurrency().name().equals(depositRequest.getCurrency()))
                    .findFirst();
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                Transaction newTransaction = new Transaction();
                newTransaction.setAmount(depositRequest.getAmount());
                newTransaction.setType(ETransactionType.DEPOSIT);
                newTransaction.setDescription(depositRequest.getDescription());
                newTransaction.setAccount(account);
                transactionRepository.save(newTransaction);

                account.setBalance(account.getBalance() + depositRequest.getAmount());
                accountRepository.save(account);
                return true;

            }
        }
        return false;
    }

    @Override
    public TransactionResponseDto createPayment(TransactionRequestDto paymentRequest, String token) {
        if (paymentRequest.getAmount() < 0.00) {
            return null;
        }
        String userEmail = jwtService.extractUserName(token.substring(7));
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Account> userAccounts = user.getAccounts();
            Optional<Account> accountOptional = userAccounts.stream()
                    .filter(account -> account.getCurrency().name().equals(paymentRequest.getCurrency()))
                    .findFirst();
            if (accountOptional.isPresent()) {
                Account account = accountOptional.get();
                if (account.getBalance() >= paymentRequest.getAmount()) {
                    Transaction newTransaction = new Transaction();
                    newTransaction.setAmount(paymentRequest.getAmount());
                    newTransaction.setType(ETransactionType.DEPOSIT);
                    newTransaction.setDescription(StringUtils.hasText(paymentRequest.getDescription()) ? paymentRequest.getDescription() : "");
                    newTransaction.setAccount(account);
                    Transaction transactionCreated = transactionRepository.save(newTransaction);

                    account.setBalance(account.getBalance() - paymentRequest.getAmount());
                    accountRepository.save(account);
                    return new TransactionResponseDto(
                            user.getEmail(),
                            account.getId(),
                            transactionCreated.getId(),
                            paymentRequest.getCurrency(),
                            ETransactionType.PAYMENT.name(),
                            transactionCreated.getAmount(),
                            transactionCreated.getDescription(),
                            transactionCreated.getTransactionDate()
                    );
                }
            }
        }
        return null;
    }

}











