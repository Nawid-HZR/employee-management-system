package com.nawid.EMS.service.impl;

import com.nawid.EMS.dto.request.PositionRequest;
import com.nawid.EMS.dto.response.PositionResponse;
import com.nawid.EMS.entity.Position;
import com.nawid.EMS.exception.BadRequestException;
import com.nawid.EMS.exception.ResourceNotFoundException;
import com.nawid.EMS.repo.PositionRepository;
import com.nawid.EMS.service.PositionService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final ModelMapper mapper;

    public PositionServiceImpl(PositionRepository positionRepository, ModelMapper mapper) {
        this.positionRepository = positionRepository;
        this.mapper = mapper;
    }

    @Override
    public PositionResponse create(PositionRequest request) {

        if (positionRepository.existsByTitle(request.getTitle())) {
            throw new BadRequestException("Position already exists");
        }

        Position position = new Position();
        position.setTitle(request.getTitle());
        position.setDescription(request.getDescription());

        Position saved = positionRepository.save(position);

        return mapper.map(saved, PositionResponse.class);
    }

    @Override
    public PositionResponse getById(Long id) {

        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        return mapper.map(position, PositionResponse.class);
    }

    @Override
    public Page<PositionResponse> getAll(Pageable pageable) {

        return positionRepository.findAll(pageable)
                .map(pos -> mapper.map(pos, PositionResponse.class));
    }

    @Override
    public PositionResponse update(Long id, PositionRequest request) {

        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        position.setTitle(request.getTitle());
        position.setDescription(request.getDescription());

        Position updated = positionRepository.save(position);

        return mapper.map(updated, PositionResponse.class);
    }

    @Override
    public String delete(Long id) {

        Position position = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found"));

        positionRepository.delete(position);

        return "Deleted successfully";
    }
}