package pl.makz.server.ga.operation.select;

import pl.makz.server.ga.model.Genotype;
import pl.makz.server.ga.model.Population;

import java.util.Random;

public class TournamentSelector implements Selector {

    private int tour;

    public TournamentSelector(int tour){
        this.tour=tour;
    }

    @Override
    public Genotype select(Population population) {

        int popSize = population.getGenotypes().size();

        Random random = new Random();

        Genotype bestGenotype = population.getGenotypes().get(random.nextInt(popSize));

        for(int i=1; i<tour; i++){
            Genotype genotype = population.getGenotypes().get(random.nextInt(popSize));

            if(bestGenotype.getFittnes()>genotype.getFittnes()){
                bestGenotype = genotype;
            }
        }
        return bestGenotype;
    }
}
