import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {SignupComponent} from "./pages/signup/components/signup.component";
import {LoginComponent} from "./pages/login/components/login.component";
import {HomeComponent} from "./pages/home/components/home.component";
import {AdminDashboardComponent} from "./pages/admin/components/admin-dashboard/admin-dashboard.component";
import {UserDashboardComponent} from "./pages/user/components/user-dashboard/user-dashboard.component";
import {AdminGuard} from "./pages/guard/services/admin/admin.guard";
import {NormalGuard} from "./pages/guard/services/normal/normal.guard";

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent,
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'admin-dashboard',
    component: AdminDashboardComponent,
    pathMatch: 'full',
    canActivate: [AdminGuard] // Linking admin guard
  },
  {
    path: 'user-dashboard',
    component: UserDashboardComponent,
    pathMatch: 'full',
    canActivate: [NormalGuard] // Linking admin guard
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
