package guru.springframework.services;


import guru.springframework.api.v1.mapper.VendorMapper;
import guru.springframework.api.v1.model.*;
import guru.springframework.domain.Vendor;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.repositories.VendorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class VendorServiceImplTest {

    AutoCloseable closeable;

    VendorServiceImpl vendorService;

    @Mock
    VendorRepository mockVendorRepository;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        vendorService = new VendorServiceImpl(mockVendorRepository, VendorMapper.INSTANCE);
    }


    @Test
    void getAllVendors() {
        Vendor vendor1 = new Vendor();
        Vendor vendor2 = new Vendor();
        List<Vendor> vendorList = new ArrayList<Vendor>(Arrays.asList(vendor1, vendor2));

        when(mockVendorRepository.findAll()).thenReturn(vendorList);

        VendorListDTO vendorDTOList = vendorService.getAllVendors();
        assertEquals(vendorList.size(), vendorDTOList.getVendors().size());
    }

    @Test
    void getVendorById() {
        Vendor vendor1 = new Vendor();
        Long testID1 = 7L;
        String testName = "vendor name";
        vendor1.setId(testID1);
        vendor1.setName(testName);
        Optional<Vendor> vendorOptional = Optional.of(vendor1);
        given(mockVendorRepository.findById(anyLong())).willReturn(vendorOptional);

        VendorDTO vendorDTO = vendorService.getVendorById(testID1);

        then(mockVendorRepository).should(times(1)).findById(testID1);
        assertThat(vendorDTO.getId(), is(equalTo(testID1)));
        assertThat(vendorDTO.getName(), is(equalTo(testName)));
        assertThat(vendorDTO.getVendor_url(),is(equalTo(Constant.API_V_1_VENDORS_URL + "/" + testID1.toString())));

    }


    @Test
    void createNewVendor() {
        Vendor vendor1=new Vendor();
        Long testID1=7L;
        String testName="vendor name";
        vendor1.setId(testID1);
        vendor1.setName(testName);

        VendorDTO vendorDTO1=new VendorDTO();
        vendorDTO1.setId(testID1);
        vendorDTO1.setName(testName);

        when(mockVendorRepository.save(any(Vendor.class))).thenReturn(vendor1);

        VendorDTO savedVendorDTO = vendorService.createNewVendor(vendorDTO1);

        verify(mockVendorRepository,times(1)).save(any(Vendor.class));
        assertEquals(testID1,savedVendorDTO.getId());
        assertEquals(testName,savedVendorDTO.getName());
        assertEquals(Constant.API_V_1_VENDORS_URL+"/"+testID1.toString(),savedVendorDTO.getVendor_url());

    }

    @Test
    void deleteVendor() {
        Long testID1=7L;

        assertThrows(ResourceNotFoundException.class,()->{vendorService.deleteVendor(testID1);});

        verify(mockVendorRepository,times(1)).findById(testID1);

    }

    @Test
    void patchVendor() {
        Vendor vendor1=new Vendor();
        Long testID1=7L;
        String testName="vendor name";
        vendor1.setId(testID1);
        vendor1.setName(testName);

        VendorDTO vendorDTO1=new VendorDTO();
        vendorDTO1.setId(testID1);
        vendorDTO1.setName(testName);

        //This mock setup cannot test patch service internal logic.
        //This unit test only verify service, repository components are wired up as expected
        when(mockVendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor1));
        when(mockVendorRepository.save(any(Vendor.class))).thenReturn(vendor1);

        VendorDTO patchedVendorDTO=vendorService.patchVendor(testID1,vendorDTO1);

        verify(mockVendorRepository,times(1)).save(any(Vendor.class));
        assertEquals(testID1,patchedVendorDTO.getId());
        assertEquals(testName,patchedVendorDTO.getName());
        assertEquals(Constant.API_V_1_VENDORS_URL+"/"+testID1.toString(),patchedVendorDTO.getVendor_url());

    }

    @Test
    void saveVendor() {
        Vendor vendor1=new Vendor();
        Long testID1=7L;
        String testName="vendor name";
        vendor1.setId(testID1);
        vendor1.setName(testName);

        VendorDTO vendorDTO1=new VendorDTO();
        vendorDTO1.setId(testID1);
        vendorDTO1.setName(testName);

        when(mockVendorRepository.save(any(Vendor.class))).thenReturn(vendor1);

        VendorDTO savedVendorDTO=vendorService.saveVendor(testID1,vendorDTO1);

        verify(mockVendorRepository,times(1)).save(any(Vendor.class));
        assertEquals(testID1,savedVendorDTO.getId());
        assertEquals(testName,savedVendorDTO.getName());
        assertEquals(Constant.API_V_1_VENDORS_URL+"/"+testID1.toString(),savedVendorDTO.getVendor_url());

    }

}