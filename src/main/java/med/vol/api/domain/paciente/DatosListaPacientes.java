package med.vol.api.domain.paciente;

public record DatosListaPacientes(Long id, String nombre, String email, String documentoIdentidad) {

    public DatosListaPacientes(Paciente paciente)
    {
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
