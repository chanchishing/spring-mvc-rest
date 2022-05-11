package guru.springframework.services;

import guru.springframework.api.v1.model.CategoryDTO;
import guru.springframework.api.v1.model.CategoryListDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;

public interface VendorService {
    VendorListDTO getAllVendors();
    VendorDTO getVendorById(Long id);
}
