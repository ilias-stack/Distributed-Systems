import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js/lib/keycloak";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'ecom-front';

  public profile!:KeycloakProfile;

  constructor(public keycloakService:KeycloakService) {
  }

  async handleLogin() {
    await this.keycloakService.login({
      redirectUri:window.location.origin
    })
  }

  async handleLogout() {
    await this.keycloakService.logout(window.location.origin)
  }

  ngOnInit(): void {
    if(this.keycloakService.isLoggedIn()){
      this.keycloakService.loadUserProfile().then(value => {
        this.profile=value;
      })
    }
  }
}
