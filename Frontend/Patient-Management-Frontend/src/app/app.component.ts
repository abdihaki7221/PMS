import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Patient } from './patient.model';

/***
 * @author a_omar
 */

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'patient-management-app';
  patient: Patient = {
    firstName: '',
    middleName: '',
    lastName: '',
    identificationType: '',
    identificationNumber: ''
  };

  successMessage = '';
  errorMessage = '';
  successClass = 'alert alert-success'; 
  errorClass = 'alert alert-danger';

  constructor(private httpClient: HttpClient) {}
  createPatient() {
  
    const apiUrl = 'http://localhost:8090/create/patient'; 
    this.httpClient.post(apiUrl, this.patient).subscribe({
      next: (response: any) => {
        if(response.status =="00"){
          this.successMessage = response.message; 
          this.errorMessage = ''; 
        }else{
          this.errorMessage = response.message; 
          this.successMessage = ''; 
        }
      },
      error: (error: any) => {

        
        console.error( error.message);
        this.errorMessage =error.message 
        this.successMessage = ''; 
      }
    });
  }
 


  
}
