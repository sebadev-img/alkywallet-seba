package com.alkemy.wallet.service;

import com.alkemy.wallet.dto.request.RegisterRequestDto;
import com.alkemy.wallet.dto.response.JwtAuthenticationResponseDto;
import com.alkemy.wallet.entity.Role;
import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.enums.ERole;
import com.alkemy.wallet.repository.IRoleRepository;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService{

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final IJwtService jwtService;

    public AuthServiceImpl(IUserRepository userRepository,IRoleRepository roleRepository,PasswordEncoder passwordEncoder, JwtServiceImpl jwtService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public JwtAuthenticationResponseDto registerUser(RegisterRequestDto registerRequest) {
        User newUser = new User();
        newUser.setFirstName(registerRequest.getFirstName());
        newUser.setLastName(registerRequest.getLastName());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword())); //
        Role userRole = roleRepository.findByName(ERole.USER).get(); // find by nombre
        newUser.setRole(userRole); //TODO: el rol ya existe
        newUser.setAccounts(null); //TODO: crearle cuentas
        userRepository.save(newUser);
        return new JwtAuthenticationResponseDto(
                jwtService.generateToken(newUser)
        );
    }
}
