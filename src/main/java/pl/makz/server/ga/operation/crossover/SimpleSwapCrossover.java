package pl.makz.server.ga.operation.crossover;

import pl.makz.server.ga.model.Genotype;

public class SimpleSwapCrossover implements Crossover {

	@Override
	public void crossover(Genotype genotype, Genotype genotype2) {
        double avgX = (genotype.getGenotype()[0]+genotype2.getGenotype()[0])/2;
		double avgY = (genotype.getGenotype()[1]+genotype2.getGenotype()[1])/2;

		genotype.setGenotype(new double[]{avgX, avgY});
		genotype2.setGenotype(new double[]{avgX, avgY});
	}
}
