package com.estagio.cursosLivres.services.validation;

import com.estagio.cursosLivres.config.exception.FieldMessage;
import com.estagio.cursosLivres.dto.user.UserInsertDTO;
import com.estagio.cursosLivres.entities.User;
import com.estagio.cursosLivres.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidation implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    @Override
    public boolean isValid(UserInsertDTO userInsertDTO, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        //Se precisar fazer mais alguma verificação, faremos aqui!!

        User user = userRepository.findByEmail(userInsertDTO.getEmail());

        if(user != null) {
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
