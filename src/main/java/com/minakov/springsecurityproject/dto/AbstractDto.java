package com.minakov.springsecurityproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Yaroslav Minakov
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDto implements Serializable {

    protected Long id;
}
