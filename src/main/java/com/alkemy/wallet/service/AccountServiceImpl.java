package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.enums.ECurrency;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {


    private final IUserRepository userRepository;
    private final IAccountRepository accountRepository;

    public AccountServiceImpl(IUserRepository userRepository, IAccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public List<AccountDto> getAccountsByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            List<Account> accounts = optionalUser.get().getAccounts();
            List<AccountDto> accountsDto = accounts.stream().map(account -> {
                return new AccountDto(
                       optionalUser.get().getEmail(),
                       account.getId(),
                       account.getCurrency().name(),
                       account.getBalance(),
                       account.getTransactionLimit(),
                       null,
                       null
                );
            }).toList();
            return accountsDto;
        }
        return null;
    }

    @Override
    public Account createAccount(Long userId, ECurrency currency) {
        //TODO: user dto para mapear la respuesta
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Account> userAccounts = user.getAccounts();
            Optional<Account> accountOptional = userAccounts.stream()
                    .filter(account -> account.getCurrency() == currency)
                    .findFirst();
            if(accountOptional.isEmpty()){
                Account newAccount = new Account();
                newAccount.setCurrency(currency);
                newAccount.setTransactionLimit(currency == ECurrency.ARS ? 300000.0 : 1000.0);
                newAccount.setBalance(0.0);
                newAccount.setUser(user);
                accountRepository.save((newAccount));
            }
        }
        return null;
    }

}
