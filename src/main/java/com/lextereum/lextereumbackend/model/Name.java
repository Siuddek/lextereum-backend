package com.lextereum.lextereumbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "names")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    @Id
    private Long id;
    private String name;
    private String language;
}
