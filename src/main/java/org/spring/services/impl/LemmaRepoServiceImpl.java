package org.spring.services.impl;
import org.spring.model.Index;
import org.spring.model.Lemma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.spring.repo.LemmaRepository;
import org.spring.services.repo.LemmaRepo;

import java.util.List;

@Service
public class LemmaRepoServiceImpl implements LemmaRepo {

    private final LemmaRepository lemmaRepository;
    @Autowired
    public LemmaRepoServiceImpl(LemmaRepository lemmaRepository) {
        this.lemmaRepository = lemmaRepository;
    }

    @Override
    public List<Lemma> getLemma(String lemmaName) {
        List<Lemma> lemmas = null;
        try{
            lemmas = lemmaRepository.findByLemma(lemmaName);
        } catch (Exception e) {
            System.out.println(lemmaName);
            e.printStackTrace();
        }
        return lemmas;
    }

    @Override
    public synchronized void save(Lemma lemma) {
        lemmaRepository.save(lemma);
    }

    @Override
    public long lemmaCount(){
        return lemmaRepository.count();
    }

    @Override
    public long lemmaCount(long siteId){
        return lemmaRepository.count(siteId);
    }

    @Override
    public synchronized void deleteAllLemmas(List<Lemma> lemmaList){
        lemmaRepository.deleteAll(lemmaList);
    }


    @Override
    public List<Lemma> findLemmasByIndexing(List<Index> indexingList){
        int[] lemmaIdList = new int[indexingList.size()];
        for (int i = 0; i < indexingList.size(); i++) {
            lemmaIdList[i] = (int)indexingList.get(i).getLemmaId();
        }
        return lemmaRepository.findById(lemmaIdList);
    }
}
