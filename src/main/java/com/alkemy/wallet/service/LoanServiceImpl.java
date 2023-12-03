package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.request.LoanRequestDto;
import com.alkemy.wallet.dto.response.LoanResponseDto;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanServiceImpl implements ILoanService{

    private final IJwtService jwtService;
    private final IUserRepository userRepository;

    public LoanServiceImpl(JwtServiceImpl jwtService, IUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }


    @Override
    public LoanResponseDto calculateLoan(LoanRequestDto loanRequest, String token) {
        String userEmail = jwtService.extractUserName(token.substring(7));
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if(userOptional.isPresent()){
            Double amount = loanRequest.getAmount();
            int months = loanRequest.getMonths();
            if(amount > 0.0 && months > 0){
                return calculateLoan(amount,months);
            }
        }
        return null;
    }

    private LoanResponseDto calculateLoan(Double amount, int months) {
        Double interest = 0.05;
        Double paymentPerMonth = amount/months + amount * interest;
        Double totalInterest = (amount*interest)*months;
        Double totalPayment = amount + totalInterest;
        return new LoanResponseDto(
                amount,
                months,
                "5% monthly",
                paymentPerMonth,
                totalInterest,
                totalPayment
        );
    }
}
