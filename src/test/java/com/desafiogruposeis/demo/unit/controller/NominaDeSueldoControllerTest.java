package com.desafiogruposeis.demo.unit.controller;

import com.desafiogruposeis.demo.controllers.NominaDeSueldoController;
import com.desafiogruposeis.demo.dto.EmpleadoDTO;
import com.desafiogruposeis.demo.dto.RegistroSalarioEmpleadoDTO;
import com.desafiogruposeis.demo.dto.request.IncrementoSalarioEmpleadoRequestDTO;
import com.desafiogruposeis.demo.dto.response.IncrementoSalarioEmpleadoResponseDTO;
import com.desafiogruposeis.demo.enums.TipoExperiencia;
import com.desafiogruposeis.demo.services.crud.IEmpleadoService;
import com.desafiogruposeis.demo.services.crud.IRegistroSalarioEmpleadoService;
import com.desafiogruposeis.demo.services.impl.INominaDeSueldoServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NominaDeSueldoControllerTest {

    MockHttpServletRequest request = new MockHttpServletRequest();

    //1-Instanciar los objetos falsos con Mockito

    private IEmpleadoService empleadoServiceMock  = Mockito.mock(IEmpleadoService.class);

    private IRegistroSalarioEmpleadoService registroSalarioEmpleadoServiceMock  = Mockito.mock(IRegistroSalarioEmpleadoService.class);

    private final ModelMapper modelMapperMock = Mockito.mock(ModelMapper.class);

    //2- crear el sercive a provar con los objetos falsos como parametro

    private INominaDeSueldoServiceImpl iNominaDeSueldoService = Mockito.mock(INominaDeSueldoServiceImpl.class);

    private IncrementoSalarioEmpleadoRequestDTO incrementoSalarioEmpleadoRequestDTOMock = new IncrementoSalarioEmpleadoRequestDTO();

    @Autowired
    NominaDeSueldoController nominaDeSueldoController= new NominaDeSueldoController(iNominaDeSueldoService);

    @BeforeEach
    void setUp(){
        System.out.println("antes de la prueba");

        //3- Crear la respuesta esperada
        EmpleadoDTO EmpleadoDTOMock = new EmpleadoDTO();
        EmpleadoDTOMock.setId((Integer) 1);
        EmpleadoDTOMock.setNombre("Damian");
        EmpleadoDTOMock.setApellido("Betancur");
        EmpleadoDTOMock.setDni("3067736");
        EmpleadoDTOMock.setAntiguedad((Integer) 7);
        EmpleadoDTOMock.setSalarioActual(new BigDecimal(10));


        //4- setear la respuesta a la llamada

        Mockito.when(empleadoServiceMock.buscarEmpleadoPorDNI("30677736")).thenReturn(EmpleadoDTOMock);

        RegistroSalarioEmpleadoDTO registroSalarioEmpleadoDTOMock = new RegistroSalarioEmpleadoDTO();
        registroSalarioEmpleadoDTOMock.setId((Integer) 1);
        registroSalarioEmpleadoDTOMock.setUnEmpleado(EmpleadoDTOMock);
        registroSalarioEmpleadoDTOMock.setFecha( LocalDate.of(2021, 4, 19));
        registroSalarioEmpleadoDTOMock.setMonto(new BigDecimal(10));

        Mockito.when(registroSalarioEmpleadoServiceMock.registrar(registroSalarioEmpleadoDTOMock)).thenReturn(registroSalarioEmpleadoDTOMock);


        incrementoSalarioEmpleadoRequestDTOMock.setFecha( LocalDate.of(2021, 4, 19));
        incrementoSalarioEmpleadoRequestDTOMock.setDni("30677736");
        incrementoSalarioEmpleadoRequestDTOMock.setMonto(new BigDecimal(10));

        IncrementoSalarioEmpleadoResponseDTO incrementoSalarioEmpleadoResponseDTOMock = new IncrementoSalarioEmpleadoResponseDTO();
        incrementoSalarioEmpleadoResponseDTOMock.setUnEmpleado(empleadoServiceMock.buscarEmpleadoPorDNI("30677736"));
        incrementoSalarioEmpleadoResponseDTOMock.setUnTipoExperiencia(TipoExperiencia.CON_EXPERIENCIA);



        Mockito.when(iNominaDeSueldoService.incrementarSalario(incrementoSalarioEmpleadoRequestDTOMock)).thenReturn(incrementoSalarioEmpleadoResponseDTOMock);

    }

    @Test
    void incrementarSalario() {

        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ResponseEntity <IncrementoSalarioEmpleadoResponseDTO> respuesta = nominaDeSueldoController.incrementarSalarioEmpleado(incrementoSalarioEmpleadoRequestDTOMock);

        Assertions.assertEquals("Betancur",respuesta.getBody().getUnEmpleado().getApellido());
    }

    @AfterEach
    void tearDown(){
        System.out.println("Despues de la prueba");
    }
}