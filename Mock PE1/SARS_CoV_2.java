public class SARS_CoV_2 extends Virus {
        SARS_CoV_2(double probabilityOfMutating) {
                super("SARS-CoV-2", probabilityOfMutating);
        }

        @Override
        public Virus spread(double random) {
                if (random <= this.getProbabilityOfMutating())
                        return new BetaCoronavirus();
                else
                        return new SARS_CoV_2(this.getProbabilityOfMutating()
                                        * SimulationParameters.VIRUS_MUTATION_PROBABILITY_REDUCTION);

        }
}
