package com.akveo.bundlejava.repository;

import com.akveo.bundlejava.model.index.Traitement;
import com.akveo.bundlejava.repository.search.TraitementRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {
    Logger LOG= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TraitementRepository traitementRepository;

    @Autowired
    private ElasticsearchTemplate esTemplate;

//    @Before
//    public void before() {
//        //esTemplate.deleteIndex(Process.class);
//        esTemplate.createIndex(Process.class);
//        esTemplate.putMapping(Process.class);
//        esTemplate.refresh(Process.class);
//    }

//    @Test
//    public void testSave() {
//
//        Traitement traitement = new Traitement();
//        traitement.setProcessName("PROS484877");
//        traitement.setIdFun("0PIJUUJ87");
//        traitement.setIdTech("TECH65468");
//        traitement.setProcessEnd(new Date());
//        traitement.setProcessStart(new Date());
//        Traitement testProcess = traitementRepository.save(traitement);
//        assertEquals(testProcess.getProcessName(),traitement.getProcessName());
//        LOG.info("---------->",testProcess);
////        assertNotNull(testProcess.getId());
////        assertEquals(testProcess.getTitle(), Process.getTitle());
////        assertEquals(testProcess.getAuthor(), Process.getAuthor());
////        assertEquals(testProcess.getReleaseDate(), Process.getReleaseDate());
//
//    }

    @Test
    public void testFindByIdFun() {

        Traitement process = new Traitement();
        process.setIdFun("FUN100");
        process.setProcessName("NAME100");
        process.setIdTech("TECH100");
        traitementRepository.save(process);

        Page<Traitement> testProcess =traitementRepository.findByIdFun(process.getIdFun(),PageRequest.of(0,5));
        Stream<Traitement> traitementStream= testProcess.get();
        traitementStream.forEach(x-> System.out.println(x.toString()));
        LOG.info("***************",testProcess.toString());
    }

    @Test
    public void testFindByName() {

        Traitement process = new Traitement();
        traitementRepository.save(process);
        Pageable pageable=PageRequest.of(0,100);
        Page<Traitement> processFind =traitementRepository.findByProcessName(process.getProcessName(), pageable);
        assertThat(processFind.getTotalElements(), is(1));
        LOG.info("-----------",processFind.toString());
    }

    @Test
    public void testFindByIdFunAndProcessName() {

        List<Traitement> traitementList = new ArrayList<>();
        for (int i=0;i<=10;i++){
            Traitement traitement = new Traitement();
            traitement.setProcessName("N100"+i);
            traitement.setIdTech("TECH100"+i*3);
            traitement.setIdFun("FUN00"+i*9);
            traitement.setId(10L+i);
            traitement.setStatus("UP489874");
            traitement.setLog("[INFO] 10/6545/6265 --- 5684897949");
            traitement.setProcessEnd(new Date());
            traitement.setProcessStart(new Date());
            traitementRepository.save(traitement);
            traitementList.add(new Traitement());
        }

//        for (Traitement item : traitementList) {
//            traitementRepository.save(item);
//        }

//        Page<Traitement> item1 = traitementRepository.findByIdFun("FUN100",PageRequest.of(0, 1000));
//        assertThat(item1.getTotalElements(), is(4L));
//
//        Page<Traitement> item2 = traitementRepository.findByProcessName("NAME100", PageRequest.of(0, 1000));
//        assertThat(item2.getTotalElements(), is(1L));
//        LOG.info("++++++++++",item1.toString(),item2.toString());
    }

    @Test
    public void testDelete() {
        Page<Traitement> item2 =  new PageImpl<Traitement>(traitementRepository.findAll(PageRequest.of(0,1000)).getContent());
//        List<Traitement> traitements = item2.getContent()
//                                            .stream()
//                                            .filter(i->i.getProcessName().equals(""))
//                                            .collect(Collectors.toList());
        traitementRepository.deleteAll();
    }

}