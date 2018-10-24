package com.ddoscollector.computer.service;

import com.ddoscollector.computer.model.Computer;

import java.util.List;
import java.util.Optional;

/**
 * This interface describes a computer service.
 */
public interface ComputerService {

    /**
     * Create a computer.
     *
     * @param computer the computer to create
     * @return the created computer
     */
    Computer createComputer(Computer computer);

    /**
     * Get computer.
     *
     * @param id the ID of the computer to get
     * @return an optional with computer if it exists or an empty optional otherwise
     */
    Optional<Computer> getComputer(String id);

    /**
     * Update computer.
     *
     * @param id                 the ID of the computer to update
     * @param computerWithUpdate the computer with the update
     * @return the updated computer
     */
    Computer updateComputer(String id, Computer computerWithUpdate);

    /**
     * Delete computer.
     *
     * @param id the ID of the computer to delete
     */
    void deleteComputer(String id);

    /**
     * Get computers.
     *
     * @return the computers
     */
    List<Computer> getComputers();
}
