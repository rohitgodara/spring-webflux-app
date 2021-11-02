package com.catapi.service;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.catapi.constants.Message;
import com.catapi.dto.GoogleDTO;
import com.catapi.dto.UserRequestDTO;
import com.catapi.dto.UserResponseDTO;
import com.catapi.entity.User;
import com.catapi.exception.UserNotFoundException;
import com.catapi.repository.RoleRepository;
import com.catapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GoogleLoginService {

	@Autowired
	private UserRepository repository;
	@Autowired
	private RoleRepository roleRepository;

	@Value("${google.default.user.role}")
	private String defaultRole;

	@Value("${google.api.url}")
	private String googleAPIURL;

	private LocalDate localdate = LocalDate.now();
	private WebClient webclient = WebClient.create();

	public Mono<UserResponseDTO> updateUserInDatabase(UserRequestDTO dto) {

		return varifyUser(dto).flatMap(t -> {
			if (t.booleanValue()) {
				return repository.findByUserName(dto.getEmail()).flatMap(user -> {
					log.info("User Already Found");
					return getUserInformation(user);
				}).switchIfEmpty(updateDataBase(dto));
			} else {
				throw new UserNotFoundException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND);
			}
		});

	}

	private Mono<Boolean> varifyUser(UserRequestDTO dto) {

		return webclient.get().uri(googleAPIURL + dto.getToken()).retrieve().bodyToMono(GoogleDTO.class).map(t -> {

			if (t.getEmail().equalsIgnoreCase(dto.getEmail()) && t.isVerified_email()) {
				return true;
			} else {
				throw new UserNotFoundException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND);
			}
		}).doOnError(t -> new UserNotFoundException(HttpStatus.NOT_FOUND, Message.USER_NOT_FOUND));
	}

	private Mono<UserResponseDTO> getUserInformation(User user) {

		return roleRepository.findByName(defaultRole)
				.map(role -> new UserResponseDTO(user.getId(), role.getName(), user.getUserName(), user.getName()));
	}

	private Mono<UserResponseDTO> updateDataBase(UserRequestDTO dto) {
		return roleRepository.findByName(defaultRole).flatMap(role -> {
			log.info("Inserting User Information");
			return repository
					.save(new User(UUID.randomUUID(), dto.getEmail(), null, dto.getName(), role.getId(), true, localdate,
							localdate, true))
					.map(usr -> new UserResponseDTO(usr.getId(), role.getName(), usr.getUserName(), usr.getName()));
		});

	}
}
