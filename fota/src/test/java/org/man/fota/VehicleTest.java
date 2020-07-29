package org.man.fota;


import org.junit.jupiter.api.Test;
import org.man.fota.controller.VehicleController;
import org.man.fota.controller.service.FeatureService;
import org.man.fota.model.dto.FeatureDTO;
import org.man.fota.util.exceptions.VehicleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashSet;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Testing all status code from {@link VehicleController}
 */
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@WebMvcTest(VehicleController.class)
public class VehicleTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FeatureService service;

    @Test
    @DisplayName("Testing Vehicle not found exception")
    public void getInstallableVehicleException() throws Exception {
        given(service.getInstallableFeatures(any(UUID.class))).willThrow(new VehicleNotFoundException());
        MockHttpServletResponse response = mockMvc.perform(
                get("/vehicles/{vin}/installable", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    @DisplayName("Testing Vehicle not found exception")
    public void getIncompatibleVehicleException() throws Exception {
        given(service.getIncompatibleFeatures(any(UUID.class))).willThrow(new VehicleNotFoundException());
        MockHttpServletResponse response = mockMvc.perform(
                get("/vehicles/{vin}/incompatible", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(204, response.getStatus());
    }

    @Test
    @DisplayName("Testing all incompatibles")
    public void getIncompatible() throws Exception {
        given(service.getIncompatibleFeatures(any(UUID.class))).willReturn(new HashSet<FeatureDTO>());
        MockHttpServletResponse response = mockMvc.perform(
                get("/vehicles/{vin}/incompatible", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Testing all installables")
    public void getInstallable() throws Exception {
        given(service.getIncompatibleFeatures(any(UUID.class))).willReturn(new HashSet<FeatureDTO>());
        MockHttpServletResponse response = mockMvc.perform(
                get("/vehicles/{vin}/installable", UUID.randomUUID())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Testing invalid UUID")
    public void getInstallableVehicleInvalidUUID() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                get("/vehicles/{vin}/installable", "INVALID_UUID")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(400, response.getStatus());

        response = mockMvc.perform(
                get("/vehicles/{vin}/installable", 1234567890)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(400, response.getStatus());

        response = mockMvc.perform(
                get("/vehicles/{vin}/installable", "92be8ada-0fc4-4c08-b2ab81909785cd3d")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(400, response.getStatus());
    }

    @Test
    @DisplayName("Testing invalid UUID")
    public void getIncompatibleVehicleInvalidUUID() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                get("/vehicles/{vin}/incompatible", "INVALID_UUID")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(400, response.getStatus());

        response = mockMvc.perform(
                get("/vehicles/{vin}/incompatible", 1234567890)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(400, response.getStatus());

        response = mockMvc.perform(
                get("/vehicles/{vin}/incompatible", "92be8ada-0fc4-4c08-b2ab81909785cd3d")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        Assert.assertEquals(400, response.getStatus());
    }


}
