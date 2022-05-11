package guru.springframework.api.v1.mapper;


import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(target = "vendor_url", expression = "java(guru.springframework.api.v1.model.Constant.API_V_1_VENDORS_URL+\"/\"+vendor.getId())")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
}

