package org.hibernate.validator.bugs;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import javafx.beans.property.SimpleObjectProperty;
import org.hibernate.validator.testutil.TestForIssue;
import org.junit.BeforeClass;
import org.junit.Test;

public class JavaFxObjectPropertyValidateValueTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	@TestForIssue(jiraKey = "HV-1471")
	public void testJavaFxObjectPropertyValidateValue_UnwrappedParameter() {
		Set<ConstraintViolation<JavaFxBean>> constraintViolations =
				validator.validateValue( JavaFxBean.class, "id", BigInteger.ZERO );

		assertEquals( 1, constraintViolations.size() );
		assertEquals(
				"must be greater than or equal to 1",
				constraintViolations.iterator().next().getMessage() );
	}

	@Test
	@TestForIssue(jiraKey = "HV-1471")
	public void testJavaFxObjectPropertyValidateValue_WrappedParameter() {
		Set<ConstraintViolation<JavaFxBean>> constraintViolations =
				validator.validateValue( JavaFxBean.class, "id", new SimpleObjectProperty<>( BigInteger.ZERO ) );

		assertEquals( 1, constraintViolations.size() );
		assertEquals(
				"must be greater than or equal to 1",
				constraintViolations.iterator().next().getMessage() );
	}

}
