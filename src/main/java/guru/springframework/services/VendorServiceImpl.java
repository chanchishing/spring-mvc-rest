package guru.springframework.services;

import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.api.v1.model.VendorListDTO;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.VendorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;
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

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO)));
    }

    @Override
    public void deleteVendor(Long id) {
        vendorRepository.findById(id).map(vendor -> {
            vendorRepository.deleteById(id);
            return Optional.empty();
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id).map(vendor -> {

            if(vendorDTO.getName() != null){
                vendor.setName(vendorDTO.getName());
            }

            return vendorMapper.vendorToVendorDTO(vendorRepository.save(vendor));
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO saveVendor(Long id, VendorDTO vendorDTO) {

        vendorDTO.setId(id);
        Vendor savedVendor= vendorRepository.save(vendorMapper.vendorDTOToVendor(vendorDTO));

        return vendorMapper.vendorToVendorDTO(savedVendor);

    }

}
