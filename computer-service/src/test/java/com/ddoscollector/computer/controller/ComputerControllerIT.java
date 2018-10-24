package com.ddoscollector.computer.controller;

import com.ddoscollector.computer.model.Computer;
import com.ddoscollector.computer.service.ComputerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ComputerControllerIT {

    @Inject
    private MockMvc mvc;

    @Inject
    private ComputerRepository computerRepository;

    @Inject
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        // delete all messages at the start to have a clean repeatable fixture
        computerRepository.deleteAll();
    }

    @Test
    public void create_computer_when_data_is_valid_creates_computer_and_returns_computer_and_ok_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "Mac", new Date(), "Location");
        final Computer computerReturned = createComputerViaRest(computer);

        computer.setId(computerReturned.getId());

        assertNotNull(computerReturned.getId());
        assertEquals(computer, computerReturned);
    }

    @Test
    public void create_computer_when_name_is_blank_returns_bad_request_status() throws Exception {
        final var computer = new Computer(null, "", "IP", "Mac", new Date(), "Location");
        create_computer_when_data_is_invalid_returns_bad_request_status(computer);
    }

    @Test
    public void create_computer_when_ip_is_blank_returns_bad_request_status() throws Exception {
        final var computer = new Computer(null, "Name", "", "Mac", new Date(), "Location");
        create_computer_when_data_is_invalid_returns_bad_request_status(computer);
    }

    @Test
    public void create_computer_when_mac_is_blank_returns_bad_request_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "", new Date(), "Location");
        create_computer_when_data_is_invalid_returns_bad_request_status(computer);
    }

    @Test
    public void create_computer_when_date_is_null_returns_bad_request_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "MAC", null, "Location");
        create_computer_when_data_is_invalid_returns_bad_request_status(computer);
    }

    private void create_computer_when_data_is_invalid_returns_bad_request_status(final Computer invalidComputer) throws Exception {
        final var computerJson = mapper.writeValueAsString(invalidComputer);

        mvc.perform(post(ComputerController.URL).content(computerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void get_computer_when_computer_exists_returns_computer_and_ok_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "Mac", new Date(), "Location");
        final Computer computerCreated = createComputerViaRest(computer);
        final Computer computerFetched = getComputerViaRest(computerCreated.getId());

        assertEquals(computerCreated, computerFetched);
    }

    @Test
    public void get_computer_when_computer_does_not_exist_returns_not_found_status() throws Exception {
        mvc.perform(get(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE, UUID.randomUUID()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_computer_when_computer_exists_returns_updated_computer_and_ok_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "Mac", new Date(), "Location");
        final var computerCreated = createComputerViaRest(computer);
        computerCreated.setName("Name updated");

        final var computerToUpdateJson = mapper.writeValueAsString(computerCreated);

        final MvcResult result = mvc.perform(patch(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE,
                computerCreated.getId())
                .content(computerToUpdateJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        final Computer computerUpdatedFetched = mapper.readValue(result.getResponse().getContentAsString(), Computer.class);
        assertEquals(computerCreated, computerUpdatedFetched);
    }

    @Test
    public void update_computer_when_computer_does_not_exist_returns_not_found_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "Mac", new Date(), "Location");
        final var computerJson = mapper.writeValueAsString(computer);

        mvc.perform(patch(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE,
                UUID.randomUUID())
                .content(computerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void update_computer_when_data_is_invalid_returns_bad_request_status() throws Exception {
        final var computerWithBlankName = new Computer(null, "", "IP", "Mac", new Date(), "Location");
        final var computerJson = mapper.writeValueAsString(computerWithBlankName);

        mvc.perform(patch(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE,
                UUID.randomUUID())
                .content(computerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void delete_computer_when_computer_exists_deletes_computer_and_returns_ok_status() throws Exception {
        final var computer = new Computer(null, "Name", "IP", "Mac", new Date(), "Location");
        final var computerCreated = createComputerViaRest(computer);

        mvc.perform(delete(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE,
                computerCreated.getId()))
                .andExpect(status().isOk()).andReturn();

        mvc.perform(get(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE, computerCreated.getId()))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void delete_computer_when_computer_does_not_exist_returns_not_found_status() throws Exception {
        mvc.perform(delete(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE,
                UUID.randomUUID()))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void get_computers_when_computers_exist_returns_computers_and_ok_status() throws Exception {
        final var computersCount = 3;
        for (int i = 0; i < 3; i++) {
            final Computer computer = new Computer(null, "Name", "IP", "MAC", new Date(), "Location");
            createComputerViaRest(computer);
        }

        final List<Computer> messagesFetched = getComputers();

        assertEquals(computersCount, messagesFetched.size());
    }

    @Test
    public void get_computers_when_no_computers_exist_returns_empty_list_and_ok_status() throws Exception {
        final List<Computer> messagesFetched = getComputers();

        assertEquals(0, messagesFetched.size());
    }


    private Computer createComputerViaRest(final Computer computer) throws Exception {
        final var computerJson = mapper.writeValueAsString(computer);
        final MvcResult result = mvc.perform(post(ComputerController.URL).content(computerJson).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        return mapper.readValue(result.getResponse().getContentAsString(), Computer.class);
    }

    private Computer getComputerViaRest(final String computerId) throws Exception {
        final MvcResult result = mvc.perform(get(ComputerController.URL + ComputerController.URL_COMPUTER_RESOURCE, computerId))
                .andExpect(status().isOk()).andReturn();

        return mapper.readValue(result.getResponse().getContentAsString(), Computer.class);
    }

    private List<Computer> getComputers() throws Exception {
        final MvcResult result = mvc.perform(get(ComputerController.URL))
                .andExpect(status().isOk()).andReturn();

        return mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Computer>>() {
        });
    }
}
