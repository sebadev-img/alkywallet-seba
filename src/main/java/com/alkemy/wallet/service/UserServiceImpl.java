package com.alkemy.wallet.service;

import com.alkemy.wallet.entity.User;
import com.alkemy.wallet.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User deleteUserById(Long id) {
        //TODO: el usuario solo se puede eliminar a si mismo, si es admin puede eliminar a cualquiera
        Optional<User> userOptional =userRepository.findById(id);
        if(userOptional.isPresent()){
            User user=userOptional.get();
            user.setSoftDelete(Boolean.TRUE);
            userRepository.save(user);
            return user;
        }
        return null;
    }
}
