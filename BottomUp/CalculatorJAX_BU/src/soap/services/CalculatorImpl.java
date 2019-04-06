/*
 * This class defines the functionality of the Calculator Service
 */
package soap.services;

import javax.jws.WebService;

@WebService(endpointInterface="soap.services.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public float add(float x, float y) {
		return x + y;
	}

	@Override
	public float sub(float x, float y) {
		return x - y;
	}

	@Override
	public float mul(float x, float y) {
		return x * y;
	}

	@Override
	public float div(float x, float y) {
		return x / y;
	}

}
