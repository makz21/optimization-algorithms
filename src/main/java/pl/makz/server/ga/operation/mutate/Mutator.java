package pl.makz.server.ga.operation.mutate;

import pl.makz.server.ga.model.Genotype;

public interface Mutator {

	void mutateSingleGeno(Genotype genotype, int geno);

}
