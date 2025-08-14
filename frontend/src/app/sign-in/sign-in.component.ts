import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputComponent } from '../shared/components/input/input.component';

@Component({
  selector: 'app-sign-in',
  imports: [FormsModule, InputComponent ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  email: string = '';
  password: string = '';

  signIn() {
    console.log('Email:', this.email);
    console.log('Password:', this.password);
  }
}
