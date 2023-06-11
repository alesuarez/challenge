package com.tenpo.challenge.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Record {
    @Id
    @TableGenerator(name = "tab", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tab")
    private long id;
    private LocalDateTime createdDate;
    private Double numberOne;
    private Double numberTwo;
    private Double percentageResponse;
    private String serverStatusResponse;
}
