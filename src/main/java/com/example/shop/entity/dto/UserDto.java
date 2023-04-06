package com.example.shop.entity.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @NotBlank(message = "Username should not be blank or empty")
    private String username;

    @NotBlank(message = "City should not be blank or empty")
    @Length(min = 3, message = "City ")
    private String city;

    @NotBlank(message = "Username should not be blank or empty")
    @Length(min = 8, message = "Password ")
    private String password;

    private String role;

}
