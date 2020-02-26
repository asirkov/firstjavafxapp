package javafxapp.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseDto {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
