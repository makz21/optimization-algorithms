package pl.makz.server;

import pl.makz.server.api.FunctionOptimizationTask;

public class Main {

	public static void main(String[] args) {

		Server server = new Server();
		server.start();
		server.optimizationTaskRequest(new RosenbrockFunction());
		server.close();
	}

}
class RosenbrockFunction implements FunctionOptimizationTask {

	@Override
	public double valueFor(double x, double y) {
		return (1 - x) * (1 - x) + 100 * ((y - (x * x)) * (y - (x * x)));
	}
}