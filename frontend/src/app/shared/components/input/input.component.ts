import { Component, EventEmitter, forwardRef, Input, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'ui-input',
  imports: [],
  templateUrl: './input.component.html',
  styleUrl: './input.component.css',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => InputComponent),
      multi: true,
    },
  ],
})
export class InputComponent implements ControlValueAccessor{
  @Input() name: string = '';
  @Input() placeholder: string = '';
  @Input() type: string = 'text';
  @Input() value: string = '';
  @Output() valueChange = new EventEmitter<string>();

  onChange: any = () => {};
  onTouched: any = () => {};

  onInput(event: Event) {
    this.value = (event.target as HTMLInputElement).value;
    this.valueChange.emit(this.value);
    this.onChange(this.value);
  }

  onBlur() {
    this.onTouched();
  }

  writeValue(value: string): void {
    this.value = value || '';
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
}