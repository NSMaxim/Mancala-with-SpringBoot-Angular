import { TestBed } from '@angular/core/testing';

import { PersistenceGameService } from './persistence-game.service';

describe('PersistanceGameService', () => {
  let service: PersistenceGameService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PersistenceGameService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
