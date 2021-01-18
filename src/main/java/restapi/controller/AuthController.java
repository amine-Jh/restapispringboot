package restapi.controller;



import restapi.details.UserDetailsImpl;
import restapi.model.Company;
import restapi.model.Etudiant;
import restapi.model.User;
import restapi.payload.CompanySignup;
import restapi.payload.JwtResponse;
import restapi.payload.LoginRequest;
import restapi.payload.MessageResponse;
import restapi.payload.SignupRequest;
import restapi.payload.StudentSignup;
import restapi.repository.*;
import restapi.security.*;
import restapi.services.EmailSenderService;
import restapi.model.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	/***
	 * email services injection
	 */
	@Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;
	
	
	@Autowired
    private EmailSenderService emailSenderService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository roleRepository;
	
	
	@Autowired
	JwtUtils jwtUtils;
	
	@PreAuthorize("permitAll()")
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		 if (!userRepository.existsByUsername(loginRequest.getUsername())) {
			return ResponseEntity
					.ok()
					.body(new MessageResponse(" cet utilisateur n'existe pas "));
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		
			return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getName(),
												   roles ));
		
		
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getName(),encoder.encode(signUpRequest.getPassword()) , 
							 signUpRequest.getEmail(),signUpRequest.getUsername());

		userRepository.save(user);
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Success : New User registred!")) ;  
		
		
}
	
	
	@PostMapping("/signupStudent")
	public ResponseEntity<?> registerUser(@RequestBody StudentSignup signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.ok()
					.body(new MessageResponse("Error:  username est deja utilisé  "));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.ok()
					.body(new MessageResponse("Error:  Email déja utilisé"));
		}

		// Create new Student's account
		 Etudiant user= new Etudiant( signUpRequest.getName() , encoder.encode(signUpRequest.getPassword()) ,signUpRequest.getEmail(),
				 signUpRequest.getFilliere() ,signUpRequest.getUsername(), signUpRequest.getAnnee(),signUpRequest.getTelephone()
				 );
		 
		 Set<String> strRoles = signUpRequest.getRoles();
			Set<Role> roles = new HashSet<>();
			
			
		
			
			
			
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "company":
						Role CompanyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(CompanyRole);

						break;
					default:
						Role EtudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(EtudiantRole);
					}
				});
			}
			
			
			
			user.setRoles(roles);
			
			userRepository.save(user);
			
			/**
			ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
            +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);
			**/
			

		userRepository.save(user);
		return ResponseEntity
				.ok()
				.body(new MessageResponse("Welcome : New Etudiant inscrit !")) ;  
		
		
}
	
	@PostMapping("/signupCompany")
	public ResponseEntity<?> registerCompany(@RequestBody CompanySignup signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.ok()
					.body(new MessageResponse("Error:  username est deja utilisé  "));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.ok()
					.body(new MessageResponse("Error:  Email déja utilisé"));
		}

		// Create new Company account 
		 Company user= new Company(signUpRequest.getName(),encoder.encode(signUpRequest.getPassword()), signUpRequest.getEmail(), signUpRequest.getUsername() ,
				 signUpRequest.getAdresse()   , signUpRequest.getType(), signUpRequest.getTelephone());
		 
		 
		 Set<String> strRoles = signUpRequest.getRoles();
			Set<Role> roles = new HashSet<>();

			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "company":
						Role CompanyRole = roleRepository.findByName(ERole.ROLE_COMPANY)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(CompanyRole);

						break;
					default:
						Role EtudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(EtudiantRole);
					}
				});
			}
				
			/**
			ConfirmationToken confirmationToken = new ConfirmationToken(user);

            confirmationTokenRepository.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("chand312902@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
            +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);
			**/
			
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity
				.ok()
				.body(new MessageResponse("Welcome : New Company inscrit !")) ;  
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	
}
