import {Component, OnInit} from '@angular/core';
import {RechercheService} from "../../../@core/backend/common/services/Recherche.service";
import {Recherche} from "../../../@core/backend/common/model/Recherche";
import {LocalDataSource} from "ng2-smart-table";

@Component({
  selector: 'ngx-recherch',
  templateUrl: './recherch.component.html',
  styleUrls: ['./recherch.component.scss'],
  providers: [RechercheService],
})
export class RecherchComponent implements OnInit {
  recherche: Recherche = new Recherche();
  processNAme: string;
  status: string;
  idFun: string;
  idTech: string;
  dateStart: Date;
  dateEnd: Date;
  dataName: any[] = [];
  dataStatus: any[] = [];
  dataIdFun: any[] = [];
  dataIdTech: any[] = [];
  dataStartDate: any[] = [];
  dataEndDate: any[] = [];
  data: any[] = [];

  settings = {
    add: {
      addButtonContent: '<i class="nb-plus"></i>',
      createButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    edit: {
      editButtonContent: '<i class="nb-edit"></i>',
      saveButtonContent: '<i class="nb-checkmark"></i>',
      cancelButtonContent: '<i class="nb-close"></i>',
    },
    delete: {
      deleteButtonContent: '<i class="nb-trash"></i>',
      confirmDelete: true,
    },
    columns: {
      id: {
        title: 'ID',
        type: 'number',
      },
      processName: {
        title: 'Nom de traitement',
        type: 'string',
      },
      processStart: {
        title: 'Date de debut',
        type: 'date',
      },
      processEnd: {
        title: 'Date de fin',
        type: 'date',
      },
      idFun: {
        title: 'Id fonc',
        type: 'string',
      },
      idTech: {
        title: 'Id tech',
        type: 'string',
      },
      status: {
        title: 'Log',
        type: 'string',
      },
      log: {
        title: 'Log',
        type: 'string',
      },
    },
  };

  source: LocalDataSource = new LocalDataSource();

  constructor(private service: RechercheService) {
  }

  ngOnInit() {
    this.service.getAll().subscribe(data => {
      this.source = data;
      console.log(data);
    });
  }

  save() {
    this.service.create(this.recherche).subscribe(data => {
      console.log(data);
    });
  }

  getByName() {
    this.service.getByName(this.processNAme).subscribe(data => {
      this.dataName = data;
      console.log(data);
    });
  }

  getByStatus() {
    this.service.getByStatus(this.status).subscribe(data => {
      this.dataStatus = data;
      console.log(data);
    });
  }

  getByIdTech() {
    this.service.getByIdTech(this.idTech).subscribe(data => {
      this.dataIdTech = data;
      console.log(data);
    });
  }

  getByIdFun() {
    this.service.getByIdFun(this.idFun).subscribe(data => {
      this.dataIdFun = data;
      console.log(data);
    });
  }

  getByDateStart() {
    this.service.getByDateStart(this.dateStart).subscribe(data => {
      this.dateStart = data;
      console.log(data);
    });
  }

  getByDateEnd() {
    this.service.getByDateEnd(this.dateEnd).subscribe(data => {
      this.dataEndDate = data;
      console.log(data);
    });
  }

  search() {
    if (this.status != null) {
      this.getByStatus();
    }
    if (this.dateEnd != null) {
      this.getByDateEnd();
    }
    if (this.dateStart != null) {
      this.getByDateStart();
    }
    if (this.idTech != null) {
      this.getByIdTech();
    }
    if (this.idFun != null) {
      this.getByIdFun();
    }
    const list1 = this.dataStatus.concat(this.dataIdFun).concat(this.dataIdTech);
    const list = list1.concat(this.dataStartDate).concat(this.dataEndDate);
    this.source.load(list);
  }
}
