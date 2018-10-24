package com.ddoscollector.computer.controller;

import com.ddoscollector.computer.model.Computer;
import com.ddoscollector.computer.service.ComputerNotFoundException;
import com.ddoscollector.computer.service.ComputerService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * The Computer REST controller.
 */
@RestController()
@RequestMapping(value = ComputerController.URL)
public class ComputerController {

    static final String URL = "/computers";

    static final String URL_COMPUTER_RESOURCE = "/{computerId}";

    @Inject
    private ComputerService computerService;

    @RequestMapping(method = RequestMethod.POST)
    public Computer createComputer(@Valid @RequestBody final Computer computer) {
        return computerService.createComputer(computer);
    }

    @RequestMapping(path = URL_COMPUTER_RESOURCE, method = RequestMethod.GET)
    public Computer getComputer(@PathVariable final String computerId) {
        return computerService.getComputer(computerId).orElseThrow(ComputerNotFoundException::new);
    }

    @RequestMapping(path = URL_COMPUTER_RESOURCE, method = RequestMethod.PATCH)
    public Computer updateComputer(@PathVariable final String computerId, @Valid @RequestBody final Computer computerWithUpdate) {
        return computerService.updateComputer(computerId, computerWithUpdate);
    }

    @RequestMapping(path = URL_COMPUTER_RESOURCE, method = RequestMethod.DELETE)
    public void deleteComputer(@PathVariable final String computerId) {
        computerService.deleteComputer(computerId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Computer> getComputers() {
        return computerService.getComputers();
    }
}
