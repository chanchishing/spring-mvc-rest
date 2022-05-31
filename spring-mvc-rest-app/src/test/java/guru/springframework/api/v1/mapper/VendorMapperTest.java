package guru.springframework.api.v1.mapper;

import guru.springframework.api.v1.model.Constant;
import guru.springframework.api.v1.model.VendorDTO;
import guru.springframework.domain.Vendor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    private final String VENDORNAME ="vendor name";
    private final Long ID=99L;
    

    @Test
    void customerToCustomerDTO() {
        //given
        Vendor vendor = new Vendor();
        vendor.setName(VENDORNAME);
        vendor.setId(ID);

        //when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        //then
        assertEquals(Long.valueOf(ID), vendorDTO.getId());
        assertEquals(VENDORNAME, vendorDTO.getName());
        assertEquals(Constant.API_V_1_VENDORS_URL+"/"+ID.toString(),vendorDTO.getVendor_url());
        
    }

    @Test
    void vendorDTOToVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName(VENDORNAME);
        vendorDTO.setId(ID);

        //when
        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        //then
        assertEquals(Long.valueOf(ID), vendor.getId());
        assertEquals(VENDORNAME, vendor.getName());

    }

}