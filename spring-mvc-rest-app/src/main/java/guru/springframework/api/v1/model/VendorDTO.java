package guru.springframework.api.v1.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorDTO {
    private Long id;
    @Schema(description = "Vendor name", required = true)
    private String name;
    private String vendor_url;
}
