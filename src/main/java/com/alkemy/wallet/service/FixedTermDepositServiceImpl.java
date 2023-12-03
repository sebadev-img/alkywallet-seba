package com.alkemy.wallet.service;


import com.alkemy.wallet.dto.request.SimulateFixedTermDepositRequestDto;
import com.alkemy.wallet.dto.response.SimulateFixedTermDepositResponseDto;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

@Service
public class FixedTermDepositServiceImpl implements IFixedTermDepositService{

    private final IJwtService jwtService;
    private final IUserRepository userRepository;

    public FixedTermDepositServiceImpl(JwtServiceImpl jwtService, IUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }
    @Override
    public SimulateFixedTermDepositResponseDto simulateFixedTermDeposit(SimulateFixedTermDepositRequestDto fixedTermRequest, String token) {
        String userEmail = jwtService.extractUserName(token.substring(7));
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(userOptional.isPresent()){
            Double amount = fixedTermRequest.getAmount();
            int days = fixedTermRequest.getDays();
            if(amount > 0.0 && days > 0){
                return calculateFixedTermDeposit(amount,days);
            }
        }
        return null;
    }

    private SimulateFixedTermDepositResponseDto calculateFixedTermDeposit(Double amount, int days) {
        Double interestRate = 0.002;
        Double interestPerDay =  amount * interestRate;
        Double totalInterest = interestPerDay * days;
        Double totalValue = amount + totalInterest;
        Timestamp creationDate = new Timestamp(new Date().getTime());
        Timestamp closingDate = new Timestamp(new Date().getTime() + (long) days * 24 * 60 * 60 * 1000);
        return new SimulateFixedTermDepositResponseDto(
                amount,
                creationDate,
                closingDate,
                totalInterest,
                totalValue
        );
    }
}
