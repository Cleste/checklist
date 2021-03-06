package ru.kgeu.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @SequenceGenerator(
            name = "role_id_seq_Gen",
            sequenceName = "role_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_seq_Gen")
    private Long id;

    private String name;

    @Column(name = "rus_name")
    private String rusName;

    @OneToMany(mappedBy = "role")
    private List<User> users;
}
