package pl.makz.server.ga;

import pl.makz.server.AlgorithmStepListener;
import pl.makz.server.api.FunctionOptimizationTask;
import pl.makz.server.ga.model.Genotype;
import pl.makz.server.ga.model.Population;
import pl.makz.server.ga.operation.crossover.Crossover;
import pl.makz.server.ga.operation.mutate.Mutator;
import pl.makz.server.ga.operation.select.Selector;

import java.util.ArrayList;

public class GeneticAlgorithm {


	private Mutator mutator;
	private Selector selector;
	private Crossover crossover;
	private double crossoverProbability;
	private double mutateProbability;
	private int populationSize;


	public GeneticAlgorithm(Mutator mutator, Selector selector, Crossover crossover, double crossoverProbability, double mutateProbability, int populationSize) {
		this.mutator = mutator;
		this.selector = selector;
		this.crossover = crossover;
		this.crossoverProbability = crossoverProbability;
		this.mutateProbability = mutateProbability;
		this.populationSize = populationSize;
	}


	public void execute(FunctionOptimizationTask functionOptimizationTask, int generations, AlgorithmStepListener listener) {
		listener.onStepFinished("Rozpocząto działanie algorytmu genetycznego...");
		Population population = new Population.PopulationBuilder().genotypeSize(2).populationSize(this.populationSize).buildInitPopulation(100);
		listener.onStepFinished("Stworzono pierwszą populacje...");
		listener.onStepFinished("Ewaluowanie pierwszej populacji....");
		population.evaluate(functionOptimizationTask);
		Genotype bestGenotype = population.getBestGenotype();
		listener.onStepFinished("Najlepszy osobnik w pierwszej populacji...");


		for (int i = 0; i < generations; i++) {
			listener.onStepFinished("Ewoluowanie populacji "+i+" ...");
			population = evolve(population, functionOptimizationTask);
			listener.onStepFinished("Ewaluowanie populacji "+i+" ...");
			population.evaluate(functionOptimizationTask);
			listener.onStepFinished("Najlepszy osobnik w  populacji "+i+" ...");
			if (population.getBestGenotype().getFittnes() <= bestGenotype.getFittnes()) {
				bestGenotype = new Genotype(population.getBestGenotype().getGenotype(), population.getBestGenotype().getFittnes());
			}
		}

	}

	private Population evolve(Population population, FunctionOptimizationTask functionOptimizationTask) {
		ArrayList<Genotype> newGenotypes = new ArrayList<>();

		newGenotypes.add(new Genotype(population.getBestGenotype()));
		for (int i = 0; i < population.getGenotypes().size()-1; i++) {

			Genotype genotype = new Genotype(selector.select(population));

			if (Math.random() < crossoverProbability) {
				Genotype crossoverSecondGenotype = new Genotype(selector.select(population));
				crossover.crossover(genotype, crossoverSecondGenotype);
			}

			for (int j = 0; j < genotype.getGenotype().length; j++) {
				if (Math.random() < mutateProbability) {
					mutator.mutateSingleGeno(genotype, j);
				}
			}

			genotype.evaluate(functionOptimizationTask);
			newGenotypes.add(genotype);
		}

		return new Population(newGenotypes);
	}

}
