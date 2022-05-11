package guru.springframework.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDTO {
    private Long id;
    private String name;
    private String vendor_url;
}
