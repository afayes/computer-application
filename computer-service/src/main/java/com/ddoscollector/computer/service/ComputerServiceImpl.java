package com.ddoscollector.computer.service;

import com.ddoscollector.computer.model.Computer;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ComputerServiceImpl implements ComputerService {

    @Inject
    private ComputerRepository computerRepository;

    @Override
    public Computer createComputer(final Computer computer) {
        Validate.notNull(computer, "computer must not be null");

        computer.setId(UUID.randomUUID().toString());
        // todo check if computer exists with that ID
        return computerRepository.save(computer);
    }

    @Override
    public Optional<Computer> getComputer(final String id) {
        Validate.notBlank(id, "id must not be null");

        return computerRepository.findById(id);
    }

    @Override
    public Computer updateComputer(final String id, final Computer computerWithUpdate) {
        Validate.notBlank(id, "id must not be null");

        final Computer computer = getComputer(id).orElseThrow(ComputerNotFoundException::new);
        computerWithUpdate.setId(computer.getId());

        return computerRepository.save(computerWithUpdate);
    }

    @Override
    public void deleteComputer(final String id) {
        Validate.notBlank(id, "id must not be null");

        final Optional<Computer> computerOp = getComputer(id);
        final var computer = computerOp.orElseThrow(ComputerNotFoundException::new);
        computerRepository.delete(computer);
    }

    @Override
    public List<Computer> getComputers() {
        final Iterable<Computer> all = computerRepository.findAll();
        final var computers = new ArrayList<Computer>();
        all.forEach(computers::add);
        return computers;
    }
}
