package com.ikshvaku.ProjectHive.Controller;


import com.ikshvaku.ProjectHive.Config.JwtProvider;
import com.ikshvaku.ProjectHive.Response.AuthResponse;
import com.ikshvaku.ProjectHive.Services.CustomUserDetailsImpl;
import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.repository.UserRepository;
import com.ikshvaku.ProjectHive.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsImpl customUserDetails;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<User>createUserHandler(@RequestBody User user) throws Exception{
        User isUserExist = userRepository.findByEmail(user.getEmail());
        if (isUserExist!= null){
            throw new Exception("User Already Exist");
        }
        User createdUser = new User();
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setEmail(user.getEmail());
        createdUser.setFullname(user.getFullname());

        User savedUser = userRepository.save(createdUser);

        //Code to share jwt token in database
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res =  new AuthResponse();
        res.setMessage("SignUp Successfully");
        res.setJwt(jwt);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> signing(@RequestBody LoginRequest loginRequest){
        String username =loginRequest.getEmail();
        String password =loginRequest.getPassword();
        Authentication authentication = authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = JwtProvider.generateToken(authentication);

        AuthResponse res =  new AuthResponse();
        res.setMessage("Signing Successfully");
        res.setJwt(jwt);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails = customUserDetails.loadUserByUsername(username);
        if(username==null){
            throw new BadCredentialsException("Invalid Username");
        }
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

}
