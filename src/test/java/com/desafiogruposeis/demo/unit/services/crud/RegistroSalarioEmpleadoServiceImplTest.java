package com.desafiogruposeis.demo.unit.services.crud;

import com.desafiogruposeis.demo.repositories.RegistroSalarioEmpleadoRepository;
import com.desafiogruposeis.demo.services.crud.impl.RegistroSalarioEmpleadoServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RegistroSalarioEmpleadoServiceImplTest {

    @Mock
    private RegistroSalarioEmpleadoRepository registroSalarioEmpleadoRepository;

    @InjectMocks
    private RegistroSalarioEmpleadoServiceImpl registroSalarioEmpleadoEmpleadoService;

    @BeforeEach
    public void init() {

    }

    @Test
    public void registrar() {

    }

    @Test
    public void modificar() {
    }

    @Test
    public void eliminar() {
    }

    @Test
    public void listarId() {
    }

    @Test
    public void listarTodos() {
    }

    @Test
    public void buscarRegistrosSalariosEmpleadosPorEmpleado() {
    }
}