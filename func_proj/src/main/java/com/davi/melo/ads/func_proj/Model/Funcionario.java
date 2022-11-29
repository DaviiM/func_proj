package com.davi.melo.ads.func_proj.Model;
import com.davi.melo.ads.func_proj.Model.Projeto;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
public class Funcionario {

    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "funcionario_generator")
    private Long id;

    @Column(length = 120, nullable = false)
    private String nome;

    @Column(length = 50, nullable = false)
    private String email;

    @Column(length = 15, nullable = false)
    private String celular;

    @Column(length = 60, nullable = false)
    private String cargo;

    //@Column(precision = 10, scale = 2, nullable = false)
    @Column(columnDefinition = "decimal(10,2)", nullable = false)
    private Double salario;

    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "funcionario_projeto",
                joinColumns = {@JoinColumn(name = "funcionario_id")},
                inverseJoinColumns = {@JoinColumn(name = "projeto_id")})

    private Set<Projeto> projetos = new HashSet<>();
    
}
