import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { InputComponent } from '../shared/components/input/input.component';
import { LabelComponent } from '../shared/components/label/label.component';
import { ButtonComponent } from '../shared/components/button/button.component';

@Component({
  selector: 'app-sign-in',
  imports: [FormsModule, InputComponent, LabelComponent, ButtonComponent ],
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
