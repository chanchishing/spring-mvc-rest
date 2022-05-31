package guru.springframework.services;

import guru.springframework.api.v1.model.*;

public interface VendorService {
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);
    VendorDTO createNewVendor(VendorDTO vendorDTO);
    void deleteVendor(Long id);
    VendorDTO patchVendor(Long id, VendorDTO vendorDTO);
    VendorDTO saveVendor(Long id, VendorDTO vendorDTO);

}
