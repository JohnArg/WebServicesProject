/*
 * This interface describes the functionality of 
 * a calculator service
 */
package soap.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style=Style.RPC)
public interface Calculator {
	//Add 2 numbers
	@WebMethod
	public float add(float x, float y);
	//Subtract 2 numbers
	@WebMethod
	public float sub(float x, float y);
	//Multiply 2 numbers
	@WebMethod
	public float mul(float x, float y);
	//Divide 2 numbers
	@WebMethod
	public float div(float x, float y);
}
