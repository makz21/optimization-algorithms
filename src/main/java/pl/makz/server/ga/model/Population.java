package pl.makz.server.ga.model;

import pl.makz.server.api.FunctionOptimizationTask;

import java.util.ArrayList;
import java.util.List;

public class Population {

	protected List<Genotype> genotypes;
	protected double bestFittnes;
	protected double avgFittnes;
	protected double worstFittnes;
	protected Genotype bestGenotype;


	public Population(List<Genotype> genotypes) {
		this.genotypes = genotypes;
	}

	public void evaluate(FunctionOptimizationTask functionOptimizationTask) {
		genotypes.forEach(genotype -> genotype.evaluate(functionOptimizationTask));
		calculateFittneses();
	}

	private void calculateFittneses() {
		bestFittnes = genotypes.get(0).getFittnes();
		bestGenotype = genotypes.get(0);
		worstFittnes = genotypes.get(0).getFittnes();

		double sum = 0;

		for (Genotype g : genotypes) {
			sum += g.getFittnes();
			if (g.getFittnes() < bestFittnes) {
				bestFittnes = g.getFittnes();
				bestGenotype = new Genotype(g);
				if (bestFittnes != bestGenotype.getFittnes()) {
					Genotype gs = new Genotype(bestGenotype);
					bestGenotype.getFittnes();
				}
			}
			if (g.getFittnes() > worstFittnes) {
				worstFittnes = g.getFittnes();
			}
		}
		avgFittnes = sum / genotypes.size();
	}

	public void setGenotypes(List<Genotype> genotypes) {
		this.genotypes = genotypes;
	}

	public void setBestFittnes(int bestFittnes) {
		this.bestFittnes = bestFittnes;
	}

	public void setAvgFittnes(int avgFittnes) {
		this.avgFittnes = avgFittnes;
	}

	public void setWorstFittnes(int worstFittnes) {
		this.worstFittnes = worstFittnes;
	}

	public List<Genotype> getGenotypes() {
		return genotypes;
	}

	public double getBestFittnes() {
		return bestFittnes;
	}

	public double getAvgFittnes() {
		return avgFittnes;
	}

	public double getWorstFittnes() {
		return worstFittnes;
	}

	public Genotype getBestGenotype() {
		return bestGenotype;
	}

	public String toCsv() {
		return bestFittnes + "," + avgFittnes + "," + worstFittnes;

	}

	public void print() {
		for (Genotype g : genotypes) {
			System.out.println(g.toString());
		}
	}


	public static class PopulationBuilder {

		private int populationSize;
		private int genotypeSize;

		public PopulationBuilder() {
			this.populationSize = 0;
			this.genotypeSize = 0;
		}

		public PopulationBuilder populationSize(int populationSize) {
			this.populationSize = populationSize;
			return this;
		}

		public PopulationBuilder genotypeSize(int genotypeSize) {
			this.genotypeSize = genotypeSize;
			return this;
		}

		public Population buildInitPopulation(int maxValue) {
			List<Genotype> genotypes = new ArrayList<Genotype>();
			Genotype.GenotypeBuilder genotypeBuilder = new Genotype.GenotypeBuilder().genotypeSize(genotypeSize);
			for (int i = 0; i < populationSize; i++) {
				genotypes.add(genotypeBuilder.buildRandomGenotype(maxValue));
			}

			return new Population(genotypes);
		}
	}
}
