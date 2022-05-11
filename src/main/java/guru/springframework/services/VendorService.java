package guru.springframework.services;

import guru.springframework.api.v1.model.*;
import guru.springframework.exceptions.ResourceNotFoundException;

import java.util.Optional;

public interface VendorService {
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    void deleteVendor(Long id);

}
