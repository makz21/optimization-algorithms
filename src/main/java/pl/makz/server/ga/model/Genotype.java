package pl.makz.server.ga.model;

import pl.makz.server.api.FunctionOptimizationTask;
import pl.makz.server.util.RandomGenerator;

import java.util.List;

import static java.util.Arrays.asList;

public class Genotype {

	private double genotype[];
	private double fittnes;

	public Genotype(double[] genotype){
		this.genotype = genotype;
	}

	public Genotype(double[] genotype, double fittness){
		this.genotype = genotype;
		this.fittnes = fittness;
	}

	public Genotype(Genotype genotype){
		this(genotype.genotype.clone(), genotype.getFittnes());
	}

	public double[] getGenotype() {
		return genotype;
	}

	public void setGenotype(double[] genotype) {
		this.genotype = genotype;
	}

	public double getFittnes() {
		return fittnes;
	}

	public void setFittnes(int fittnes) {
		this.fittnes = fittnes;
	}

	public int size() {
		return genotype.length;
	}

	public void evaluate(FunctionOptimizationTask functionOptimizationTask) {
		this.fittnes=functionOptimizationTask.valueFor(genotype[0],genotype[1]);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Genotype: [");
		for(double i: genotype){
			stringBuilder.append(i+",");
		}
		stringBuilder.deleteCharAt(stringBuilder.length()-1);
		stringBuilder.append(" | ");
		stringBuilder.append(fittnes);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	public static class GenotypeBuilder{
		private int genotypeSize;

		public GenotypeBuilder(){

		}
		public GenotypeBuilder(int genotypeSize){
			this.genotypeSize=genotypeSize;
		}

		public GenotypeBuilder genotypeSize(int genotypeSize){
			this.genotypeSize=genotypeSize;
			return this;
		}

		public Genotype buildRandomGenotype (int maxValue){

			List<Double> genoms = asList(RandomGenerator.randomDouble(0, maxValue), RandomGenerator.randomDouble(0, maxValue));
			double[] genotype = genoms.stream().mapToDouble(Double::doubleValue).toArray();
			return new Genotype(genotype);
		}

	}
}