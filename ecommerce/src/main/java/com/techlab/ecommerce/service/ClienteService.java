import java.util.Optional;

public interface ClienteService {
    // ... otros métodos
    Optional<Cliente> findByEmail(String email);
}