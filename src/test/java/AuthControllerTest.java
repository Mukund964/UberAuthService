import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.authservice.Services.AuthService;
import org.example.authservice.Services.JwtService;
import org.example.authservice.controllers.authController;
import org.example.authservice.dtos.passengerDto;
import org.example.authservice.dtos.passengerSignInRequestDto;
import org.example.authservice.dtos.passengerSignUpRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @InjectMocks
    private authController authController;
    @Mock
    private AuthService  authService;

    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private Authentication authentication;

    private Long CookieExpiry;



    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(authController, "cookieExpiry", 86400L);
    }

    @Test
    public void testSignupPassenger_success(){
        passengerSignUpRequestDto passengerSignUpRequestDto = new passengerSignUpRequestDto();
        passengerSignUpRequestDto.setEmail("Abc@123");
        passengerSignUpRequestDto.setPassword("12345");
        passengerSignUpRequestDto.setName("Abc");
        passengerSignUpRequestDto.setPhoneNumber("1234567");

        passengerDto passengerDto = new passengerDto();
        passengerDto.setId("1");
        passengerDto.setName("Abc");
        passengerDto.setEmail("Abc@123");
        passengerDto.setPassword("12345");
        passengerDto.setPhoneNumber("1234567");

       Mockito.when(authService.signupPassenger(passengerSignUpRequestDto)).thenReturn(passengerDto);

       ResponseEntity<?> response = authController.signupPassenger(passengerSignUpRequestDto);

       assertEquals(HttpStatus.OK,response.getStatusCode());
       assertEquals(passengerDto,response.getBody());

    }

    @Test
    public void testSignupPassenger_Failure(){

        passengerSignUpRequestDto passengerSignUpRequestDto = new passengerSignUpRequestDto();
        passengerSignUpRequestDto.setEmail("Abc@123");
        passengerSignUpRequestDto.setPassword("12345");
        passengerSignUpRequestDto.setName("Abc");
        passengerSignUpRequestDto.setPhoneNumber("1234567");


        Mockito.when(authService.signupPassenger(passengerSignUpRequestDto)).thenReturn(null);

        ResponseEntity<?> response = authController.signupPassenger(passengerSignUpRequestDto);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

    }


    @Test
    public void testSignInPassenger_success(){
        passengerSignInRequestDto requestDto = new passengerSignInRequestDto();
        requestDto.setEmail("abc@test.com");
        requestDto.setPassword("password");
        String JWT_Token = "abc";
       Mockito.when(authenticationManager.authenticate(Mockito.any())).thenReturn(authentication);

       Mockito.when(authentication.isAuthenticated()).thenReturn(true);

       Mockito.when(jwtService.tokenCreationWithoutMap(Mockito.any())).thenReturn(JWT_Token);

       ResponseEntity<?> response = authController.signInPassenger(requestDto, httpServletResponse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(JWT_Token, response.getBody());

        Mockito.verify(httpServletResponse)
                .setHeader(Mockito.eq(HttpHeaders.SET_COOKIE), Mockito.contains("JWT_TOKEN"));

        Mockito.verify(authenticationManager)
                .authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
    }
}
