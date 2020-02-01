package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.dto.*;
import com.minakov.springsecurityproject.model.*;
import com.minakov.springsecurityproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Minakov
 */
@RestController
@RequestMapping({
        "/api/v1/admin/",
        "/api/v1/moderator/"})
public class ModeratorRestControllerV1 {

    private final SkillService skillService;
    private final RoleService roleService;
    private final TeamService teamService;
    private final ProjectService projectService;
    private final CustomerService customerService;

    @Autowired
    public ModeratorRestControllerV1(RoleService roleService,
                                     SkillService skillService,
                                     TeamService teamService,
                                     ProjectService projectService,
                                     CustomerService customerService) {
        this.roleService = roleService;
        this.skillService = skillService;
        this.teamService = teamService;
        this.projectService = projectService;
        this.customerService = customerService;
    }

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoleDto>> getRoles() {
        List<Role> roles = this.roleService.findAll();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<RoleDto> adminsRoleDto = roles.stream()
                .map(RoleDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminsRoleDto, HttpStatus.OK);
    }

    @GetMapping(value = "roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> getRole(@PathVariable("id") Long roleId) {

        if (roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.findById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RoleDto roleDto = RoleDto.toDto(role);

        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }

    @PutMapping(value = "roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> updateRole(@RequestBody @Valid RoleDto roleDto, @PathVariable("id") Long roleId) {

        if (roleDto == null || roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!roleDto.getId().equals(roleId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.findById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        role = roleDto.toEntity(role);

        this.roleService.save(role);

        return new ResponseEntity<>(roleDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleDto> createRole(@RequestBody @Valid RoleDto roleDto) {

        if (roleDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Role role = roleDto.toEntity();
        roleDto = RoleDto.toDto(this.roleService.save(role));

        return new ResponseEntity<>(roleDto, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "skills/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> updateSkill(@RequestBody @Valid SkillDto skillDto, @PathVariable("id") Long skillId) {

        if (skillDto == null || skillId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!skillDto.getId().equals(skillId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Skill skill = this.skillService.findById(skillId);

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        skill = skillDto.toEntity(skill);

        this.skillService.save(skill);

        return new ResponseEntity<>(skillDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> createSkill(@RequestBody @Valid SkillDto skillDto) {

        if (skillDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Skill skill = skillDto.toEntity();
        skillDto = SkillDto.toDto(this.skillService.save(skill));

        return new ResponseEntity<>(skillDto, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDto> updateTeam(@RequestBody @Valid TeamDto teamDto, @PathVariable("id") Long teamId) {

        if (teamDto == null || teamId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!teamDto.getId().equals(teamId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Team team = this.teamService.findById(teamId);

        if (team == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        team = teamDto.toEntity(team);

        this.teamService.save(team);

        return new ResponseEntity<>(teamDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDto> createTeam(@RequestBody @Valid TeamDto teamDto) {

        if (teamDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Team team = teamDto.toEntity();
        teamDto = TeamDto.toDto(this.teamService.save(team));

        return new ResponseEntity<>(teamDto, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> updateProject(@RequestBody @Valid ProjectDto projectDto, @PathVariable("id") Long projectId) {

        if (projectDto == null || projectId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!projectDto.getId().equals(projectId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project project = this.projectService.findById(projectId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        project = projectDto.toEntity(project);

        this.projectService.save(project);

        return new ResponseEntity<>(projectDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> createProject(@RequestBody @Valid ProjectDto projectDto) {

        if (projectDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Project project = projectDto.toEntity();
        projectDto = ProjectDto.toDto(this.projectService.save(project));

        return new ResponseEntity<>(projectDto, headers, HttpStatus.CREATED);
    }

    @PutMapping(value = "customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid CustomerDto customerDto, @PathVariable("id") Long customerId) {

        if (customerDto == null || customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!customerDto.getId().equals(customerId)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = this.customerService.findById(customerId);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        customer = customerDto.toEntity(customer);

        this.customerService.save(customer);

        return new ResponseEntity<>(customerDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customerDto) {

        if (customerDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Customer customer = customerDto.toEntity();
        customerDto = CustomerDto.toDto(this.customerService.save(customer));

        return new ResponseEntity<>(customerDto, headers, HttpStatus.CREATED);
    }
}