/*
 * A client to invoke methods of a Calculator Service
 */
package soap.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import soap.generated.Calculator;
import soap.generated.CalculatorImplService;

public class CalculatorClientTD {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		try {
			//Prepare for service invocation
			URL calcUrl = new URL("http://localhost:8080/CalculatorJAX_TD/calculator?wsdl");
			CalculatorImplService calcImpl = new CalculatorImplService(calcUrl);
			Calculator calcService = calcImpl.getCalculatorImplPort();
			//Action loop
			StringBuilder message = new StringBuilder("Select one of the following options : \n");
			message.append("a) Type 1 to add 2 numbers\n");
			message.append("b) Type 2 to subtract 2 numbers\n");
			message.append("c) Type 3 to multiply 2 numbers\n");
			message.append("d) Type 4 to divide 2 numbers\n");
			message.append("e) Type 0 to exit\n");
			int action;
			float number1;
			float number2;
			do {
				System.out.println(message);
				action = input.nextInt();
				if(action > 0 && action <5) {
					System.out.println("Give the first number : ");
					number1 = input.nextFloat();
					System.out.println("Give the second number : ");
					number2 = input.nextFloat();
					switch(action) {
					case 1 :
						System.out.println(number1 + " + "+number2+" = "+calcService.add(number1, number2)+"\n");
						break;
					case 2 :
						System.out.println(number1 + " - "+number2+" = "+calcService.sub(number1, number2)+"\n");
						break;
					case 3 :
						System.out.println(number1 + " * "+number2+" = "+calcService.mul(number1, number2)+"\n");
						break;
					case 4 :
						System.out.println(number1 + " / "+number2+" = "+calcService.div(number1, number2)+"\n");
						break;
					default:
						break;
					}
					
				}
			}while(action != 0);
			System.out.println("Goodbye!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}finally {
			input.close();
		}
	}
}
