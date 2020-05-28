package com.akveo.bundlejava.repository.search;

import com.akveo.bundlejava.model.index.Traitement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Date;

public interface TraitementRepository extends ElasticsearchRepository<Traitement, Long> {

    @Query("{\"bool\": {\"must\":[{\"match\": {\"process.name\": \"?0\"}}]}}")
    Page<Process> findByProcessNameUsingCustomQuery(String name, Pageable pageable);
    // find All records
    public Page<Traitement> findAll(Pageable pageable);
    public Page<Traitement> findByProcessName(String processName, Pageable pageable);
    public Page<Traitement> findByProcessEndBefore(Date datebefore, Pageable pageable);
    public Page<Traitement> findByProcessStartAfter(Date processStartAfter, Pageable pageable);
    public Page<Traitement> findByIdFun(String idFun, Pageable pageable);
    public Page<Traitement> findByIdTech(String idTech, Pageable pageable);
    public Page<Traitement> findByLog(String log, Pageable pageable);
    public Page<Traitement> findByStatus(String status, Pageable pageable);
}
