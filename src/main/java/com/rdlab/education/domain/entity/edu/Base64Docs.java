package com.rdlab.education.domain.entity.edu;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "base64docs")
public class Base64Docs {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base64docs_id_seq")
    @SequenceGenerator(name = "base64docs_id_seq", sequenceName = "base64docs_id_seq", allocationSize = 1)
    private Long id;

    private String filename;

    private String base64Docs;
}
