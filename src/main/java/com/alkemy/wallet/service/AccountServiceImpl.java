package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.AccountDto;
import com.alkemy.wallet.dto.request.UpdateAccountRequestDto;
import com.alkemy.wallet.dto.response.PageableAccountResponseDto;
import com.alkemy.wallet.entity.Account;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.enums.ECurrency;
import com.alkemy.wallet.repository.IAccountRepository;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {


    private final IUserRepository userRepository;
    private final IAccountRepository accountRepository;
    private final IJwtService jwtService;

    public AccountServiceImpl(IUserRepository userRepository, IAccountRepository accountRepository, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
    }

    @Override
    public PageableAccountResponseDto getAllAccounts(int page) {
        int pageToFind = page > 0 ? page-1 : 0;
        PageRequest pr = PageRequest.of(pageToFind,10);
        Page<Account> accountPage = accountRepository.findAll(pr);
        long count = accountPage.getTotalElements();
        int pages = accountPage.getTotalPages();
        String prevPage = accountPage.hasPrevious() ? "/api/v1/users?page="+(page-1) : null;
        String nextPage = accountPage.hasNext() ? "/api/v1/users?page="+(page+1) : null;
        if(pages < page){
            return null;
        }
        List<Account> accounts = accountPage.getContent();
        List<AccountDto> accountsDto = accounts.stream().map(account -> {
            return new AccountDto(
                    account.getUser().getEmail(),
                    account.getId(),
                    account.getCurrency().name(),
                    account.getBalance(),
                    account.getTransactionLimit()
            );
        }).toList();
        return new PageableAccountResponseDto(
                count,
                pages,
                prevPage,
                nextPage,
                accountsDto
        );
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
                       account.getTransactionLimit()
                );
            }).toList();
            return accountsDto;
        }
        return null;
    }

    @Override
    public AccountDto createAccount(String currency, String token) {
        //TODO: user dto para mapear la respuesta
        String userEmail = jwtService.extractUserName(token.substring(7));
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            List<Account> userAccounts = user.getAccounts();
            Optional<Account> accountOptional = userAccounts.stream()
                    .filter(account -> account.getCurrency().name().equals(currency))
                    .findFirst();
            if(accountOptional.isEmpty()){
                Account newAccount = new Account();
                newAccount.setCurrency(ECurrency.valueOf(currency));
                newAccount.setTransactionLimit(currency.equals(ECurrency.ARS.name()) ? 300000.0 : 1000.0);
                newAccount.setBalance(0.0);
                newAccount.setUser(user);
                accountRepository.save((newAccount));
            }
        }
        return null;
    }

    @Override
    public AccountDto updateTransactionLimit(Long id, UpdateAccountRequestDto updateRequest,String token) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        if(accountOptional.isPresent()){
            Account account = accountOptional.get();
            String userEmail = jwtService.extractUserName(token.substring(7));
            Optional<User> userOptional = userRepository.findByEmail(userEmail);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                if(Objects.equals(account.getUser().getId(), user.getId())){
                    if(updateRequest.getNewTransactionLimit() > 0.0){
                        account.setTransactionLimit(updateRequest.getNewTransactionLimit());
                        accountRepository.save(account);
                        return new AccountDto(
                                userEmail,
                                account.getId(),
                                account.getCurrency().name(),
                                account.getBalance(),
                                account.getTransactionLimit()
                        );
                    }
                }
            }
        }
        return null;
    }

}
