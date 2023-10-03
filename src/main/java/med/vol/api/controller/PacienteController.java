package med.vol.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.vol.api.domain.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrar(@RequestBody DatosRegistroPaciente datosRegistroPaciente, UriComponentsBuilder uriBuilder)
    {
        var paciente = new Paciente(datosRegistroPaciente);
        repository.save(paciente);

        var uri= uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalladoPacinete(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPacientes>> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion)
    {
        var page = repository.findAllByActivoTrue(paginacion).map(DatosListaPacientes::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizar(@RequestBody@Valid DatosActualizarPaciente datosActualizarPaciente)
    {
        var paciente = repository.getReferenceById(datosActualizarPaciente.id());
        paciente.actualizarInformacion(datosActualizarPaciente);

        return ResponseEntity.ok(new DatosDetalladoPacinete(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminar(@PathVariable Long id)
    {
        var paciente = repository.getReferenceById(id);
        paciente.inactivar();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detallar(@PathVariable Long id)
    {
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DatosDetalladoPacinete(paciente));
    }
}
