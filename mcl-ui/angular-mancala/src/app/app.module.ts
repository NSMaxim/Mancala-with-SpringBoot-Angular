import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SmallPitComponent } from './components/small-pit/small-pit.component';
import { LargePitComponent } from './components/large-pit/large-pit.component';
import { BoardComponent } from './components/board/board.component';
import { BoardWithPersistenceComponent } from './components/board-with-persistence/board-with-persistence.component';

const appRoutes: Routes = [
  { path: 'with-persistence', component: BoardWithPersistenceComponent },
  { path: 'in-the-moment', component: BoardComponent },
  { path: '**',   redirectTo: '/in-the-moment', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    SmallPitComponent,
    LargePitComponent,
    BoardComponent,
    BoardWithPersistenceComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true } // <-- debugging purposes only
    )
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
