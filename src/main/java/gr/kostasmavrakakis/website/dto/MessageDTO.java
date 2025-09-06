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

    @NotBlank(message = "{validation.field.required}")
    private String name;
    
    @NotBlank(message = "{validation.field.required}")
    @Email(message = "{validation.email.invalid}")
    private String email;

    private String telephone;

    @NotBlank(message = "{validation.field.required}")
    private String message;
}