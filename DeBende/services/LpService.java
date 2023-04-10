package com.shopr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopr.domains.articles.Lp;
import com.shopr.repositories.LpRepository;

@Service
public class LpService {

    @Autowired
    private LpRepository lpRepository;

    public List<Lp> getAllLpsFromDb(){
        return lpRepository.getAllLpsFromDb();
    }

    public void addLpToDb(Lp lp) {
        lpRepository.addLpToDb(lp);
    }

    public Lp findLpById(long id) {
        return lpRepository.findLpById(id);
    }

    public void editLpInDb(Lp lp) {
        lpRepository.editLpInDb(lp);
    }

    public void removeLpById(long id) {
        lpRepository.removeLpById(id);
    }

    public List<Lp> searchLp(Lp lpSearched) {
        return lpRepository.searchLp(lpSearched);
    }
}
