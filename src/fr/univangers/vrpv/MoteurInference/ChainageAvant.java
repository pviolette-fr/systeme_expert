package fr.univangers.vrpv.MoteurInference;

public abstract class ChainageAvant extends Chainage {

    public ChainageAvant(Paquet baseDeRegle, BaseDeFait baseDeFait) {
        super(baseDeRegle, baseDeFait);
        // TODO Auto-generated constructor stub
    }

    @Override
    public abstract boolean rechercheBut(Fait but);

    /**
     * @param bdf La base de fait dans laquelle rechercher
     * @return un paquet contenant toutes les règles applicables
     */
    protected Paquet getReglesApplicables(BaseDeFait bdf) {

        Paquet p = new Paquet();

        for (Regle r : m_baseDeRegles.getRegles()) {

            if (r.estApplicable(bdf)) {
                p.ajouterRegle(r);
            }

        }

        return p;

    }

}
