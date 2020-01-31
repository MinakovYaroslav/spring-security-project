package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.dto.admin.*;
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
@RequestMapping({"/api/v1/admin/", "/api/v1/moderator/"})
public class ModeratorRestControllerV1 {

    private final SkillService skillService;
    private final RoleService roleService;
    private final TeamService teamService;
    private final ProjectService projectService;
    private final CustomerService customerService;
    private final UserService userService;

    @Autowired
    public ModeratorRestControllerV1(RoleService roleService,
                                     SkillService skillService,
                                     TeamService teamService,
                                     ProjectService projectService,
                                     CustomerService customerService,
                                     UserService userService) {
        this.roleService = roleService;
        this.skillService = skillService;
        this.teamService = teamService;
        this.projectService = projectService;
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminUserDto>> getUsers() {
        List<User> users = this.userService.findAllFetchSkills();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AdminUserDto> adminUsersDto = users.stream()
                .map(AdminUserDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminUsersDto, HttpStatus.OK);
    }

    @GetMapping(value = "users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminUserDto> getUser(@PathVariable("id") Long userId) {

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.findByIdFetchSkills(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdminUserDto adminUserDto = AdminUserDto.toDto(user);

        return new ResponseEntity<>(adminUserDto, HttpStatus.OK);
    }

    @GetMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminRoleDto>> getRoles() {
        List<Role> roles = this.roleService.findAll();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AdminRoleDto> adminsRoleDto = roles.stream()
                .map(AdminRoleDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminsRoleDto, HttpStatus.OK);
    }

    @GetMapping(value = "roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminRoleDto> getRole(@PathVariable("id") Long roleId) {

        if (roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Role role = this.roleService.findById(roleId);

        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdminRoleDto adminRoleDto = AdminRoleDto.toDto(role);

        return new ResponseEntity<>(adminRoleDto, HttpStatus.OK);
    }

    @PutMapping(value = "roles/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminRoleDto> updateRole(@RequestBody @Valid AdminRoleDto adminRoleDto, @PathVariable("id") Long roleId) {

        if (adminRoleDto == null || roleId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Role role = adminRoleDto.toEntity();

        this.roleService.save(role);

        return new ResponseEntity<>(adminRoleDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminRoleDto> createRole(@RequestBody @Valid AdminRoleDto adminRoleDto) {

        if (adminRoleDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Role role = adminRoleDto.toEntity();
        adminRoleDto = AdminRoleDto.toDto(this.roleService.save(role));
        //TODO CREATE TEAM WITH STATUS FALSE
        //TODO CREATE 2 METHODS FINDALL FOR ALL DTOs (FINDALL WITH STATUS FALSE AND FINDALL WITH STATUS TRUE)

        return new ResponseEntity<>(adminRoleDto, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminSkillDto>> getSkills() {
        List<Skill> skills = this.skillService.findAll();

        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AdminSkillDto> adminSkillsDto = skills.stream()
                .map(AdminSkillDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminSkillsDto, HttpStatus.OK);
    }

    @GetMapping(value = "skills/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminSkillDto> getSkill(@PathVariable("id") Long skillId) {

        if (skillId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Skill skill = this.skillService.findById(skillId);

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdminSkillDto adminSkillDto = AdminSkillDto.toDto(skill);

        return new ResponseEntity<>(adminSkillDto, HttpStatus.OK);
    }

    @PutMapping(value = "skills/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminSkillDto> updateSkill(@RequestBody @Valid AdminSkillDto adminSkillDto, @PathVariable("id") Long skillId) {

        if (adminSkillDto == null || skillId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Skill skill = adminSkillDto.toEntity();

        this.skillService.save(skill);

        return new ResponseEntity<>(adminSkillDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminSkillDto> createSkill(@RequestBody @Valid AdminSkillDto adminSkillDto) {

        if (adminSkillDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Skill skill = adminSkillDto.toEntity();
        adminSkillDto = AdminSkillDto.toDto(this.skillService.save(skill));

        return new ResponseEntity<>(adminSkillDto, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminTeamDto>> getTeams() {
        List<Team> teams = this.teamService.findAll();

        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AdminTeamDto> adminTeamsDto = teams.stream()
                .map(AdminTeamDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminTeamsDto, HttpStatus.OK);
    }

    @GetMapping(value = "teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminTeamDto> getTeam(@PathVariable("id") Long teamId) {

        if (teamId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Team team = this.teamService.findById(teamId);

        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdminTeamDto adminTeamDto = AdminTeamDto.toDto(team);

        return new ResponseEntity<>(adminTeamDto, HttpStatus.OK);
    }

    @PutMapping(value = "teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminTeamDto> updateTeam(@RequestBody @Valid AdminTeamDto adminTeamDto, @PathVariable("id") Long teamId) {

        if (adminTeamDto == null || teamId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Team team = adminTeamDto.toEntity();

        this.teamService.save(team);

        return new ResponseEntity<>(adminTeamDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminTeamDto> createTeam(@RequestBody @Valid AdminTeamDto adminTeamDto) {

        if (adminTeamDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Team team = adminTeamDto.toEntity();
        adminTeamDto = AdminTeamDto.toDto(this.teamService.save(team));

        return new ResponseEntity<>(adminTeamDto, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminProjectDto>> getProjects() {
        List<Project> projects = this.projectService.findAllFetchTeams();

        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AdminProjectDto> adminProjectsDto = projects.stream()
                .map(AdminProjectDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminProjectsDto, HttpStatus.OK);
    }

    @GetMapping(value = "projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminProjectDto> getProject(@PathVariable("id") Long projectId) {

        if (projectId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project project = this.projectService.findByIdFetchTeams(projectId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdminProjectDto adminProjectDto = AdminProjectDto.toDto(project);

        return new ResponseEntity<>(adminProjectDto, HttpStatus.OK);
    }

    @PutMapping(value = "projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminProjectDto> updateProject(@RequestBody @Valid AdminProjectDto adminProjectDto, @PathVariable("id") Long projectId) {

        if (adminProjectDto == null || projectId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Project project = adminProjectDto.toEntity();

        this.projectService.save(project);

        return new ResponseEntity<>(adminProjectDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminProjectDto> createProject(@RequestBody @Valid AdminProjectDto adminProjectDto) {

        if (adminProjectDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Project project = adminProjectDto.toEntity();
        adminProjectDto = AdminProjectDto.toDto(this.projectService.save(project));

        return new ResponseEntity<>(adminProjectDto, headers, HttpStatus.CREATED);
    }

    @GetMapping(value = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdminCustomerDto>> getCustomers() {
        List<Customer> customers = this.customerService.findAll();

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<AdminCustomerDto> adminCustomersDto = customers.stream()
                .map(AdminCustomerDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(adminCustomersDto, HttpStatus.OK);
    }

    @GetMapping(value = "customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminCustomerDto> getCustomer(@PathVariable("id") Long customerId) {

        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = this.customerService.findByIdFetchProjects(customerId); //TODO LazyInitializationException
        System.out.println();

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        AdminCustomerDto adminCustomerDto = AdminCustomerDto.toDto(customer);

        return new ResponseEntity<>(adminCustomerDto, HttpStatus.OK);
    }

    @PutMapping(value = "customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminCustomerDto> updateCustomer(@RequestBody @Valid AdminCustomerDto adminCustomerDto, @PathVariable("id") Long customerId) {

        if (adminCustomerDto == null || customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Customer customer = adminCustomerDto.toEntity();

        this.customerService.save(customer);

        return new ResponseEntity<>(adminCustomerDto, headers, HttpStatus.OK);
    }

    @PostMapping(value = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AdminCustomerDto> createCustomer(@RequestBody @Valid AdminCustomerDto adminCustomerDto) {

        if (adminCustomerDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        Customer customer = adminCustomerDto.toEntity();
        adminCustomerDto = AdminCustomerDto.toDto(this.customerService.save(customer));

        return new ResponseEntity<>(adminCustomerDto, headers, HttpStatus.CREATED);
    }
}
