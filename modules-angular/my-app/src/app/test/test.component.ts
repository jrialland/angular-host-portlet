import { Component } from "@angular/core";

declare global {
  interface Window {
    Liferay: any;
  }
}

@Component({
    selector: 'app-test',
    templateUrl: './test.component.html',
    styleUrl: './test.component.scss',
    standalone: true
})
export class AppTest {
  constructor() {
    console.log('AppTest');
  }

  onClick() {
    window.Liferay.Util.openToast({
      title: 'Success',
      message: 'You clicked the button!',
      type: 'success'
    });
  }
  
}