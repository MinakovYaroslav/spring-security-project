package com.minakov.springsecurityproject.dto.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.minakov.springsecurityproject.dto.AbstractDto;
import com.minakov.springsecurityproject.model.Skill;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Yaroslav Minakov
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminSkillDto extends AbstractDto {

    private String name;
    private Boolean status;

    @Builder
    public AdminSkillDto(Long id,
                         String name,
                         Boolean status) {
        super(id);
        this.name = name;
        this.status = status;
    }

    public Skill toEntity() {
        return Skill.builder()
                .id(id)
                .name(name)
                .status(status)
                .build();
    }

    public static AdminSkillDto toDto(Skill skill) {
        return AdminSkillDto.builder()
                .id(skill.getId())
                .name(skill.getName())
                .status(skill.getStatus())
                .build();
    }
}