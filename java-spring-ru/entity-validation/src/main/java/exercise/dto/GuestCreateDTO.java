package exercise.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

// BEGIN
@Getter
@Setter
public class GuestCreateDTO {

    @NotNull
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "^[+][0-9]*")
    @Size(min = 11, max = 13)
    private String phoneNumber;
    @Size(min = 4, max = 4)
    private String clubCard;
    @FutureOrPresent
    private LocalDate cardValidUntil;
}
// END
