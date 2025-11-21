package be.ipam.language.bll.services;

import be.ipam.language.api.dto.RoleEntityDto;
import be.ipam.language.api.mapper.RoleEntityMapper;
import be.ipam.language.bll.exceptions.RoleException;
import be.ipam.language.bll.services.Iservices.IRoleService;
import be.ipam.language.dao.entities.RoleEntity;
import be.ipam.language.dao.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope("singleton")
@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    public RoleEntityDto create(RoleEntityDto dto) {
        if (roleRepository.existsByName(dto.getName())) {
            throw new RoleException("Role with name '" + dto.getName() + "' already exists.");
        }

        RoleEntity entity = roleEntityMapper.toEntity(dto);
        RoleEntity saved = roleRepository.save(entity);
        return roleEntityMapper.toDto(saved);
    }

    public RoleEntityDto getById(Long id) {
        RoleEntity entity = roleRepository.findById(id)
                .orElseThrow(() -> new RoleException("Role with ID " + id + " not found."));
        return roleEntityMapper.toDto(entity);
    }

    public List<RoleEntityDto> getAll() {
        return roleRepository.findAll().stream()
                .map(roleEntityMapper::toDto)
                .collect(Collectors.toList());
    }

    public RoleEntityDto update(Long id, RoleEntityDto dto) {
        RoleEntity existing = roleRepository.findById(id)
                .orElseThrow(() -> new RoleException("Role with ID " + id + " not found."));

        roleEntityMapper.partialUpdate(dto, existing);

        RoleEntity updated = roleRepository.save(existing);
        return roleEntityMapper.toDto(updated);
    }

    public void delete(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new RoleException("Role with ID " + id + " not found.");
        }
        roleRepository.deleteById(id);
    }
}
