package pe.gob.casadelaliteratura.biblioteca.services.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.gob.casadelaliteratura.biblioteca.models.persona.Usuario;
import pe.gob.casadelaliteratura.biblioteca.repositories.persona.UsuarioRepository;
import pe.gob.casadelaliteratura.biblioteca.utils.enums.Estado;
import java.util.List;

@Service
public class DetalleUsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public DetalleUsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByPersona_NumeroDoc(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuario no encontrado.")
                );

        boolean state = usuario.getEstado() == Estado.ACTIVO;

        return new User(usuario.getPersona().getNumeroDoc(),
                usuario.getPassword(),
                state, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol())));
    }

    public Usuario obtenerUsuarioAutenticado() {

        String username = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();

        return usuarioRepository.findByPersona_NumeroDoc(username)
                .get();

    }

}
