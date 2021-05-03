package com.desafiogruposeis.demo.controllers;

import com.desafiogruposeis.demo.dto.EmpleadoDTO;
import com.desafiogruposeis.demo.services.crud.IEmpleadoService;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(EmpleadoController.RESOURCE)
public class EmpleadoController {

    public static final String RESOURCE = "/api/v1/empleados";

    public static final String DNI = "/{dni}";
    public static final String SEARCH = "/search";

    private IEmpleadoService empleadoService;

    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }


    @RequestMapping(method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public ResponseEntity<EmpleadoDTO> create(@RequestBody EmpleadoDTO nuevoEmpleado) {
        EmpleadoDTO eempleadoRespuesta = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Responded", "EmpleadoController");

        try {
            if (empleadoService.buscarEmpleadoPorDNI(nuevoEmpleado.getDni())==null){
                eempleadoRespuesta = empleadoService.registrar(nuevoEmpleado);
            }

        } catch(Exception ex) {
            System.out.println("Employee not found" + ex.getMessage());
            return ResponseEntity.ok().headers(headers).body(eempleadoRespuesta);
        }

        return ResponseEntity.ok().headers(headers).body(eempleadoRespuesta);
    }

    @GetMapping(DNI)
    public ResponseEntity<EmpleadoDTO> read(@PathVariable(value = "dni") String dni) {
        LogManager.getLogger(this.getClass()).info("===>>> read: " + dni);
        EmpleadoDTO empleadoBuscado = (EmpleadoDTO) empleadoService.buscarEmpleadoPorDNI(dni);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
                .buildAndExpand(empleadoBuscado.getId()).toUri();
        return new ResponseEntity<EmpleadoDTO>(empleadoBuscado, HttpStatus.OK).created(location).body(empleadoBuscado);
    }

    @PutMapping(DNI)
    public ResponseEntity<EmpleadoDTO> update(@PathVariable(value = "dni") String dni, @RequestBody EmpleadoDTO enpleadoDTO) {
        LogManager.getLogger(this.getClass()).info("===>>> update: " + dni + ", " + enpleadoDTO);
        EmpleadoDTO empleadoBuscado = (EmpleadoDTO) empleadoService.registrar(enpleadoDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{dni}")
                .buildAndExpand(empleadoBuscado.getId()).toUri();
        return new ResponseEntity<EmpleadoDTO>(empleadoBuscado, HttpStatus.OK).created(location).body(empleadoBuscado);
    }

    @DeleteMapping(DNI)
    public ResponseEntity<EmpleadoDTO> delete(@PathVariable(value = "dni") String dni) {
        LogManager.getLogger(this.getClass()).info("===>>> delete: " + dni);
        EmpleadoDTO empleadoBuscado = (EmpleadoDTO) empleadoService.buscarEmpleadoPorDNI(dni);

        empleadoService.eliminar(empleadoBuscado.getId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/empleado/{dni}")
                .buildAndExpand(empleadoBuscado.getId()).toUri();
        return new ResponseEntity<EmpleadoDTO>(empleadoBuscado, HttpStatus.OK).created(location).body(empleadoBuscado);
    }


}
