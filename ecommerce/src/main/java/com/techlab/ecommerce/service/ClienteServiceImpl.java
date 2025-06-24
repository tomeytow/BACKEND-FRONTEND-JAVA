@Override
public Optional<Cliente> findByEmail(String email) {
    return clienteRepository.findByEmail(email);
}