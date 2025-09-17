package com.estagio.cursosLivres.services.validation;

import com.estagio.cursosLivres.config.exception.FieldMessage;
import com.estagio.cursosLivres.dto.user.UserUpdateDTO;
import com.estagio.cursosLivres.entities.User;
import com.estagio.cursosLivres.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    @Override
    public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext context) {

        @SuppressWarnings("unchecked")
        var  uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();
        //Se precisar fazer mais alguma verificação, faremos aqui!!

        User user = userRepository.findByEmail(userUpdateDTO.getEmail());
        if(user != null && userId != user.getId()){
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();

    }
}
