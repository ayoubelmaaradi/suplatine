package com.akveo.bundlejava.api;

import com.akveo.bundlejava.model.index.Traitement;
import com.akveo.bundlejava.repository.search.TraitementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class TraitementController {
    @Autowired
    private TraitementRepository processRepository;

    @GetMapping("/get-all")
    public Object getAll(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                         @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findAll(paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Process>();
        }
    }

    @GetMapping(value = "/get-processame/{processName}")
    public Object getByProcessName(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                                   @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo,
                                   @PathVariable("processNmae") String processName) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findByProcessName(processName, paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Traitement>();
        }
    }

    @GetMapping("/get-idFun/{idFun}")
    public Object getByIdFun(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                             @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo,
                             @PathVariable("idFun") String idFun) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findByIdFun(idFun, paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Traitement>();
        }
    }

    @GetMapping("/get-idTech/{idTech}")
    public Object getByIdTech(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                              @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo,
                              @PathVariable("idTech") String idTech) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findByIdTech(idTech, paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Process>();
        }
    }

    @GetMapping("/get-status/{status}")
    public Object getByStatus(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                              @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo,
                              @PathVariable("status") String status) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findByStatus(status, paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Process>();
        }
    }

    @GetMapping("/get-dateStart/{dateStart}")
    public Object getByDateStart(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                                 @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo,
                                 @PathVariable("dateStart") Date dateStart) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findByProcessEndBefore(dateStart, paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Process>();
        }
    }

    @GetMapping("/get-dateEnd/{dateEnd}")
    public Object getByDateEnd(@RequestParam(value = "_perPage", required = false, defaultValue = "1000") Integer pageSize,
                               @RequestParam(value = "_pageNo", required = false, defaultValue = "0") Integer pageNo,
                               @PathVariable("dateEnd") Date dateEnd) {
        Pageable paging = PageRequest.of(pageNo, pageSize);

        Page<Traitement> pagedResult = processRepository.findByProcessStartAfter(dateEnd, paging);

        if (pagedResult.hasContent()) {
            return (ResponseEntity.ok(pagedResult.getContent()));
        } else {
            return new ArrayList<Process>();
        }
    }
}
