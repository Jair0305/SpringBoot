package med.vol.api.domain.paciente;

import med.vol.api.domain.direccion.Direccion;

public record DatosDetalladoPacinete(String nombre, String email, String telefono, String documentoIdentidad, Direccion direccion) {
    public DatosDetalladoPacinete(Paciente paciente)
    {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getTelefono(), paciente.getDocumentoIdentidad(), paciente.getDireccion());
    }
}
