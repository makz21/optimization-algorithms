package pl.makz.server.ga.operation.select;

import pl.makz.server.ga.model.Genotype;
import pl.makz.server.ga.model.Population;

public interface Selector {

	Genotype select(Population population);

}
