package guru.springframework.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String customer_url;
}
