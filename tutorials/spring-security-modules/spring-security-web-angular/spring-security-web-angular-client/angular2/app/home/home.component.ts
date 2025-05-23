﻿import { Component, OnInit } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import 'rxjs/add/operator/map'

@Component({
    selector:'home',
    templateUrl: './app/home/home.component.html'
})

export class HomeComponent implements OnInit {

    userName: string;

    constructor(private http: Http) { }

    ngOnInit() {
        let url = 'http://localhost:8082/user';
        let headers = new Headers({
            'Authorization': 'Basic ' + sessionStorage.getItem('token')
        });
        let options = new RequestOptions({ headers: headers });
        this.http.post(url,{}, options).
        map(res => res.json()).
        subscribe(
            principal => this.userName = principal.name,
            error => {
                if(error.status == 401)
                    alert('Unauthorized');
            }
        );
    }

    logout() {
        sessionStorage.setItem('token', '');
    }
}