package soap.services;

import javax.jws.WebService;

@WebService(endpointInterface="soap.services.Calculator")
public class CalculatorImpl implements Calculator {

	@Override
	public float sub(float arg0, float arg1) {
		return arg0 - arg1;
	}

	@Override
	public float div(float arg0, float arg1) {
		return arg0 / arg1;
	}

	@Override
	public float mul(float arg0, float arg1) {
		return arg0 * arg1;
	}

	@Override
	public float add(float arg0, float arg1) {
		return arg0 + arg1;
	}

}
