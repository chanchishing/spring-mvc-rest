package guru.springframework.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    @Schema(description = "The customer's first name", required = true)
    private String firstname;
    @Schema(description = "The customer's last name", required = true)
    private String lastname;
    private String customer_url;
}
