package com.desafiogruposeis.demo.dto.response;

import com.desafiogruposeis.demo.dto.EmpleadoDTO;
import com.desafiogruposeis.demo.enums.TipoExperiencia;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class IncrementoSalarioEmpleadoResponseDTO {
    private EmpleadoDTO unEmpleado;
    private TipoExperiencia unTipoExperiencia;
}
