package com.library.library_management.authservice.controller;

import com.library.library_management.adminservice.model.Librarien;
import com.library.library_management.adminservice.repository.LibrarianRepository;
import com.library.library_management.authservice.config.CustomerServiceImplementation;
import com.library.library_management.authservice.config.JwtProvider;
import com.library.library_management.authservice.config.SecurityUtils;
import com.library.library_management.authservice.dtos.AuthResponse;
import com.library.library_management.authservice.dtos.LoginRequest;
import com.library.library_management.authservice.dtos.RegisterDTO;
import com.library.library_management.authservice.model.Account;
import com.library.library_management.authservice.service.AccountService;
import com.library.library_management.memberservice.model.Member;
import com.library.library_management.memberservice.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final LibrarianRepository librarianRepository;
    private final MemberRepository memberRepository;

    private JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder;

    private AccountService accountService;

    private CustomerServiceImplementation customUserDetailsService;

    public AuthController(LibrarianRepository librarianRepository, MemberRepository memberRepository, JwtProvider jwtProvider, PasswordEncoder passwordEncoder, AccountService accountService, CustomerServiceImplementation customUserDetailsService) {
        this.librarianRepository = librarianRepository;
        this.memberRepository = memberRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/register/admin")
    public ResponseEntity<AuthResponse> registerLibrarian(@RequestBody RegisterDTO registerRequest) throws Exception {
        Librarien librarian = new Librarien();
        librarian.setUsername(registerRequest.username());

        // Use the password encoder to hash the password
        String encodedPassword = passwordEncoder.encode(registerRequest.password());
        librarian.setPassword(encodedPassword);

        // Save the librarian to the database
        librarianRepository.save(librarian);

        Authentication authentication = new UsernamePasswordAuthenticationToken(librarian.getUsername(),librarian.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        System.out.println("Token  :  "+ token);
        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);

    }

    @PostMapping("/register/member")
    public ResponseEntity<AuthResponse> registerMember(@RequestBody RegisterDTO registerDTO) throws Exception {
        String role = registerDTO.role();
        Member member = new Member();
        member.setUsername(registerDTO.username());
        String encodedPassword = passwordEncoder.encode(registerDTO.password());
        member.setPassword(encodedPassword);
        // Save the member to the database
        memberRepository.save(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(member.getUsername(),member.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws Exception {
        String  username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);

        Account user = SecurityUtils.getCurrentUser(accountService);
        AuthResponse authResponse = new AuthResponse(token);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        if(userDetails==null) {   throw  new BadCredentialsException("Invalid USerNAme");   }
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            throw  new BadCredentialsException("Invalid Password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null);
    }

//    @GetMapping("/getUser")
//    public ResponseEntity<AuthResponse>  getUserDetails() throws Exception {
//        User user = SecurityUtils.getCurrentUser(userService);
//        AuthResponse authResponse = new AuthResponse();
//        Map<Long, Integer> unreadMessages = user.getUnreadMessages();
//        authResponse.setUnreadMessages(unreadMessages);
//        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
//    }

}
