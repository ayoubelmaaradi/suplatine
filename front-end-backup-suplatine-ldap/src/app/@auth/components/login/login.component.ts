/*
 * Copyright (c) Akveo 2019. All Rights Reserved.
 * Licensed under the Single Application / Multi Application License.
 * See LICENSE_SINGLE_APP / LICENSE_MULTI_APP in the 'docs' folder for license information on type of purchased license.
 */
import {ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {
  NB_AUTH_OPTIONS,
  NbAuthSocialLink,
  NbAuthService,
  NbAuthResult,
} from '@nebular/auth';
import {getDeepFromObject} from '../../helpers';
import {NbThemeService} from '@nebular/theme';
import {EMAIL_PATTERN} from '../constants';
import {InitUserService} from '../../../@theme/services/init-user.service';
import {AuthService} from "../../auth.service";
import {environment} from "../../../../environments/environment";
import {HttpService} from "../../../@core/backend/common/api/http.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'ngx-login',
  templateUrl: './login.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [AuthService, HttpClient],
})

export class NgxLoginComponent implements OnInit {

  minLength: number = this.getConfigValue('forms.validation.password.minLength');
  maxLength: number = this.getConfigValue('forms.validation.password.maxLength');
  redirectDelay: number = this.getConfigValue('forms.login.redirectDelay');
  showMessages: any = this.getConfigValue('forms.login.showMessages');
  strategy: string = this.getConfigValue('forms.login.strategy');
  socialLinks: NbAuthSocialLink[] = this.getConfigValue('forms.login.socialLinks');
  rememberMe = this.getConfigValue('forms.login.rememberMe');
  isEmailRequired: boolean = this.getConfigValue('forms.validation.email.required');
  isPasswordRequired: boolean = this.getConfigValue('forms.validation.password.required');

  errors: string[] = [];
  messages: string[] = [];
  user: any = {
    username: '',
    password: '',
    rememberMe: '',
  };
  submitted: boolean = false;
  loginForm: FormGroup;
  alive: boolean = true;

  get email() {
    return this.loginForm.get('email');
  }

  get password() {
    return this.loginForm.get('password');
  }

  get apiUrl(): string {
    return environment.apiUrl;
  }

  username: string = '';
  passw: string = '';

  constructor(protected service: NbAuthService, private authService: AuthService,
              @Inject(NB_AUTH_OPTIONS) protected options = {},
              protected cd: ChangeDetectorRef,
              protected themeService: NbThemeService,
              private fb: FormBuilder, private api: HttpService, private httpClient: HttpClient,
              protected router: Router,
              protected initUserService: InitUserService) {
  }

  ngOnInit(): void {
    const emailValidators = [
      // Validators.pattern(EMAIL_PATTERN),
    ];
    // this.isEmailRequired && emailValidators.push(Validators.required);

    const passwordValidators = [
      Validators.minLength(this.minLength),
      Validators.maxLength(this.maxLength),
    ];
    // this.isPasswordRequired && passwordValidators.push(Validators.required);

    this.loginForm = this.fb.group({
      username: this.fb.control(''), // validator removed
      password: this.fb.control(''), // validator emoved
      rememberMe: this.fb.control(''),
    });
  }


  login(): void {
    console.log(this.username + this.passw);
    this.user = this.loginForm.value;
    this.errors = [];
    this.messages = [];
    this.submitted = true;
    this.authService.authenticate('email', {
      email: this.username,
      password: this.passw,
    }).subscribe((result: NbAuthResult) => {
      this.submitted = false;

      if (result.isSuccess()) {
        console.log('login success !!!!!!!!');
        this.messages = result.getMessages();
        this.initUserService.initCurrentUser().subscribe();
      } else {
        this.errors = result.getErrors();
      }

      const redirect = result.getRedirect();
      if (redirect) {
        setTimeout(() => {
          return this.router.navigateByUrl(redirect);
        }, this.redirectDelay);
      }
      this.cd.detectChanges();
    });
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }
}
