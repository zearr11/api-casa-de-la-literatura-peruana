package pe.gob.casadelaliteratura.biblioteca.dtos.prestamo.prestamo.response.complements;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pe.gob.casadelaliteratura.biblioteca.utils.enums.TipoEntrega;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetalleDevolucionResponseDto {

    private TipoEntrega estadoEntrega;

    // Opcional
    private DetalleProblemaResponseDto problema;

    // Opcional
    private DetalleSolucionResponseDto solucion;

}
