package med.vol.api.domain.paciente;

import jakarta.validation.Valid;
import med.vol.api.domain.direccion.DatosDireccion;

public record DatosActualizarPaciente(Long id, String nombre, String telefono, @Valid DatosDireccion direccion) {
}
