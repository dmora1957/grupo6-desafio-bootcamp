package com.desafiogruposeis.demo.models;

import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "empleados")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(description = "Información de los Empleados")
public class Empleado {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    @NotNull(message = "El nombre no puede ser Nulo")
    @Size(min = 2, message = "El nombre debe tener al menos dos caracteres")
    private String nombre;

    @Column(nullable = false, length = 50)
    @NotNull(message = "El apellido no puede ser Nulo")
    @Size(min = 2, message = "El apellido debe tener al menos dos caracteres")
    private String apellido;

    @Column(nullable = false, length = 50)
    @NotNull(message = "El dni no puede ser Nulo")
    @Size(min = 7, message = "El dni debe tener al menos ocho caracteres")
    private String dni;

    @Column(nullable = false)
    @NotNull(message = "La antiguedad no puede ser Nulo")
    private Integer antiguedad;

    @Column(name = "salario_actual", nullable = false)
    @NotNull(message = "El sueldo no puede ser Nulo")
    private BigDecimal salarioActual;

    @OneToMany(mappedBy = "unEmpleado", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.REMOVE }, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RegistroSalarioEmpleado> registrosSalariosEmpleados;

    public Empleado(@NotNull(message = "El dni no puede ser Nulo") @Size(min = 7, message = "El dni debe tener al menos ocho caracteres") String dni) {
        this.dni = dni;
    }
}
