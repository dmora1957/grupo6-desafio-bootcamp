package com.desafiogruposeis.demo.repositories;


import com.desafiogruposeis.demo.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Empleado findByDni(String dni);

}
