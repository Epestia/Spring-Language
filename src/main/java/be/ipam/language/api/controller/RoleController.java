package be.ipam.language.api.controller;

import be.ipam.language.api.dto.RoleEntityDto;
import be.ipam.language.bll.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Scope("singleton")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleEntityDto> create(@RequestBody RoleEntityDto dto) {
        return ResponseEntity.ok(roleService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntityDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleEntityDto>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleEntityDto> update(@PathVariable Long id, @RequestBody RoleEntityDto dto) {
        return ResponseEntity.ok(roleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
