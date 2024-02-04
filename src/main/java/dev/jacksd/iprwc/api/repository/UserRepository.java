package dev.jacksd.iprwc.api.repository;

import dev.jacksd.iprwc.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}
