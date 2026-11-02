package com.petersonsantos9k.picpay_desafio.service;

import com.petersonsantos9k.picpay_desafio.domain.User;
import com.petersonsantos9k.picpay_desafio.domain.UserType;
import com.petersonsantos9k.picpay_desafio.dto.UserRequestDTO;
import com.petersonsantos9k.picpay_desafio.dto.UserResponseDTO;
import com.petersonsantos9k.picpay_desafio.exception.UserException;
import com.petersonsantos9k.picpay_desafio.repository.UserRepository;
import com.petersonsantos9k.picpay_desafio.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDTO findById(UUID id){
        User obj = userRepository.findById(id)
            .orElseThrow(() -> new UserException(""));
        return userMapper.toResponse(obj);
    }

    public UserResponseDTO create(UserRequestDTO dto){
        if(userRepository.existsByTaxIdOrEmail(dto.taxId(), dto.email())){
            throw new UserException("");
        }

        UserType userType = dto.taxId().length() == 11 ? UserType.COMMON : UserType.MERCHANT;

        User obj = userMapper.toEntity(dto);
        obj.setUserType(userType);

        obj = userRepository.save(obj);
        return userMapper.toResponse(obj);
    }





}
