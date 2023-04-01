package com.teodora.springcloud.controllers;

import java.io.UnsupportedEncodingException;
import java.net.Authenticator.RequestorType;
import java.text.ParseException;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.tomcat.util.descriptor.web.ContextResourceLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.boot.context.properties.bind.Bindable.BindRestriction;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.teodora.springcloud.annotations.ValidPassword;
import com.teodora.springcloud.exception.ErrorResponse;
import com.teodora.springcloud.exception.UserAlreadyExistsException;

import com.teodora.springcloud.model.User;
import com.teodora.springcloud.model.VerificationToken;
import com.teodora.springcloud.repos.UserRepo;
import com.teodora.springcloud.repos.VerificationTokenRepository;
import com.teodora.springcloud.service.EmailService;
import com.teodora.springcloud.service.UserService;
import com.teodora.springcloud.utils.GenericResponse;
import com.teodora.springcloud.utils.OnRegistrationCompleteEvent;
import com.teodora.springcloud.utils.UserUtil;
import com.teodora.springcloud.web.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RestController
//@RequestMapping("/userapi")
@Controller
public class UserRestController {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ApplicationEventPublisher eventPublisher; 
	
	@Autowired
	private JavaMailSender mailSender;
	
	//@Autowired
    //private MessageSource messages;
	
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public User create(@RequestBody User user) {
		return repo.save(user);
	}
	
	@RequestMapping(value="/users/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		return repo.findById(id).orElse(null);	
	}
	@RequestMapping(value="/updateusers", method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		return repo.save(user);
	}
	
	@RequestMapping(value="/deleteuser/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable("id") Long Id) {
		repo.deleteById(Id);
		
	}
	@RequestMapping(value="/users/list", method = RequestMethod.GET)
	public List<User> getUsers() {
		return repo.findAll();
		
	}
	
	//@RequestMapping(value="user",method = RequestMethod.GET)
	@GetMapping("/user/{id}")
	public String detailsUser(@PathVariable("id")Long id, Model model) {
		//User user = getUser(name);
		User user = repo.getReferenceById(id);
		//User user=categoryService.getCategoryName(name);
		model.addAttribute("firstName", user.getFirstName());
		model.addAttribute("lastName", user.getLastName());
		model.addAttribute("birthDate",user.getBirthDate());
		model.addAttribute("email",user.getEmail());
		return "user";
		
	}
	@GetMapping("user-list")
	public String users(Model model) {
		List<User> users = new ArrayList<>();
		//users=getUsers();
		users=repo.findAll();
		//users=userService.getUsers();
		//List<String> names=categories.stream().map(Category::getName).collect(Collectors.toList());
		model.addAttribute("users",users);
		//return "user-list";
		return "Users";
	}
	@GetMapping("/edit-user/{id}")
	public String showUpdateForm(@PathVariable("id")Long id,Model model) {
		//User updateUser = getUser(id);
		User updateUser = repo.getReferenceById(id);
		//User updateUser=(User)userService.getUser(id);
		model.addAttribute("user",updateUser);
		return "update-user";
	}
	@PostMapping("/update-user/{id}")
	public String updateUser(@PathVariable("id") Long id,@Validated User user,BindingResult result, Model model) {
		if(result.hasErrors()) {
			user.setId(id);
			return "update-user";
		}
		//updateUser(user);
		repo.save(user);
		//userService.updateUser(id,model.getAttribute("name").toString(), new BigDecimal(model.getAttribute("beginRange").toString()), new BigDecimal(model.getAttribute("endRange").toString()));
		return "redirect:/user-list";
	}
	@GetMapping("/delete-user/{id}")
	public String deleteUser(@PathVariable("id") Long id, Model model) {
		repo.deleteById(id);
		//User user=userService.getUser(id);
		//userService.deleteUser(user);
		return "redirect:/user-list";
	}
	
	/*@GetMapping("/createUserView")
	public String getUserRegisterPage(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		model.addAttribute("existedUsername",null);
		//return "user-register";
		return "register-user";
	}*/
	
	@GetMapping("/createUserView")
	public String getUserRegisterPage(Model model) {
		User user=new User();
		model.addAttribute("user",user);
		model.addAttribute("existedUsername",null);
		return "register-user";
		//return "user-register";
	}
	
