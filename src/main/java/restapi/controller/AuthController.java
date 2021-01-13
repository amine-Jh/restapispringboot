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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@PreAuthorize("permitAll()")
	@PostMapping("/signin")
	
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
		
		if (!userRepository.existsByEmail(loginRequest.getEmail())) {
			return ResponseEntity
					.ok()
					.body(new MessageResponse(" cet utilisateur n'existe pas "));
		}

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		
		
			return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getName()));
		
		
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
							 signUpRequest.getEmail(),signUpRequest.getUsername()
							  );

		userRepository.save(user);
		return ResponseEntity
				.badRequest()
				.body(new MessageResponse("Error: New User registred!")) ;  
		
		
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

		// Create new user's account
		 Etudiant user= new Etudiant( signUpRequest.getName() , encoder.encode(signUpRequest.getPassword()) ,signUpRequest.getEmail(),
				 signUpRequest.getFilliere() ,signUpRequest.getUsername(), signUpRequest.getAnnee(),signUpRequest.getTelephone()
				 );

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
		 
		 
		userRepository.save(user);
		return ResponseEntity
				.ok()
				.body(new MessageResponse("Welcome : New Company inscrit !")) ;  
		
		
}
	
	
	
	
	
	
	
	
	
	
	
	
}
