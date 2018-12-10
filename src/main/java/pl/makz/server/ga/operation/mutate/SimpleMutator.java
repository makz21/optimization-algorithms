package pl.makz.server.ga.operation.mutate;

import pl.makz.server.ga.model.Genotype;

import java.util.Random;

import static pl.makz.server.util.RandomGenerator.randomDouble;

public class SimpleMutator implements Mutator {

	//Increace
	@Override
	public void mutateSingleGeno(Genotype genotype, int geno) {
		Random random = new Random();
		int genoIndex = random.nextInt(genotype.getGenotype().length);
		double genoValue = genotype.getGenotype()[genoIndex];
		double newGenoValue = randomDouble(-1, 1) + genoValue;
		genotype.getGenotype()[genoIndex] = newGenoValue;
	}
}
