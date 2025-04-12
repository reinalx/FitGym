package com.fitGym.backend.rest.controllers;

import com.fitGym.backend.model.entities.User;
import com.fitGym.backend.model.exceptions.*;
import com.fitGym.backend.model.services.UserService;
import com.fitGym.backend.rest.common.ErrorsDto;
import com.fitGym.backend.rest.common.JwtGenerator;
import com.fitGym.backend.rest.common.JwtInfo;
import com.fitGym.backend.rest.dtos.AuthenticatedUserDto;
import com.fitGym.backend.rest.dtos.ChangePasswordParamsDto;
import com.fitGym.backend.rest.dtos.LoginParamsDto;
import com.fitGym.backend.rest.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Locale;

import static com.fitGym.backend.rest.dtos.UserConversor.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final static String INCORRECT_LOGIN_EXCEPTION_CODE = "project.exceptions.IncorrectLoginException";
    private final static String INCORRECT_PASSWORD_EXCEPTION_CODE = "project.exceptions.IncorrectPasswordException";

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JwtGenerator jwtGenerator;

    @Autowired
    private UserService userService;

    @ExceptionHandler(IncorrectLoginException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleIncorrectLoginException(IncorrectLoginException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORRECT_LOGIN_EXCEPTION_CODE, null,
                INCORRECT_LOGIN_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorsDto handleIncorrectPasswordException(IncorrectPasswordException exception, Locale locale) {

        String errorMessage = messageSource.getMessage(INCORRECT_PASSWORD_EXCEPTION_CODE, null,
                INCORRECT_PASSWORD_EXCEPTION_CODE, locale);

        return new ErrorsDto(errorMessage);

    }

    @PostMapping("/signUp")
    public ResponseEntity<AuthenticatedUserDto> signUp(
            @Validated({UserDto.AllValidations.class}) @RequestBody UserDto userDto) throws DuplicateInstanceException {

        User user = toUser(userDto);

        userService.signUp(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(toAuthenticatedUserDto(generateServiceToken(user), user));

    }

    @PostMapping("/login")
    public AuthenticatedUserDto login(@Validated @RequestBody LoginParamsDto params)
            throws IncorrectLoginException {

        User user = userService.login(params.getUserName(), params.getPassword());

        return toAuthenticatedUserDto(generateServiceToken(user), user);

    }

    @PostMapping("/loginFromServiceToken")
    public AuthenticatedUserDto loginFromServiceToken(@RequestAttribute Long userId,
                                                      @RequestAttribute String serviceToken) throws InstanceNotFoundException {

        User user = userService.loginFromId(userId);

        return toAuthenticatedUserDto(serviceToken, user);

    }

    @PutMapping("/{id}")
    public UserDto updateProfile(@RequestAttribute Long userId, @PathVariable Long id,
                                 @Validated({UserDto.UpdateValidations.class}) @RequestBody UserDto userDto)
            throws InstanceNotFoundException, PermissionException {

        if (!id.equals(userId)) {
            throw new PermissionException();
        }

        return toUserDto(userService.updateProfile(id, userDto.getFirstName(), userDto.getLastName(),
                userDto.getEmail()));

    }

    @PostMapping("/{id}/changePassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestAttribute Long userId, @PathVariable Long id,
                               @Validated @RequestBody ChangePasswordParamsDto params)
            throws PermissionException, InstanceNotFoundException, IncorrectPasswordException {

        if (!id.equals(userId)) {
            throw new PermissionException();
        }

        userService.changePassword(id, params.getOldPassword(), params.getNewPassword());

    }

    private String generateServiceToken(User user) {

        JwtInfo jwtInfo = new JwtInfo(user.getId(), user.getUserName(), user.getRole().toString());

        return jwtGenerator.generate(jwtInfo);

    }

}
