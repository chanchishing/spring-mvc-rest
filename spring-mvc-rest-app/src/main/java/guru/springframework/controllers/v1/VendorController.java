package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.*;
import guru.springframework.services.VendorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "vendor-controller", description = "This is the Vendor controller")
@RestController
@RequestMapping(Constant.API_V_1_VENDORS_URL)
public class VendorController {
    private final VendorService vendorService;


    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @Operation(summary = "This lists all vendors", description = "Please refer to VendorListDTO and VendorDTO for structure of return object")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public VendorListDTO getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.createNewVendor(vendorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return null;
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id,@RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id,vendorDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO saveVendor(@PathVariable Long id,@RequestBody VendorDTO vendorDTO) {
        return vendorService.saveVendor(id,vendorDTO);
    }

}
