import { Component } from '@angular/core';
import axios from 'axios'
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-auth-content',
  templateUrl: './auth-content.component.html',
  styleUrls: ['./auth-content.component.css']
})
export class AuthContentComponent {
  data: string[] = ["First","Second","Third"];

  constructor(private axiosService: AxiosService){}

  ngOnInit(): void{
    this.axiosService.request(
      "GET",
      "/messages",
      ""
    ).then(
      (response) => this.data = response.data
    );
  }
}
