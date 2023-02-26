package com.teodora.springcloud.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.AlphabeticalSequenceRule;
import org.passay.CharacterRule;
import org.passay.DigitCharacterRule;
//import org.passay.EnglishCharacterData;
//import org.passay.EnglishSequenceData;
//import org.passay.IllegalSequenceRule;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.NumericalSequenceRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.QwertySequenceRule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;



import com.teodora.springcloud.annotations.ValidPassword;

import lombok.SneakyThrows;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
	
	
	  @Override
	  public void initialize(final ValidPassword arg0) {
	  }

	//@SneakyThrows
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		 /*Properties props = new Properties();
		 InputStream inputStream = getClass()
				                 .getClassLoader().getResourceAsStream("passay.properties");
		 try {
			props.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 //MessageResolver resolver = new PropertiesMessageResolver(props);
		 /*PasswordValidator  validator = new PasswordValidator(resolver,Arrays.asList(
				 new LengthRule(8,16),
				 new CharacterRule(EnglishCharacterData.UpperCase, 1),
				 new CharacterRule(EnglishCharacterData.LowerCase, 1),
				 new CharacterRule(EnglishCharacterData.Digit, 1),
				 new CharacterRule(EnglishCharacterData.Special, 1),
				 new WhitespaceRule(),
				 new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
				 new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)
				 ));*/
		 PasswordValidator validator = new PasswordValidator(Arrays.asList(
		           new LengthRule(8, 30), 
		           new UppercaseCharacterRule(1), 
		           new DigitCharacterRule(1), 
		           new SpecialCharacterRule(1), 
		           new NumericalSequenceRule(3,false), 
		           new AlphabeticalSequenceRule(3,false), 
		           new QwertySequenceRule(3,false),
		           new WhitespaceRule()));
		

		 RuleResult result = validator.validate(new PasswordData(password));
		 if(result.isValid()) {
			 return true;
		 }
		 List<String> messages = validator.getMessages(result);
		 String messageTemplate = String.join(",", messages);
		 context.buildConstraintViolationWithTemplate(messageTemplate)
		 .addConstraintViolation()
		 .disableDefaultConstraintViolation();
		 
		
		return false;
	}

}
