/*
 * A client to invoke methods of a Calculator Service
 */
package soap.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.ws.WebServiceException;

import soap.generated.Calculator;
import soap.generated.CalculatorImplService;

public class CalculatorClient {

	public static void main(String[] args) {
		Properties props =  new Properties();
		//Read the service URL from the configuration file and start the client's operations
		try(FileInputStream config = new FileInputStream(("connection.properties"))){
			props.load(config);
			URL calcUrl = new URL(props.getProperty("service_url"));
			CalculatorImplService calcImpl = new CalculatorImplService(calcUrl);
			Calculator calcService = calcImpl.getCalculatorImplPort();
			clientOperations(calcService);
		} catch (IOException e) {
			System.out.println("Service URL not valid");
		} catch(WebServiceException d) {
			System.out.println("Unable to access service's WSDL at the given address");
		}
	}
	
	/* This executes the client's operations
	* Uses a loop to ask the client for actions and invokes the calculator service's
	* functions depending on the action chosen.
	*/
	public static void clientOperations(Calculator calcService) {
		Scanner input = new Scanner(System.in);
		//Action loop
		StringBuilder message = new StringBuilder("Select one of the following options : \n");
		message.append("a) Type 1 to add 2 numbers\n");
		message.append("b) Type 2 to subtract 2 numbers\n");
		message.append("c) Type 3 to multiply 2 numbers\n");
		message.append("d) Type 4 to divide 2 numbers\n");
		message.append("e) Type 0 to exit\n");
		int action = 1;
		float number1;
		float number2;
		try {
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
		}catch(InputMismatchException e) {
			System.out.println("Invalid input. Make sure you type numbers and not letters");
		}finally {
			input.close();
			System.out.println("Client terminated.");
		}
	}
}
