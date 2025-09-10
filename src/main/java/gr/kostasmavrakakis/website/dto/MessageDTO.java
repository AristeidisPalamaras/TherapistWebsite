package gr.kostasmavrakakis.website.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageDTO {

    @NotBlank(message = "{validation.field.required}")
    @Size(max = 100, message = "{validation.field.tooLong}")
    private String name;
    
    @NotBlank(message = "{validation.field.required}")
    @Email(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "{validation.email.invalid}")
    @Size(max = 200, message = "{validation.field.tooLong}")
    private String email;

    @Pattern(regexp = "^[0-9+\\-()\\s]*$", message = "{validation.telephone.invalid}")
    @Size(max = 40, message = "{validation.field.tooLong}")
    private String telephone;

    @NotBlank(message = "{validation.field.required}")
    @Size(max = 4000, message = "{validation.field.tooLong}")
    private String message;
}