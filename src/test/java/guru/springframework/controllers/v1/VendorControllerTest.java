package guru.springframework.controllers.v1;

import guru.springframework.api.v1.model.*;
import guru.springframework.controllers.RestResponseEntityExceptionHandler;
import guru.springframework.exceptions.ResourceNotFoundException;
import guru.springframework.services.VendorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VendorControllerTest {

    private AutoCloseable closeable;

    VendorController vendorController;

    @Mock
    VendorServiceImpl mockVendorService;

    @Mock
    private Model mockModel;
    MockMvc mockMvc;



    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        vendorController = new VendorController(mockVendorService);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void getAllVendors() throws Exception {
        VendorDTO vendor2DTO=new VendorDTO();
        VendorDTO vendor1DTO=new VendorDTO();
        Long vendor1ID=1L;
        Long vendor2ID=2L;
        String vendor1Name="Vendor 1 Name";
        String vendor2Name="Vendor 2 Name";
        vendor1DTO.setId(vendor1ID);
        vendor1DTO.setName(vendor1Name);
        vendor2DTO.setId(vendor2ID);
        vendor2DTO.setName(vendor2Name);

        VendorListDTO vendorDTOList= new VendorListDTO(Arrays.asList(vendor1DTO,vendor2DTO));

        when(mockVendorService.getAllVendors()).thenReturn(vendorDTOList);

        vendorController.getAllVendors();

        mockMvc.perform(get(Constant.API_V_1_VENDORS_URL).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(2)))
                .andExpect(jsonPath("$.vendors[0].id",equalTo(vendor1ID.intValue())));
        ;

    }

    @Test
    void getVendorById() throws Exception {
        VendorDTO vendor1 = new VendorDTO();
        Long vendor1ID=1L;
        String vendor1Name="Vendor 1 Name";
        vendor1.setId(vendor1ID);
        vendor1.setName(vendor1Name);

        when(mockVendorService.getVendorById(anyLong())).thenReturn(vendor1);

        mockMvc.perform(get(Constant.API_V_1_VENDORS_URL+"/"+vendor1ID.toString()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(vendor1ID.intValue())))
                .andExpect(jsonPath("$.name", equalTo(vendor1Name)));

    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        Long cat1ID=99L;

        when(mockVendorService.getVendorById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(Constant.API_V_1_VENDORS_URL + "/"+cat1ID.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}