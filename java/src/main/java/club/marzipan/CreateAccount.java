package club.marzipan;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateAccount {

    @NotBlank
    @Size(min = 8, max = 16, message = "length must be 8 to 16 bytes")
    private String username;

    @NotBlank
    @Size(max = 64, message = "length must be 64 bytes or less")
    @Email(message = "malformed")
    private String email;

    @NotBlank
    @Size(min = 8, max = 64, message = "length must be 8 to 64 bytes")
    private String password;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
