package com.akveo.bundlejava.model.index;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document(indexName = "processing", type = "processes")
public class Traitement {
    @Id
    private Long id;
    private String processName;
    private Date processStart;
    private Date processEnd;
    @NotNull
    private String idTech;
    @NotNull
    private String idFun;
    private String status;
    private String log;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Date getProcessStart() {
        return processStart;
    }

    public void setProcessStart(Date processStart) {
        this.processStart = processStart;
    }

    public Date getProcessEnd() {
        return processEnd;
    }

    public void setProcessEnd(Date processEnd) {
        this.processEnd = processEnd;
    }

    public String getIdTech() {
        return idTech;
    }

    public void setIdTech(String idTech) {
        this.idTech = idTech;
    }

    public String getIdFun() {
        return idFun;
    }

    public void setIdFun(String idFun) {
        this.idFun = idFun;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
