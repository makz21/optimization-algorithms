package pl.makz.server;

import pl.makz.server.api.FunctionOptimizationTask;
import pl.makz.server.ga.GeneticAlgorithm;
import pl.makz.server.ga.operation.crossover.SimpleSwapCrossover;
import pl.makz.server.ga.operation.mutate.SimpleMutator;
import pl.makz.server.ga.operation.select.TournamentSelector;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements AlgorithmStepListener{
	PrintWriter writer;

	public void start() {
		int port = 5000;
		try (ServerSocket serverSocket = new ServerSocket(port)) {
			System.out.println("Server is listening on port " + port);
				Socket socket = serverSocket.accept();
				System.out.println("New client connected");
				OutputStream output = socket.getOutputStream();
				writer = new PrintWriter(output, true);
		} catch (IOException ex) {
			System.out.println("Server exception: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void close() {}

	public void optimizationTaskRequest(FunctionOptimizationTask functionOptimizationTask) {
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(new SimpleMutator(), new TournamentSelector(5), new SimpleSwapCrossover(),
				0.7, 0.6, 100);
		geneticAlgorithm.execute(functionOptimizationTask, 10000, this);
	}

	public void sendOptimizationTaskProcessingStatusToClient(String status) {
		writer.println(status);
	}

	@Override
	public void onStepFinished(String algorithmStepDescription) {
		this.sendOptimizationTaskProcessingStatusToClient(algorithmStepDescription);
	}
}




