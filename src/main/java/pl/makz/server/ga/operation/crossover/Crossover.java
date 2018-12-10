package pl.makz.server.ga.operation.crossover;

import pl.makz.server.ga.model.Genotype;

public interface Crossover {
	void crossover(Genotype genotype, Genotype genotype2);
}
