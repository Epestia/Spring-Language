package be.ipam.language.bll.services.Iservices;

import be.ipam.language.api.dto.RoleEntityDto;

import java.util.List;

public interface IRoleService {

    RoleEntityDto create(RoleEntityDto dto);

    RoleEntityDto getById(Long id);

    List<RoleEntityDto> getAll();

    RoleEntityDto update(Long id, RoleEntityDto dto);

    void delete(Long id);
}
