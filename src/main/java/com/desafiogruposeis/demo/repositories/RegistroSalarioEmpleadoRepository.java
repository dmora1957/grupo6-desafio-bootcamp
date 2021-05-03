package com.desafiogruposeis.demo.repositories;


import com.desafiogruposeis.demo.models.Empleado;
import com.desafiogruposeis.demo.models.RegistroSalarioEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistroSalarioEmpleadoRepository extends JpaRepository<RegistroSalarioEmpleado, Integer> {

    List<RegistroSalarioEmpleado> findByUnEmpleado(Empleado empleado);

}
