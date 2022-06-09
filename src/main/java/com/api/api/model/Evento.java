package com.api.api.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventos")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "localidad")
    private String localidad;
}
