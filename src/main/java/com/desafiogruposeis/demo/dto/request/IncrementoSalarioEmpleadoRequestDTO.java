package com.desafiogruposeis.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Validated
@Data
public class IncrementoSalarioEmpleadoRequestDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "la fecha tiene que ser igual o superior a la fecha actual")
    private LocalDate fecha;

    @NotBlank(message="El DNI no puede estar vacío")
    @Pattern(regexp="^[0-9]{8}$",message="El DNI debe poseer 8 caracteres numericos")
    private String dni;


    @DecimalMin(value = "0.1", inclusive = false, message = "El valor minimo es 0.1")
    @Digits(integer=4, fraction=2, message = "el valor entero maximo es de 4 digitos y para los decimales de 2 digitos")
    private BigDecimal monto;
}
