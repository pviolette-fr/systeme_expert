package fr.univangers.vrpv.MoteurInference;

import org.json.simple.JSONAware;

public enum ModeChainage implements JSONAware{
    AVANT_PROFONDEUR,
    AVANT_LARGEUR,
    ARRIERE,
    DEFAULT;

    @Override
    public String toString() {
        return super.toString();
    }

    static ModeChainage getDefault(){
        return AVANT_PROFONDEUR;
    }


    @Override
    public String toJSONString() {
        return "\"" + this.toString() + "\"";
    }

    public static ModeChainage parseString(String str){
        for(ModeChainage c : values()){
            if(c.toString() == str){
                return c;
            }
        }
        return null;
    }
}
