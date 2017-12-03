package fr.univangers.vrpv.MoteurInference;

import java.util.Set;


/**
 * @author Valentine Rahier
 */
public abstract class Chainage {

    //Modif Paulin 28.11.2017 : modification valeur afin d'éviter d'avoir les même valeur dans les constantes de classes
    public static final int LE_PLUS_DE_PREMISSES = 3;
    public static final int LES_FAITS_DEDUITS_LES_PLUS_RECENTS = 4;

    protected Paquet m_baseDeRegles;
    protected BaseDeFait m_baseDeFaits;

    protected String m_trace;
    protected String m_traceAbregee;

    public Chainage(Paquet baseDeRegle, BaseDeFait baseDeFait) {
        m_baseDeRegles = new Paquet(baseDeRegle);
        m_baseDeFaits = baseDeFait;

        m_trace = "";
        m_traceAbregee = "";
    }

    public abstract boolean rechercheBut(Fait but);

    private Regle rechercheRegleApplicablePlusDePremisse(Paquet baseDeRegles, BaseDeFait baseDeFaits) {
        int positionRegle = -1, nbPremisses = 0;

        for (int i = 0; i < baseDeRegles.getRegles().size(); i++) {
            if (baseDeRegles.getRegles().get(i).estApplicable(baseDeFaits) && (baseDeRegles.getRegles().get(i).getPremisses().size() > nbPremisses)) {
                positionRegle = i;
                nbPremisses = baseDeRegles.getRegles().get(i).getPremisses().size();
            }
        }
        if (positionRegle != (-1)) {
            return baseDeRegles.getRegles().get(positionRegle);
        } else {
            return null;
        }
    }

    /**
     * @param baseDeRegles Le paquet dans lequel on recherche un regle applicable
     * @param baseDeFaits  La base de fait sur laquel la règle doit être applicable
     * @return la regle applicable de plus haute priorité vis à vis du critère, ou null si aucune règle n'est applicable
     */
    private Regle rechercheRegleApplicableFaitPlusRecent(Paquet baseDeRegles, BaseDeFait baseDeFaits) {

        BaseDeFait baseDeFaitsTmp = new BaseDeFait();

        for (int i = m_baseDeFaits.size() - 1; i >= 0; i--) {

            baseDeFaitsTmp.add(m_baseDeFaits.get(i));

            for (Regle r : baseDeRegles.getRegles()) {

                if (r.estApplicable(baseDeFaitsTmp)) {
                    return r;
                }
            }
        }
        return null; //Aucune regle trouvée
    }

    /**
     * @param baseDeRegles Le paquet dans lequel on recherche un regle applicable
     * @param baseDeFaits  La base de fait sur laquel la règle doit être applicable
     * @param choixRegle   Le mode de selection parmis les règles applicable
     * @return la regle applicable de plus haute priorité vis à vis du critère, ou null si aucune règle n'est applicable
     */
    protected Regle rechercheRegleApplicable(Paquet baseDeRegles, BaseDeFait baseDeFaits, int choixRegle) {

        switch (choixRegle) {
            case LE_PLUS_DE_PREMISSES:
                return rechercheRegleApplicablePlusDePremisse(baseDeRegles, baseDeFaits);
            case LES_FAITS_DEDUITS_LES_PLUS_RECENTS:
                return rechercheRegleApplicableFaitPlusRecent(baseDeRegles, baseDeFaits);
        }

        return null;
    }


    public static Chainage getChainage(ModeChainage mode, Paquet baseDeRegle, BaseDeFait baseDeFait) {
        if(mode == null){
            System.err.println("Chainage.getChainage() : mode is null. Defaulting to ModeChainage.getDefault()");
            mode = ModeChainage.getDefault();
        }
        switch (mode) {
            case AVANT_PROFONDEUR:
                return new ChainageAvantProfondeur(baseDeRegle, baseDeFait);
            case AVANT_LARGEUR:
                return new ChainageAvantLargeur(baseDeRegle, baseDeFait);
            case ARRIERE:
                return new ChainageArriere(baseDeRegle, baseDeFait);
            default:
                return Chainage.getChainage(ModeChainage.getDefault(), baseDeRegle, baseDeFait);
        }
    }
    


    public BaseDeFait getBaseDeFaits() {
        return m_baseDeFaits;
    }

    public String getTrace() {
        return m_trace;
    }

    public String getTraceAbregee() {
        return m_traceAbregee;
    }

}
