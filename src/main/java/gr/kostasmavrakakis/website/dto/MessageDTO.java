package gr.kostasmavrakakis.website.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {

    @NotBlank(message = "{field.required}")
    private String name;
    
    @NotBlank(message = "{field.required}")
    @Email(message = "{field.invalid.email}")
    private String email;

    private String telephone;

    @NotBlank(message = "{field.required}")
    private String message;
}