	/*@RequestMapping(value="/register", method=RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") @Validated @Valid User user, RedirectAttributes redirectAttributes,Model model,BindingResult bindingResult) {
		User existingUser = repo.findByEmail(user.getEmail());
		if(existingUser != null) {
			model.addAttribute("existedUsername",existingUser);
			bindingResult.rejectValue("email", null,"There is already an account registered with the same email");
		}
		if(bindingResult.hasErrors()) {
			model.addAttribute("user",user);
			//return "user-register";
			return "register-user";
		}
		userService.registerUser(user);
		redirectAttributes.addAttribute("id", user.getId());
		VerificationToken verificationToken = new VerificationToken(user);
		verificationTokenRepository.save(verificationToken);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration");
		mailMessage.setFrom("nikolovskat95@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "+"http://localhost:8080/confirm-account?token="+verificationToken.getToken());
		emailService.sendEmail(mailMessage);
		//model.addAttribute("email",user.getEmail());
		return "successfulRegistration";
		/*else {
			repo.save(user);
			VerificationToken verificationToken = new VerificationToken(user);
			verificationTokenRepository.save(verificationToken);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("Complete Registration");
			mailMessage.setFrom("nikolovskat95@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "+"http://localhost:8080/confirm-account?token="+verificationToken.getToken());
			emailService.sendEmail(mailMessage);
			modelAndView.addObject("email",user.getEmail());
			modelAndView.setViewName("successfulRegistration");
			
		}
		return modelAndView;*/
	/*}*/
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public String confirmUserAccount(@RequestParam("token") String confirmationToken, Model model) {
		VerificationToken token=verificationTokenRepository.findByToken(confirmationToken);
		if(token!=null) {
			User user = repo.findByEmail(token.getUser().getEmail());
			user.setEnabled(true);
			userService.saveUserWIthDefaultRole(user);
			//repo.save(user);
			return("accountVerified");
		}
		/*else {
			//modelAndView.addObject("message","The link is invalid or broken!");
			//modelAndView.setViewName("error");
			
		}*/
		return "verificationFailed";
	}
	
	
	@PostMapping("/createuser")
	public String createUser(@ModelAttribute("user") @Validated @Valid User user, RedirectAttributes redirectAttributes,Model model,BindingResult bindingResult) throws UnsupportedEncodingException, MessagingException {
		User existedUsername = repo.findByEmail(user.getEmail());
		if(existedUsername!=null) {
			model.addAttribute("existedUsername",existedUsername);
			bindingResult.rejectValue("email", null,"There is already an account registered with the same email");
			}
		if(bindingResult.hasErrors()) {
			model.addAttribute("user",user);
			//return "user-register";
			return "register-user";
		}
		/*userService.registerUser(user);*/
		/*redirectAttributes.addAttribute("id", user.getId());*/
		userService.registerUser(user);
		redirectAttributes.addAttribute("id", user.getId());
		VerificationToken verificationToken = new VerificationToken(user);
		verificationTokenRepository.save(verificationToken);
		/*SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setSubject("Complete Registration");
		mailMessage.setFrom("nikolovskat95@gmail.com");
		mailMessage.setText("To confirm your account, please click here : "+"http://localhost:8080/confirm-account?token="+verificationToken.getToken());
		emailService.sendEmail(mailMessage);*/
		String toAddress = user.getEmail();
	    String fromAddress = "nikolovskat95@gmail.com";
	    String senderName = "Health Condition Forecast";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]]"+",<br>"
	            + "Please click the link below to verify your registration:<br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Health Condition Forecast";
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setFrom(fromAddress,senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	    content=content.replace("[[name]]", user.getFirstName());
	    //String verifyRL = "localhost:8080/confirm-account?token="+verificationToken;
	    //Link link = Link.of("localhost:8080/confirm-account?token="+verificationToken);
	    String verifyRL = "http://localhost:8080/confirm-account?token="+verificationToken.getToken();
	    content=content.replace("[[URL]]", verifyRL);
	    helper.setText(content,true);
	    mailSender.send(message);
		//model.addAttribute("email",user.getEmail());
		//return "successfulRegistration";
		//return "redirect:/user/{id}";
	    return "successfulRegistration";
	}
	/*@GetMapping("/login")
	public String login() {
		return "login";
	}*/
	/* @RequestMapping("/login")
	    public String login() {
	        return "login";
	    }
	 private Optional<String> getDomain() {
	        Authentication auth = SecurityContextHolder.getContext()
	            .getAuthentication(); 
	        String domain = null;
	        if (auth != null && !auth.getClass().equals(AnonymousAuthenticationToken.class)) {
	            User user = (User) auth.getPrincipal();
	            domain = user.getEmail();
	        }
	        return Optional.ofNullable(domain);
	    }*/
	 
	/* @PostMapping("/user/registration")
	 public GenericResponse registerUserAccount(
	   @ModelAttribute("user") @Valid User user, 
	   HttpServletRequest request, Errors errors) { 
	     
	     //try {
	         User registered = service.registerNewUserAccount(user);
	         String appUrl = request.getContextPath();
	         eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, 
	           request.getLocale(), appUrl));
	         return new GenericResponse("success");
	    /* } catch (UserAlreadyExistsException uaeEx) {
	         ModelAndView mav = new ModelAndView("register-user", "user", user);
	         mav.addObject("message", "An account for that username/email already exists.");
	         return mav;
	     } catch (RuntimeException ex) {
	         return new ModelAndView("emailError", "user", user);
	     }

	     return new ModelAndView("successRegister", "user", user);
	 }*/
	  /*@GetMapping("/registrationConfirm")
	    public String confirmRegistration(final HttpServletRequest request, final Model model, @RequestParam("token") final String token) {
	        final Locale locale = request.getLocale();

	        final VerificationToken verificationToken = userService.getVerificationToken(token);
	        if (verificationToken == null) {
	            final String message = messages.getMessage("auth.message.invalidToken", null, locale);
	            model.addAttribute("message", message);
	            return "redirect:/badUser.html?lang=" + locale.getLanguage();
	        }

	        final User user = verificationToken.getUser();
	        final Calendar cal = Calendar.getInstance();
	        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	            model.addAttribute("message", messages.getMessage("auth.message.expired", null, locale));
	            model.addAttribute("expired", true);
	            model.addAttribute("token", token);
	            return "redirect:/badUser.html?lang=" + locale.getLanguage();
	        }

	        user.setEnabled(true);
	        userService.saveRegisteredUser(user);
	        model.addAttribute("message", messages.getMessage("message.accountVerified", null, locale));
	        return "redirect:/login.html?lang=" + locale.getLanguage();
	    }
	 
	 
	 @GetMapping("/regitrationConfirm")
	 public String confirmRegistration
	   (WebRequest request, Model model, @RequestParam("token") String token) {
	  
	     Locale locale = request.getLocale();
	     
	     VerificationToken verificationToken = service.getVerificationToken(token);
	     if (verificationToken == null) {
	         String message = messages.getMessage("auth.message.invalidToken", null, locale);
	         model.addAttribute("message", message);
	         return "redirect:/badUser.html?lang=" + locale.getLanguage();
	     }
	     
	     User user = verificationToken.getUser();
	     Calendar cal = Calendar.getInstance();
	     if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
	         String messageValue = messages.getMessage("auth.message.expired", null, locale);
	         model.addAttribute("message", messageValue);
	         return "redirect:/badUser.html?lang=" + locale.getLanguage();
	     } 
	     
	     user.setEnabled(true); 
	     service.saveRegisteredUser(user); 
	     return "redirect:/login.html?lang=" + request.getLocale().getLanguage(); 
	 }*/
	/* @GetMapping("/registrationConfirm")
	    public ModelAndView confirmRegistration(final HttpServletRequest request, final ModelMap model, @RequestParam("token") final String token) throws UnsupportedEncodingException {
	        Locale locale = request.getLocale();
	        model.addAttribute("lang", locale.getLanguage());
	        final String result = userService.validateVerificationToken(token);
	        if (result.equals("valid")) {
	            final User user = userService.getUser(token);
	            // if (user.isUsing2FA()) {
	            // model.addAttribute("qr", userService.generateQRUrl(user));
	            // return "redirect:/qrcode.html?lang=" + locale.getLanguage();
	            // }
	            authWithoutPassword(user);
	            model.addAttribute("messageKey", "message.accountVerified");
	            return new ModelAndView("redirect:/console", model);
	        }

	        model.addAttribute("messageKey", "auth.message." + result);
	        model.addAttribute("expired", "expired".equals(result));
	        model.addAttribute("token", token);
	        return new ModelAndView("redirect:/badUser", model);
	    }*/

	/*public void authWithoutPassword(User user) {

	        List<Privilege> privileges = user.getRoles()
	                .stream()
	                .map(Role::getPrivileges)
	                .flatMap(Collection::stream)
	                .distinct()
	                .collect(Collectors.toList());

	        List<GrantedAuthority> authorities = privileges.stream()
	                .map(p -> new SimpleGrantedAuthority(p.getName()))
	                .collect(Collectors.toList());

	        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    }*/
	 
	 
	 
	 
	 
	
	/*@GetMapping("/login-user")
	public String userLogin() {
		return "login";
	}*/
	
	@ExceptionHandler(value=UserAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErrorResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
		return new ErrorResponse(HttpStatus.CONFLICT.value(),ex.getMessage());
	}
	
	
}
