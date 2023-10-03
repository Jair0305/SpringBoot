package med.vol.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.vol.api.domain.direccion.DatosDireccion;

public record DatosActualizarMedico(@NotNull Long id, String nombre, String documento, @Valid DatosDireccion direccion) {
}
