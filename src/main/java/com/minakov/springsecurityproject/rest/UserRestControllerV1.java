package com.minakov.springsecurityproject.rest;

import com.minakov.springsecurityproject.dto.*;
import com.minakov.springsecurityproject.model.*;
import com.minakov.springsecurityproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yaroslav Minakov
 */
@RestController
@RequestMapping({
        "/api/v1/user/",
        "/api/v1/admin/",
        "/api/v1/moderator/"})
public class UserRestControllerV1 {

    private final UserService userService;
    private final SkillService skillService;
    private final TeamService teamService;
    private final ProjectService projectService;
    private final CustomerService customerService;

    @Autowired
    public UserRestControllerV1(UserService userService,
                                SkillService skillService,
                                TeamService teamService,
                                ProjectService projectService,
                                CustomerService customerService) {
        this.userService = userService;
        this.skillService = skillService;
        this.teamService = teamService;
        this.projectService = projectService;
        this.customerService = customerService;
    }

    @GetMapping(value = "users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = this.userService.findAllFetchSkills();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDto> usersDto = users.stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }

    @GetMapping(value = "users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId) {

        if (userId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = this.userService.findByIdFetchSkills(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserDto userDto = UserDto.toDto(user);

        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(value = "skills", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SkillDto>> getSkills() {
        List<Skill> skills = this.skillService.findAll();

        if (skills.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<SkillDto> skillsDto = skills.stream()
                .map(SkillDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(skillsDto, HttpStatus.OK);
    }

    @GetMapping(value = "skills/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SkillDto> getSkill(@PathVariable("id") Long skillId) {

        if (skillId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Skill skill = this.skillService.findById(skillId);

        if (skill == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        SkillDto skillDto = SkillDto.toDto(skill);

        return new ResponseEntity<>(skillDto, HttpStatus.OK);
    }

    @GetMapping(value = "teams", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TeamDto>> getTeams() {
        List<Team> teams = this.teamService.findAllFetchUsers();

        if (teams.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<TeamDto> teamsDto = teams.stream()
                .map(TeamDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(teamsDto, HttpStatus.OK);
    }

    @GetMapping(value = "teams/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeamDto> getTeam(@PathVariable("id") Long teamId) {

        if (teamId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Team team = this.teamService.findByIdFetchUsers(teamId);

        if (team == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TeamDto teamDto = TeamDto.toDto(team);

        return new ResponseEntity<>(teamDto, HttpStatus.OK);
    }

    @GetMapping(value = "projects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDto>> getProjects() {
        List<Project> projects = this.projectService.findAllFetchTeams();

        if (projects.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<ProjectDto> projectsDto = projects.stream()
                .map(ProjectDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(projectsDto, HttpStatus.OK);
    }

    @GetMapping(value = "projects/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDto> getProject(@PathVariable("id") Long projectId) {

        if (projectId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Project project = this.projectService.findByIdFetchTeams(projectId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProjectDto projectDto = ProjectDto.toDto(project);

        return new ResponseEntity<>(projectDto, HttpStatus.OK);
    }

    @GetMapping(value = "customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<Customer> customers = this.customerService.findAllFetchProjects();

        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<CustomerDto> customersDto = customers.stream()
                .map(CustomerDto::toDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(customersDto, HttpStatus.OK);
    }

    @GetMapping(value = "customers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("id") Long customerId) {

        if (customerId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Customer customer = this.customerService.findByIdFetchProjects(customerId);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CustomerDto customerDto = CustomerDto.toDto(customer);

        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
}