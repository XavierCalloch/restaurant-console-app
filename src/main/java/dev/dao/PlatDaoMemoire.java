package dev.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.entite.Plat;

//@Component
public class PlatDaoMemoire implements IPlatDao {

    private List<Plat> listePlats = new ArrayList<>();

    @Override
    public List<Plat> listerPlats() {
        return listePlats;
    }

    @Override
    public void ajouterPlat(String nomPlat, Integer prixPlat) {
        Plat nouveauPlat = new Plat(nomPlat, prixPlat);
        listePlats.add(nouveauPlat);
    }
}
