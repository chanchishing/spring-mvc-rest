package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    VendorRepository vendorRepository;
    VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper=vendorMapper;
    }

    @Override
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(
                vendorRepository.findAll()
                        .stream()
                        .map(vendorMapper::vendorToVendorDTO)
                        .collect(Collectors.toList())
        );
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorMapper.vendorToVendorDTO(vendorRepository.findById(id).orElseThrow(ResourceNotFoundException::new));
    }
}
