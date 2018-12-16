package logic;

import org.apache.commons.lang3.StringUtils;

public class PaymentUtils {

	public static boolean isDataCorrect(int expectedLengthCardNumber, 
			String cardNumber, int expectedLengthCV2, String CV2Number, String expirationDate) {
		
		return isNumberCorrect(expectedLengthCardNumber, cardNumber) 
				&& isNumberCorrect(expectedLengthCV2, CV2Number) 
				&& DateUtils.isExpirationDateCorrect(expirationDate);
	}
	private static boolean isNumberCorrect(int expectedLength, String number) {
		
		return StringUtils.isNumeric(number) && number.length() == expectedLength;
	}
	
}
