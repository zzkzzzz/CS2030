public class AlphaCoronavirus extends Virus {
    AlphaCoronavirus(double probabilityOfMutating) {
        super("Alpha Coronavirus", probabilityOfMutating);

    }

    @Override
    public Virus spread(double random) {
        if (random <= this.getProbabilityOfMutating())
            return new SARS_CoV_2(this.getProbabilityOfMutating());
        else
            return new AlphaCoronavirus(
                    this.getProbabilityOfMutating() * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);
    }
}
