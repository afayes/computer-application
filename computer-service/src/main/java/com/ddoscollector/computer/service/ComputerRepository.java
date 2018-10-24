package com.ddoscollector.computer.service;

import com.ddoscollector.computer.model.Computer;
import org.springframework.data.repository.CrudRepository;

/**
 * This interface describes a computer repository.
 */
public interface ComputerRepository extends CrudRepository<Computer, String> {
}
